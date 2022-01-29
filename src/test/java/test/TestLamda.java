package test;

import org.junit.jupiter.api.Test;


public class TestLamda {

    @Test
    public void testLamda(){
    Function min = (x, y) -> Math.min(x,y);
    Function max = (x,y) -> Math.max(x,y);
    System.out.println(min.function(345,23));
    System.out.println(max.function(345,23));
    }
    @Test
    public void testAnonimClass(){
        Function max = new Function(){
            @Override
            public int function(int x, int y) {
                return Math.max(x,y);
            }
        };
        System.out.println(max.function(400,123));
    }

}
