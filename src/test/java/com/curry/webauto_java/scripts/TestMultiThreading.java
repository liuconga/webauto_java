package com.curry.webauto_java.scripts;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestMultiThreading {
    @Test
    @Parameters({"browser1"})
    public void testBrowser1(String browser) throws InterruptedException {
        if (browser.equals("chrome")) {
            ChromeDriver chromeDriver = new ChromeDriver();
            chromeDriver.get("http://www.baidu.com");
            Thread.sleep(2000);
            chromeDriver.quit();
        } else if (browser.equals("firefox")) {
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.get("http://www.baidu.com");
            Thread.sleep(2000);
            firefoxDriver.quit();
        }
    }
    @Test
    @Parameters({"browser2"})
    public void testBrowser2(String browser) throws InterruptedException {
        if (browser.equals("chrome")) {
            ChromeDriver chromeDriver = new ChromeDriver();
            chromeDriver.get("http://www.baidu.com");
            Thread.sleep(2000);
            chromeDriver.quit();
        } else if (browser.equals("firefox")) {
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            firefoxDriver.get("http://www.baidu.com");
            Thread.sleep(2000);
            firefoxDriver.quit();
        }
    }

    @Test
    public void testBrowser() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new TestMultiThreading().testBrowser1("chrome");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new TestMultiThreading().testBrowser1("firefox");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /*用java多线程实现用例并发执行*/
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new TestMultiThreading().testBrowser1("chrome");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new TestMultiThreading().testBrowser1("firefox");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
