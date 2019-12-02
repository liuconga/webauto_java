package com.curry.webauto_java.scripts;

import com.curry.webauto_java.tool.DriverUtil;
import com.curry.webauto_java.tool.LogUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/*这是TestNg使用的基本介绍*/
public class TestSeleniumAPI {
    ChromeDriver driver;

    //  类级别-开始
    @BeforeClass
    public void setUpClass() {
        //获取driver对象
        driver = DriverUtil.getDriver();

        //添加隐式等待  --参数一：超时时间， --参数二：时间的单位
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //最大化窗口
        driver.manage().window().maximize();
    }

    //  类级别-结束
    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }

    @BeforeMethod
    public void setUp() {
        System.out.println("我是方法级别开始");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("我是方法级别结束");
    }

    /*-------------元素定位API————————————————————————*/
    @Test
    public void testSeleniumElement() throws InterruptedException {

        //1.元素定位-id
        //获取百度输入框
        WebElement text_input = driver.findElement(By.id("kw"));
        //以下为ChromDriver类的方法，以上为WebDriver类的方法
//        WebElement text_input = driver.findElementById("kw");
        text_input.sendKeys("hello word");
        //获取点击按钮
        driver.findElement(By.id("su")).click();
        //2.元素定位-link_text
        WebElement link_text = driver.findElement(By.linkText("hello world_百度百科"));
//        WebElement link_text=driver.findElementByLinkText("hello world_百度百科");

        link_text.click();
        Thread.sleep(2000);
        //多窗口之切换handle
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        //3.元素定位-xpath
        WebElement text = driver.findElement(By.xpath("//a[contains(text(),'个人中心')]"));
//        WebElement text =driver.findElementByXPath("//a[contains(text(),'个人中心')]");
        text.click();
        Thread.sleep(2000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[2].toString());

        //4.元素定位-css-id
        driver.findElement(By.cssSelector(" #TANGRAM__PSP_3__footerULoginBtn")).click();
//        driver.findElementByCssSelector(" #TANGRAM__PSP_3__footerULoginBtn")).click();
        Thread.sleep(2000);
    }


    /*————————————浏览器操作API————————————————————*/
    @Test
    public void testBrowserManipulation() {
        //1.浏览器导航
        driver.get("http://www.jd.com");

        //2.获取当前url
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

        //3.浏览器向前,向后,刷新
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        //4.获取当前标题
        String title = driver.getTitle();
        System.out.println(title);

        //5.获取窗口句柄
        String windowHandle = driver.getWindowHandle();
        //获取所有窗口句柄
        Set<String> windowHandles = driver.getWindowHandles();
        //切换句柄
        driver.switchTo().window(windowHandle);

        //6.关闭窗口 close(),关闭当前窗口；
        driver.close();

        //7.关闭浏览器驱动quit()
        driver.quit();
        /*退出将：
            关闭与该WebDriver会话关联的所有窗口和选项卡
            关闭浏览器进程
            关闭后台驱动程序
            通知Selenium Grid不再使用浏览器，以便其他会话可以使用它（如果您正在使用Selenium Grid）
            退出失败将使额外的后台进程和端口在您的计算机上运行，​​这可能会在以后引起您的问题。*/

        //8.切换frame框架
        //使用frame的索引进行切换
        driver.switchTo().frame(1);
        //使用WebElement进行切换-此方法最灵活
        driver.switchTo().frame(driver.findElementById("su"));
        //使用name或id进行切换
        driver.switchTo().frame("su");
        //最后要回到默认框架
        driver.switchTo().defaultContent();

        //9.窗口管理-获取窗口大小
        //方式1：
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        //方式2：
        Dimension size = driver.manage().window().getSize();
        int width1 = size.getWidth();
        int height1 = size.getHeight();

        //窗口管理-设定窗口大小
        driver.manage().window().setSize(new Dimension(1024, 768));
        //窗口管理-获取窗口位置（浏览器左上角坐标）
        // 方式1：
        int x = driver.manage().window().getPosition().getX();
        int y = driver.manage().window().getPosition().getY();

        // 方式2：
        Point position = driver.manage().window().getPosition();
        int x1 = position.getX();
        int y1 = position.getY();
        //窗口管理-设定窗口位置(将窗口移到所选位置。)
        driver.manage().window().setPosition(new Point(0, 0));

        //10.最大化窗口及全屏窗口
        driver.manage().window().maximize();
        //全屏
        driver.manage().window().fullscreen();

    }

    /*--------等待API--------------*/
    @Test
    public void testWaits() {
        //1.显示等待-lamada表达式形式；
        WebElement element = new WebDriverWait(driver, 10).until(webDriver -> webDriver.findElement(By.id("su")));
        //2.隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /*--------鼠标键盘操作API--------------*/
    @Test
    public void testMouseAndKeyboard() {
        //1.鼠标操作
        Actions actions = new Actions(driver);
        //鼠标悬停
        actions.moveToElement(driver.findElementById("su"));
        //鼠标双击
        actions.doubleClick();
        //鼠标单击右键
        actions.contextClick(driver.findElementById("su"));
        //鼠标拖动
        actions.dragAndDrop(driver.findElementById("su"), driver.findElementById("a"));
        actions.perform();
        //2.键盘操作
        WebElement e = driver.findElementById("su");
        //复制
        e.sendKeys(Keys.COMMAND, "c");
        //粘贴
        e.sendKeys(Keys.COMMAND, "v");
    }


    /*--------截图API--------------*/
    @Test
    public void screenShot() throws IOException {
        //通过截图方法进行截图-他会将截图放到mac下的/var/folders/np/77llwrj919df6bd0pn_144n80000gn/T/目录下；
        File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
        System.out.println(screenshotAs.getAbsolutePath());
        //以上打印的地址为：/var/folders/np/77llwrj919df6bd0pn_144n80000gn/T/screenshot8541939302721233942.png
        //拷贝/移动截取的图片到项目下：(通过FileUtils工具类(apache的))
        //移动
        FileUtils.moveFile(screenshotAs, new File("a.png"));
        //拷贝
        FileUtils.copyFile(screenshotAs, new File("b.png"));
    }

    /*------------获取cookies------------------*/
    @Test
    public void cookies() {
        //1.获取当前页面的所有cookies
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie);
        }
        //获取当前页面指定名字的cookie
        Cookie cookie = driver.manage().getCookieNamed("BIDUPSID");
        System.out.println("我是cookie：" + cookie);

        //2.添加cookies
        Cookie newCookie = new Cookie("name", "liucong");
        driver.manage().addCookie(newCookie);

        //3.删除指定名的cookie
        driver.manage().deleteCookieNamed("liucong");

        //4.删除所有的cookie
        driver.manage().deleteAllCookies();
    }

    /*---------------Select类API(下拉菜单)-----------------------*/
    @Test
    public void select() {
        WebElement name = driver.findElementById("selectA");
        //创建Select 类对象将元素传入
        Select select = new Select(name);

        //操作下拉列表中元素
        //根据索引来定位
        select.selectByIndex(2);
        //根据值来定位
        select.selectByValue("sz");
        //根据现实文本来定位
        select.selectByVisibleText("A北京");

    }


    /*-------操作单选框和复选框（就是定位内容然后点击）------------------------*/
    @Test
    public void testRadioButton() throws InterruptedException {
        //操作单选框
        WebElement xja = driver.findElementById("xja");
        xja.click();
        Thread.sleep(3000);
        //断言香蕉按钮是否可选
        assert xja.isSelected();
        assert xja.isEnabled();

        //操作复选框
        WebElement qca = driver.findElementById("qcA");
        qca.click();
        Thread.sleep(2000);
        assert qca.isSelected();
    }

    /*--------Log4j2---------------*/
    @Test
    public void testLog4j2() throws IOException {
        Logger logger = LogUtil.getLogger();

        logger.info("info-------");
        logger.fine("fine-------");
        logger.finer("finer--------");
        logger.config("config-------");
        logger.severe("severe--------");


    }


    /*用例执行顺序*/
    @Test
    public void testOrder(){

    }
}


