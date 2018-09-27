package pers.will.lab.basic;

import java.util.HashMap;
import java.util.Map;

public class OperationalSymbol {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        Integer a = 1;
        map.put("a", ++a);
        System.out.println(map);
        map.put("a", a++);
        System.out.println(map);
        map.put("a", a = a + 1);
        System.out.println(map);
        map.put("a", a = 100);
        System.out.println(map);
    }
}
