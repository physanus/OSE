package de.danielprinz.technikum.kreisarithmetik;

/**
 * Created by el17x002 on 25.09.2018.
 */
public class Circle {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return this.radius * 2;
    }


    /**
     * Calculates the area (Fläche) of the circle
     * @return The area
     */
    public double getArea() {
        // A = PI * r^2
        return Math.PI * this.radius * this.radius;
    }

    /**
     * Calculates the perimeter (Umfang) of the circle
     * @return The area
     */
    public double getPerimeter() {
        // U = 2 * PI * r
        return 2 * Math.PI * this.radius;
    }


    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
