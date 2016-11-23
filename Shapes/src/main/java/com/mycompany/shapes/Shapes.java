
package com.mycompany.shapes;

import java.util.Scanner;


public abstract class Shapes {
    protected Scanner sc = new Scanner(System.in);
    
    protected String color;
    
    protected abstract double area();
    
    protected abstract double perimeter();
        
    
    
    
}
