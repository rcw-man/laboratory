package pers.will.lab.log.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringLogger {

    private static final Logger logger = LoggerFactory.getLogger(HelloSlf4j.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.start();
        context.getBean(HelloSpringLogger.class);

        logger.error("slf4j- error - abcd");
        logger.info("slf4j- info -abcd");
        // spring默认是使用jcl打印日志的，可以通过jcl-over-slf4j快速切换到slf4j
    }
}
