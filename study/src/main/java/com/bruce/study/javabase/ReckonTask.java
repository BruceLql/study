package com.bruce.study.javabase;
/*
 *@ClassName ReckonTask
 *@Description 查询某个文件夹路径下容量最大的文件
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


public class ReckonTask extends RecursiveTask {
    private static final long serialVersionUID = 1L;

    File path;
    Filter filter;

    public ReckonTask(File path, Filter filter) {
        this.path = path;
        this.filter = filter;
    }

    @Override
    protected File compute() {
        System.out.println("copute====================");
        File maxFile = null;
        Long total = 0L;
        File[] listFile = path.listFiles();
        if(listFile != null){
            for (int i = 0; i < listFile.length; i++) {
                File nFile = listFile[i];
                if (nFile.isDirectory()) {// 文件夹
                    ReckonTask rt = new ReckonTask(nFile, filter);
                    rt.fork();
                    maxFile = filter.addFilter(maxFile, (File) rt.join());
                } else {// 文件
                    maxFile = filter .addFilter(maxFile, nFile);
                }
            }
        }else {
            System.out.println(" listFile is null");
        }
        return maxFile;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        File path = new File("D:\\");
        ForkJoinPool fjp = new ForkJoinPool();
        long s = System.currentTimeMillis();
        ReckonTask rt = new ReckonTask(path, new MaxFilter());
        ForkJoinTask<File> fjt = fjp.submit(rt);
        File result = fjt.get();
        System.out.printf("文件目录: %s\n文件大小: %.2fMB\n消耗时间: %sms\n", result.getAbsolutePath(),
                result.length() / 1024 / 1024f, System.currentTimeMillis() - s);
    }

}

interface Filter {
    File addFilter(File f1, File f2);
}

class MaxFilter implements Filter {

    @Override
    public File addFilter(File f1, File f2) {
        if (f1 == null)
            return f2;
        else if (f2 == null)
            return f1;
        else {
            return f1.length()> f2.length()?f1:f2;
        }
    }

}
