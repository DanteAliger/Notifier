package com.notifier;

import com.notifier.model.Person;
import org.junit.jupiter.api.Test;

public class Test1 {

    @Test
    public void testFinal(){

        Test7 test = new Test7(new Person());
        System.out.println(test);
        test.person.setName("123");
        System.out.println(test);
    }

    @Test
    public void testFinal2(){

        Test7 test = new Test7(new Person());
        System.out.println(test);
     // test.person = new Person();
        System.out.println(test);
    }


}
