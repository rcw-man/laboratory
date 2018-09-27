package pers.will.javaagent;

public class APMAgentTest {

    public static void main(String[] args) {
        System.out.println("** This is agent test main.");
        SourceClass source = new SourceClass();
        source.sayHello();
        source.write();
    }
}
