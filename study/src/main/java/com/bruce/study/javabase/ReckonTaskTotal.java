package com.bruce.study.javabase;
/*
 *@ClassName ReckonTask
 *@Description 统计某个文件夹路径下指定类型的文件的大小
    常用方法：
    1. 单线程依次遍历每层目录及文件，记录并对比文件大小
        写法简单，效率不高
    2.多线程线程池遍历每层目录及文件
       线程数量，每条线程遍历的目标，需要控制，很容易出现空闲线程
 *@Author Bruce
 *@Date 2020/6/19 17:13
 *@Version 1.0
 */

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


public class ReckonTaskTotal extends RecursiveTask {
    private static final long serialVersionUID = 1L;

    File path;
    Filter2 filter;
    String endStr;

    public ReckonTaskTotal(File path, Filter2 filter,String endStr) {
        this.path = path;
        this.filter = filter;
        this.endStr = endStr;
    }

    @Override
    protected Long compute() {
        Long total = 0L;
        File[] listFile = path.listFiles();
        if(listFile != null){
            for (int i = 0; i < listFile.length; i++) {
                File nFile = listFile[i];
                if (nFile.isDirectory()) {// 文件夹
                    ReckonTaskTotal rt = new ReckonTaskTotal(nFile, filter,endStr);
                    rt.fork();
                    total = filter.addFilter(total, (Long) rt.join());
                } else {// 文件
                    if("*".equals(endStr)){
                        total = filter.addFilter(total, nFile.length());
                    }else {
                        if(nFile.getName().endsWith(endStr)){
                            System.out.printf("文件名称: %s\n 文件大小: %sMB\n",nFile.getName(),nFile.length()/1024/1024);
                            total = filter.addFilter(total, nFile.length());
                        }
                    }

                }
            }
        }else {
            System.out.println(" listFile is null");
        }
        return total;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        File path = new File("C:\\");
        ForkJoinPool fjp = new ForkJoinPool();
        long s = System.currentTimeMillis();
        String fileType = "*";
        ReckonTaskTotal rt = new ReckonTaskTotal(path, new TotalFilter(),fileType);
        ForkJoinTask<Long> fjt = fjp.submit(rt);
        Long result = fjt.get();
        System.out.printf("文件类型: %s\n 文件总大小: %.2fMB\n消耗时间: %sms\n",fileType,
                result / 1024 / 1024f, System.currentTimeMillis() - s);
    }

}

interface Filter2 {
    Long addFilter(Long total, Long f1);
}


class TotalFilter implements Filter2 {

    @Override
    public Long addFilter(Long total, Long f1) {

        return total+=f1;
    }
}