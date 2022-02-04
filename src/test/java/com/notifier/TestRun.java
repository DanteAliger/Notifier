package com.notifier;


import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TestRun extends Test7{


    @Test
    public void run ()
    {
        Test7 test = new Test7();
        System.out.println(test.s);
    }

    @Override
    public String toString() {
        return null;
    }



    abstract class op{
        String b = "sdfsdf";
        public abstract void opp();
         void print(){
            System.out.println("Undefined printable");
        }

    }


}
