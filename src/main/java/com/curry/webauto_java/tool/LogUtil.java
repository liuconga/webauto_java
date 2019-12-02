package com.curry.webauto_java.tool;

import java.io.IOException;
import java.util.logging.*;

public class LogUtil {
    public static Logger logger=null;
    public static Logger getLogger() throws IOException {
        if (logger ==null){
            logger= Logger.getLogger("webauto_java");
            //设置总级别-否则都不显示
            logger.setLevel(Level.ALL);

            //输出到控制台------------
            ConsoleHandler consoleHandler = new ConsoleHandler();
            //设置控制台级别-跟总级别都要设置
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);

            //输出到日志文件中-----------
            FileHandler fileHandler = new FileHandler("webauto.log");
            //设置输出格式：SimpleFormatter 和 XMLFomatter(默认)
            fileHandler.setFormatter(new SimpleFormatter());
            //设置输出到文件的日志级别；
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

            //不使用父控制器的输出；如果不设置-控制台会输出父控制器的（造成重复）
            logger.setUseParentHandlers(false);

        }

        return logger;
    }

}