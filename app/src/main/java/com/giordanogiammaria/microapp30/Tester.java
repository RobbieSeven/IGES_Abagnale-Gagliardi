package com.giordanogiammaria.microapp30;

/**
 * Created by Roberto on 25/01/2018.
 */

public class Tester {

    public static void main(String[] args) {
        MicroAppGenerator generator = new MicroAppGenerator(null);
        while (generator.hasNextComponent()) {
            generator.nextCompFragment();
        }
    }

}
