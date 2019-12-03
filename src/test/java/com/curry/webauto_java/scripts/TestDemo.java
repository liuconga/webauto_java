package com.curry.webauto_java.scripts;

import com.curry.webauto_java.Retry;
import com.curry.webauto_java.tool.ReadData;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/*以下为TestNG的测试类
*
* priority属性：执行顺序（pytest中order）
* dependsOnMethods：依赖方法，被依赖的方法先执行
* alwaysRun ：设为true时，这个方法总是运行的，即使依赖的方法运行失败
* enabled: 当前方法和类是否被激活：及是否或略此方法
*
*
*
*
* */
public class TestDemo {
    //priority属性为执行顺序
    @Test(priority = 2,retryAnalyzer = Retry.class)
    public void test_login() {
        System.out.println("我是login");
        assert 1==1;
    }

    @Test(priority = 3)
    public void test_shop() {
        System.out.println("我是shop");

    }
    @Test(priority = 1)
    public void test_order() {
        Allure.attachment("描述","我是描述内容");
        System.out.println("我是order");

    }
    //忽略此方法
    @Test(enabled = false)
    public void test_pay() {
        System.out.println("我是opay");

    }
    //依赖于test_login方法:方法login要先执行
    //alwaysRun ：即使依赖的方法运行失败，此方法照样运行
    @Test(dependsOnMethods = "test_login",alwaysRun = true)
    @Description("你好hellollllllllllllllllll")
    public void test_logout() {
        System.out.println("我是logout");

    }


    /*提供dataProvider的方法*/
    @DataProvider(name = "testdata")
    public Object[][] dataProvider() throws IOException, InvalidFormatException {

        return ReadData.readExcel();
    }

    @Test(dataProvider = "testdata")
    public void testDataProvider(String a,String b,String c){
        System.out.println("++++++++"+a+"----"+b+"-----"+c);
    }

}
