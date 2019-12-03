package com.curry.webauto_java.scripts;

import com.curry.webauto_java.Retry;
import com.curry.webauto_java.tool.ReadData;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
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
    //以下为allure添加用例描述
    @Description("步骤2：我是test_login")
    public void test_login() {
        System.out.println("我是login");
        assert 1==1;
    }

    @Test(priority = 3)
    //以下为allure添加用例描述
    @Description("步骤3：我是test_shop")
    public void test_shop() {
        System.out.println("我是shop");

    }
    @Test(priority = 1)
    //以下为allure用例描述
    @Description("步骤1：我是test_order")
    public void test_order() {
        //以下为allure添加描述
        Allure.attachment("描述","我是描述内容");
        System.out.println("我是order");

    }
    //忽略此方法
    //添加allure测试用例问题
    @Issue("步骤4：我是test_pay,我被跳过啦")
    @Test(enabled = false)
    public void test_pay() {
        System.out.println("我是opay");

    }
    //依赖于test_login方法:方法login要先执行
    //alwaysRun ：即使依赖的方法运行失败，此方法照样运行
    @Test(dependsOnMethods = "test_login",alwaysRun = true)
    //添加allure测试用例描述
    @Description("步骤5：我是test_logout,我是test_logout")
    public void test_logout() {
        System.out.println("我是logout");

    }


    /*提供dataProvider的方法*/
    @DataProvider(name = "testdata")
    public Object[][] dataProvider() throws IOException, InvalidFormatException {

        return ReadData.readExcel();
    }

    @Test(dataProvider = "testdata")
    //添加allure测试用例描述
    @Description("我是testDataProvider的allure报告描述（注解）")
    public void testDataProvider(String a,String b,String c){
        Allure.attachment("我是allure的步骤描述","方法级别的描述");
        System.out.println("++++++++"+a+"----"+b+"-----"+c);
    }

}
