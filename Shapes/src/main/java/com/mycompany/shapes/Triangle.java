package com.mycompany.shapes;

import java.util.InputMismatchException;

public class Triangle extends Shapes {

    @Override
    protected double area() {
        Double area = null;
        Double base = null;
        Double height = null;

        try {
            System.out.println("Enter length of the triangle's BASE: ");
            base = sc.nextDouble();
            System.out.println("Enter the HEIGHT of the triangle ");
            height = sc.nextDouble();
            
            area = .5*(base*height);

        } catch (InputMismatchException e) {

        }
        return area;

    }

    @Override
    protected double perimeter() {
        Double side = null;
        Double perimeter = null;

        try {
            System.out.println("Enter length of one of the triangle's sides: ");
            side = sc.nextDouble();
            perimeter = (side * 3);

        } catch (InputMismatchException e) {

        }
        return perimeter;
    }
}
