package pers.will.lab.basic;

public class Regex {

    public static void main(String[] args) {
        String test = "123abc./\\[]_?#$%^&*";
        System.out.println(test.replaceAll("/", "."));
        System.out.println(test.replaceAll("/", "\\."));
        System.out.println(test.replaceAll("/", "[.]"));
    }
}
