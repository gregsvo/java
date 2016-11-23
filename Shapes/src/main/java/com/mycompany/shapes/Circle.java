
package com.mycompany.shapes;

import java.util.InputMismatchException;


public class Circle extends Shapes {

    @Override
    protected double area() {
        Double radius = findRadius();
        Double area = Math.PI * (radius * radius);

        return area;

    }

    @Override
    protected double perimeter() {
        Double radius = findRadius();
        Double perimeter = 2 * Math.PI * radius;

        return perimeter;

    }

    private Double findRadius() {
        Double radius = null;
        try {
            System.out.println("What is the radius length");
            radius = sc.nextDouble();
        } catch (InputMismatchException ex) {
            System.out.println("Number please!!!");
            sc.next();
        }

        return radius;
    }

}
