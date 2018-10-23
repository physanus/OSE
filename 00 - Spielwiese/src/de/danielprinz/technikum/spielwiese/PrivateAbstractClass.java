package de.danielprinz.technikum.spielwiese;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class PrivateAbstractClass extends A {

    public int getNumberPrivate() {
        System.out.println("triggered");
        return super.getNumber();
    }

}
