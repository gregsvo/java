
package com.mycompany.shapes;

public class Rectangle extends Shapes {

    @Override
    protected double area() {     
        System.out.println("What is the length of the rectangle");
        String userInputLength = sc.nextLine();
        Double length = Double.parseDouble(userInputLength);
        
        
        System.out.println("What is the height of the rectangle");
        String userInputHeight = sc.nextLine();
        Double width = Double.parseDouble(userInputHeight);
        Double area = length * width;
        
        return area;
        
    }
        

    @Override
    protected double perimeter() {
        System.out.println("What is the length of the rectangle");
        String userInputSide1 =  sc.nextLine();
        Double side1 = Double.parseDouble(userInputSide1);
        
        System.out.println("What is the height of the rectangle");
        String userInputSide2 = sc.nextLine();
        Double side2 = Double.parseDouble(userInputSide2);
        
        Double perimeter = 2 * (side1 + side2);
        
        return perimeter;
    }
    
}
