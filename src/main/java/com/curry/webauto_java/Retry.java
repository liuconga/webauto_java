package com.curry.webauto_java;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int count;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count == 2) {

            return false;
        } else {
            System.out.println("重试啦");
            count++;
            return true;
        }
    }
}
