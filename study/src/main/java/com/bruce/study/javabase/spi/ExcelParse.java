package com.bruce.study.javabase.spi;
/*
 *@ClassName ExcelParse
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/28 11:40
 *@Version 1.0
 */

public class ExcelParse implements IParseDoc{

    @Override
    public void parse() {
        System.out.println("解析 Excel");
    }
}