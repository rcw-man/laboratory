package pers.will.lab.log.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloLog4j2 {

    /**
     * Log4j2日志记录器
     */
    private static final Logger logger = LogManager.getLogger(HelloLog4j2.class);

    public static void main(String[] args) {
        logger.info("hello Log4j2");
    }
}
