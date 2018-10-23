package de.danielprinz.technikum.kreisarithmetik;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by el17x002 on 25.09.2018.
 */
public class CircleTest {

    private Circle circle;
    private static final double DELTA = 0.000000001;

    @Before
    public void setUp() throws Exception {
        this.circle = new Circle(0);
    }

    @Test
    public void testSetRadius() throws Exception {
        this.circle.setRadius(5);
        assertEquals(5, this.circle.getRadius(), 0);
    }

    @Test
    public void testGetDiameter() throws Exception {

    }

    @Test
    public void testGetArea() throws Exception {

    }

    @Test
    public void testGetPerimeter() throws Exception {

    }
}