package com.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Class {
    public int fuck(String s){
        return 1;
    }

    public int fuck(String s, String s2){
        return 2;
    }

    public static String sumString(String s1, String s2){
        return s1.concat(s2);
    }


}
