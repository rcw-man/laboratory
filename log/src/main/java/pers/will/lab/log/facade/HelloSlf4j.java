package pers.will.lab.log.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloSlf4j {

    /**
     * SLF4J日志API
     */
    private static final Logger logger = LoggerFactory.getLogger(HelloSlf4j.class);

    public static void main(String[] args) {
        // SLF4J会根据配置的适配器，选择相应的日志框架；如找不到适配器则报错，找到多个适配器则会选择一个默认适配器
        for (int i = 0; i < 10; i++) {
            logger.error("slf4j- error - abc" + i);
            logger.info("slf4j- info - abc" + i);
        }
    }
}
