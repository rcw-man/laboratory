package pers.will.lab.memory.jmm.reference;

import java.util.Arrays;

public class ParameterRef {

    public class User {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Test {
        private int num;
        private String str;
        private int[] arr;
        private User user;

        public void set(int num, String str, int[] arr, User user) {
            this.num = num;
            this.str = str;
            this.arr = arr;
            this.user = user;
        }

        public void output() {
            log("**对象成员变量**");
            print(num, str, arr, user);
        }

        // 重设成员变量
        public void reset() {
            this.num = -1;
            this.str = "rest";
            this.arr = new int[]{-1, -2};
            this.user = new User();
            user.setName("reset");
        }

        public void parseValue(int num, String str, int[] arr, User user) {
            num = 0;
            str = "parse";
            arr = new int[]{1, 2};
            user = new User();
            user.setName("parse");
            print(num, str, arr, user);
        }

        public void parseRef(int num, String str, int[] arr, User user) {
            arr[0] = -1;
            user.setName("change");
            print(num, str, arr, user);
        }

    }

    public void print(int num, String str, int[] arr, User user) {
        log("num: " + num + ", str: " + str + ", arr: " + arr.toString() + "-" + arr.hashCode() + "-" + Arrays.toString(arr) + ", user: " + user.toString() + "-" + user.getName());
    }

    public void log(String desc) {
        System.out.println(System.currentTimeMillis() + "\t" + desc);
    }

    public void tag(String desc) {
        System.out.println("\n" + desc);
    }


    public static void main(String[] args) {
        // 初始化
        ParameterRef pr = new ParameterRef();
        ParameterRef.Test t = pr.new Test();

        int num = 10;
        String str = "hello";
        int[] arr = {0, 1};
        ParameterRef.User user = pr.new User();
        user.setName("main");

        pr.tag("【打印原始值】");
        pr.print(num, str, arr, user);

        pr.tag("【原始值赋值到对象成员变量，打印成员变量】");
        t.set(num, str, arr, user);
        t.output();
        // 一样

        pr.tag("【原始值不变，对象方法内改变入参，比较入参变化后，原始值是否变化】");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                pr.log("**改变引用**");
                t.parseRef(num, str, arr, user);
            } else if (i == 1) {
                pr.log("**改变值(new)**");
                t.parseValue(num, str, arr, user);
            } else {
                return;
            }
            pr.log("**当前原始值**");
            pr.print(num, str, arr, user);
        }
        // 改变引用后，一起变化
        // 改变值后，原始值不变，方法内参数变为新对象

        pr.tag("【调整原始值的引用，比较原始值、对象成员变量】");
        arr[0] = -100;
        user.setName("src user changed");
        pr.log("**当前原始值**");
        pr.print(num, str, arr, user);
        t.output();
        // 原始值和对象成员变量一起变化

        pr.tag("【调整原始值(new)，比较原始值、对象成员变量】");
        num = num * 11;
        str = "src changed";
        arr = new int[]{1, 2, 3};
        user = pr.new User();
        user.setName("src new user");
        pr.log("**当前原始值**");
        pr.print(num, str, arr, user);
        t.output();
        // 原始值变化，对象成员变量未变

        pr.tag("【调整对象成员变量，比较原始值、对象成员变量】");
        t.reset();
        pr.log("**当前原始值**");
        pr.print(num, str, arr, user);
        t.output();
        // 对象成员变量变化，原始值未变

        /** Java的参数传递都是传递值（副本），但值是栈内存的值，这意味着两种情况（传值还是传址）：
         * 1.基类型，栈中存放的是值，那么传递的是值的副本；后续的任何改变都只在其作用域内生效
         * 2.对象，栈中存放的是堆内存地址，那么传递的就是堆内存地址的副本；后续的改变都可以根据地址追溯到堆，除非堆地址被改变
         * 参考文章：https://www.cnblogs.com/lixiaolun/p/4311863.html
         */

    }
}
