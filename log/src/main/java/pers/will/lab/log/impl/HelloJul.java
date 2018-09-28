package pers.will.lab.log.impl;

import java.util.logging.Logger;

public class HelloJul {

    /**
     * Java自带日志记录器
     */
    private static final Logger logger = Logger.getLogger(HelloJul.class.getName());

    public static void main(String[] args) {
        logger.info("hello jul");
    }
}
