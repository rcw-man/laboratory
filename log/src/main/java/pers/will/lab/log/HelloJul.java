package pers.will.lab.log;

import java.util.logging.Logger;

public class HelloJul {
    public static void main(String[] args) {
        // Java自带日志记录器
        Logger logger
                = Logger.getLogger(HelloJul.class.getName());
        logger.info("hello jul");
    }
}
