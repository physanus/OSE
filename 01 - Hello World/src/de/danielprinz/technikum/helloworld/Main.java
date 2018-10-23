package de.danielprinz.technikum.helloworld;

/**
 * Created by el17x002 on 25.09.2018.
 */
public class Main {

    // Übung vom 24.09.2018

    public static void main(String[] args) {

        if(args.length == 0)
            System.out.println("Hello world!");
        else
            System.out.println("Hello, " + args[0]);


        int result = Calculator.add(5, 7);
        System.out.println(result);

        Calculator calculator = new Calculator();
        calculator.add(5);
        calculator.add(7);
        calculator.add(5);
        System.out.println(calculator.getResult());

    }

}
