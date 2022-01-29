package com.notifier;

import com.notifier.model.Person;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    @Test
    public void testFinal() {

        Test7 test = new Test7(new Person());
        System.out.println(test);
        test.person.setName("123");
        System.out.println(test);
    }

    @Test
    public void testFinal2() {

        Test7 test = new Test7(new Person());
        System.out.println(test);
        // test.person = new Person();
        System.out.println(test);
    }

    @Test
    public void test3() {
        String s = "666287438645820421392548910243`123123mnn";
        System.out.println(sort(s));
    }

    public Map<Character, Integer> sort(String s) {
        Map<Character, Integer> mass = new HashMap<>();
        for (Character symbol : s.toCharArray()) {
            if (!mass.containsKey(symbol)) {
                mass.put(symbol, 1);
            } else {
                mass.put(symbol, mass.get(symbol) + 1);
            }
        }
        return mass;
    }


}
