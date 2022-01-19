package com.notifier;

import com.notifier.model.Person;
import org.springframework.format.annotation.DateTimeFormat;


public class Test7 {
    final Person person;

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
