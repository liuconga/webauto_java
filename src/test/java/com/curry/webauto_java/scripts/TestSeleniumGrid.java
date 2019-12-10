package com.curry.webauto_java.scripts;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/*selenium grid+testng框架的多线程*/
public class TestSeleniumGrid {
    @Test
    public void testChromeV78() throws MalformedURLException, InterruptedException {
        //1.设置参数：浏览器类型，版本，测试平台
        DesiredCapabilities desiredcaps = new DesiredCapabilities();
        desiredcaps.setBrowserName("chrome");
        desiredcaps.setVersion("78.0.3904.70");
        desiredcaps.setPlatform(Platform.LINUX);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), desiredcaps);
        driver.get("http://www.jd.com");
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void testChrome() throws MalformedURLException, InterruptedException {
        //2.还可以通过chromeoptions 它与DesiredCapabilities 都是Capabilities子类
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "78");
        chromeOptions.setCapability("platformName", "Windows 7");
        RemoteWebDriver driver =new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), chromeOptions);
        driver.get("http://www.baidu.com");
        Thread.sleep(2000);
        driver.quit();
    }
@Test
    public void testFirefoxV70() throws InterruptedException, MalformedURLException {
        DesiredCapabilities desiredcaps = new DesiredCapabilities();
        desiredcaps.setBrowserName("firefox");
        desiredcaps.setVersion("70");
        //VISTA中包含win7
        desiredcaps.setPlatform(Platform.MAC);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), desiredcaps);
        driver.get("http://www.jd.com");
        Thread.sleep(2000);
        driver.quit();
    }
@Test
    public void testFirefoxV71() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredcaps = new DesiredCapabilities();
        desiredcaps.setBrowserName("firefox");
        desiredcaps.setVersion("71");
        //windows中包含win7
        desiredcaps.setPlatform(Platform.WINDOWS);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), desiredcaps);
        driver.get("http://www.baidu.com");
        Thread.sleep(2000);
        driver.quit();
    }
}
