package com.taylor.stocks.test;

import org.junit.jupiter.api.Test;

public class FizzBuzz {


    @Test
    public void fizzBuzz(){
        for(int i = 0; i <= 100; i++){
            if(i % 3 == 0 && i % 5 == 0){
                System.out.println("FizzBuzz");
            } else if(i % 5 == 0) {
                System.out.println("Buzz");
            } else if(i % 3 == 0){
                System.out.println("Fizz");
            } else {
                System.out.println(i);
            }
        }
    }
}
