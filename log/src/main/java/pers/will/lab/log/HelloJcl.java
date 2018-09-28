package pers.will.lab.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloJcl {
    public static void main(String[] args) {
        // Apache JCL日志API
        Log log = LogFactory.getLog(HelloJcl.class);
        log.error("error message jcl");
    }
}
