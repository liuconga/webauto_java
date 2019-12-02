package com.curry.webauto_java.tool;

import org.openqa.selenium.chrome.ChromeDriver;

//此工具类为获取driver以及关闭dirver
public class DriverUtil {
    public static ChromeDriver driver=null;
public static ChromeDriver getDriver(){
    if (driver==null){
        //创建谷歌浏览器driver对象
        driver = new ChromeDriver();
        //获取网址
        driver.get("file:///Users/liucong/Downloads/page/%E6%B3%A8%E5%86%8CA.html");
    }
    return driver;
}
    public static void quitDriver(){
   if (driver!=null){
       driver.quit();
   }

    }
}
