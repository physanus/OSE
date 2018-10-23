package de.danielprinz.technikum.spielwiese;

import java.lang.reflect.Field;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("\n=====================================");
        System.out.println("============ CASUAL CASE ============");
        System.out.println("=====================================\n");

        PrivateStandaloneClass privateStandaloneClass = new PrivateStandaloneClass();
        System.out.println(privateStandaloneClass.getNumber());

        try {
            Field f1 = privateStandaloneClass.getClass().getDeclaredField("number");
            f1.setAccessible(true);
            f1.set(privateStandaloneClass, 2);
            System.out.println(privateStandaloneClass.getNumber());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        System.out.println("\n=====================================");
        System.out.println("=========== ABSTRACT CASE ===========");
        System.out.println("=====================================\n");

        PrivateAbstractClass privateAbstractClass = new PrivateAbstractClass();
        System.out.println(privateAbstractClass.getNumberPrivate());
        System.out.println(privateAbstractClass.getNumber());

        try {
            Field f1 = privateAbstractClass.getClass().getSuperclass().getDeclaredField("number");
            f1.setAccessible(true);
            f1.set(privateAbstractClass, 4);
            System.out.println(privateAbstractClass.getNumberPrivate());
            System.out.println(privateAbstractClass.getNumber());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
