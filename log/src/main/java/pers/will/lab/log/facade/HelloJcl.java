package pers.will.lab.log.facade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloJcl {

    /**
     * JCL日志API
     */
    private static final Log logger = LogFactory.getLog(HelloJcl.class);

    public static void main(String[] args) {
        logger.error("error message jcl");
        // 自动检索依赖中的日志框架，测试中发现优先使用了log4j、其次是jul
        // jcl可通过jcl-over-slf4j，转换为slf4j输出，以实现快速切换
    }
}
