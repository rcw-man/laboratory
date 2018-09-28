package pers.will.lab.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloSlf4j {

    private static Logger logger = LoggerFactory.getLogger(HelloSlf4j.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            logger.error("slf4j- error - abc" + i);
            logger.info("slf4j- info - abc" + i);
        }
    }
}
