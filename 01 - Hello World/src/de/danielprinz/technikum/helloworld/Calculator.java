package de.danielprinz.technikum.helloworld;

/**
 * Created by el17x002 on 25.09.2018.
 */
public class Calculator {

    private int result;

    public Calculator() {
        this.result = 0;
    }
    public Calculator(int value) {
        this.result = value;
    }


    public Calculator add(int a) {
        this.result += a;
        return this;
    }

    public int getResult() {
        return this.result;
    }

    public static int add(int a, int b) {
        return a + b;
    }


}
