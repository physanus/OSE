package de.danielprinz.technikum.kreisarithmetik;

/**
 * Created by el17x002 on 25.09.2018.
 */
public class Main {

    // Übung vom 25.09.2018

    public static void main(String[] args) {

        Circle circle = new Circle(5.5);

        System.out.println("circle:   " + circle);
        System.out.println("diameter: " + circle.getDiameter());
        System.out.println("perimter: " + circle.getPerimeter());
        System.out.println("area:     " + circle.getArea());

    }

}
