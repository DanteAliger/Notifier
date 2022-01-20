package com.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Class {

    @Autowired // внедрит класс2 в класс1
    private Class2 class2;

//    @Qualifier("userService1")
    @Autowired
    private UserService userService1;

    @Autowired
    private List<UserService> services;

//    @Autowired //2 способ
//    public Class(Class2 class2) {
//        this.class2 = class2;
//    }
}
