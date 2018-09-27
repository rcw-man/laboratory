package pers.will.lab;

import java.util.Date;

public class TestClassLoader {

    public void say() {
        System.out.println(this.getClass().getClassLoader().toString());
        System.out.println("Hello World");
    }

    @Override
    public String toString() {
        System.out.println(this.getClass().getClassLoader().toString());
        return " [Test Class]" + new Date().toString();
    }
}
