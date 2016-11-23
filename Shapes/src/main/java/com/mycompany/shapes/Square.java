package com.mycompany.shapes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Square extends Shapes {

    @Override
    protected double area() {
        Double side = null;
        Double area = null;
        try {
            System.out.println("Enter length of single side: ");
            side = sc.nextDouble();
            area = side * side;

        } catch (InputMismatchException ex) {
        }
        return area;
    }

    @Override
    protected double perimeter() {
        Double side = null;
        Double perimeter = null;
        try {
            System.out.println("Enter length of single side: ");
            side = sc.nextDouble();
            perimeter = side * 4;
        } catch (InputMismatchException e) {
        }
        return perimeter;
    }

}
