package pers.will.lab.log.impl;

import org.apache.log4j.Logger;

public class HelloLog4j {

    /**
     * Log4j日志记录器
     */
    private static final Logger logger = Logger.getLogger(HelloLog4j.class);

    public static void main(String[] args) {
        logger.info("hello Log4j");
        // log4j可通过log4j-over-slf4j，转换为slf4j输出，以达到快速切换日志框架的目的；
        // 但需注意该适配器、不能与slf4j-log4j12并存，会产生相互委托导致类装载失败
    }
}
