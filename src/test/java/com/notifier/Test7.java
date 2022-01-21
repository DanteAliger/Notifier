package com.notifier;

import com.notifier.model.Person;
import org.springframework.format.annotation.DateTimeFormat;


public class Test7 {

      final Person person;

//    Operation operation;
//    operation = (x,y)->x+y;
//    int result = operation.calculate(10, 20);
//        System.out.println(result); //30

    @Override
    public String toString() {
        return "Test7{" +
                "person=" + person +
                '}';
    }

    public Test7(Person person) {
        this.person = person;
    }
}
