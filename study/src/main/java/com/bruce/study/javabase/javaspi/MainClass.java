package com.bruce.study.javabase.javaspi;
/*
 *@ClassName MainClass
 *@Description
 * 使用SPI的机制:  服务动态扩展机制
 * 1.当服务提供者提供了接口的一种具体实现后,
 * 在工程中的META-INF/services 目录下创建一个以"接口全限定名"为命名的文件,内容为实现类的全限定名
 * 2.接口实现类所在的工程 classpath 中
 * 3.主程序通过java.util.ServiceLoder动态装载实现模块,她通过扫描META-INF/services目录下的配置文件找到实现类的权限的名称
 * 4. SPI的实现类必须带一个不带参数的构造方法;
 *
 *@Author Bruce
 *@Date 2020/6/28 11:41
 *@Version 1.0
 */

import java.util.ServiceLoader;

public class MainClass {
    private static ServiceLoader<IParseDoc> iParseDocs = ServiceLoader.load(IParseDoc.class);

    public static void main(String[] args) {
        ServiceLoader<IParseDoc> iParseDocs = ServiceLoader.load(IParseDoc.class);

//        IParseDoc iParseDoc = new ExcelParse();

        for (IParseDoc SS:iParseDocs){
            SS.parse();
        }
    }
}