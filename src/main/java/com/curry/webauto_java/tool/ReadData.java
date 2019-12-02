package com.curry.webauto_java.tool;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class ReadData {
    /*通过json官方解析方式解析json数据*/
    public static Object[][] readJson() throws IOException {
        //java跟python的不同：相对路径都是从项目根目录开始的
        String s = FileUtils.readFileToString(new File("src/main/java/com/curry/webauto_java/data/data.json"), "utf-8");
        JSONArray jsonArray = new JSONArray(s);
        Object[][] objects = new Object[jsonArray.length()][2];
        for (int i = 0; i < jsonArray.length(); i++) {
            objects[i][0] = jsonArray.getJSONObject(i).getJSONObject("user" + i).getString("name");
            objects[i][1] = jsonArray.getJSONObject(i).getJSONObject("user" + i).getInt("age");

        }

        return objects;

    }

    /*通过apache的poi(3.9)类库读取excel文件*/
    public static Object[][] readExcel() throws IOException, InvalidFormatException {
        //1.获取excel文件流
        FileInputStream is = new FileInputStream("/Users/liucong/Downloads/javaprojects/webauto_java/src/main/java/com/curry/webauto_java/data/data.xlsx");
        //2.获取excel文件
        Workbook  excel = WorkbookFactory.create(is);
        //3.获取表格(名为login的表格)
        Sheet login = excel.getSheet("login");
        //4.获取单元格中内容
        //5.通过以下代码转换excel中数字格式：否则excel输出数字为1.8...E10这种形式
        DecimalFormat df = new DecimalFormat("#");
        //6.定义二维数组类型变量接收读取excel表中内容
        Object[][] objects=new Object[login.getLastRowNum()][login.getRow(0).getLastCellNum()];
        for(int i=1;i<=login.getLastRowNum();i++){
            for(int k=0;k<login.getRow(i).getLastCellNum();k++){
                Cell cell = login.getRow(i).getCell(k);
               //5.1通过java.text.DecimalFormat转换输出格式
                String s = df.format(cell.getNumericCellValue());
                objects[i-1][k]=s;
            }
        }
        return objects;
    }


    /*以下为测试类*/
    public static void main(String[] args) throws IOException, InvalidFormatException {
//        Object[][] objects = readJson();
//        System.out.println("" + objects[0][0] + objects[0][1]);
//        Object[][] objects=readExel();
//        System.out.println(""+objects[0][0]+objects[0][1]+objects[0][2]);
    }
}
