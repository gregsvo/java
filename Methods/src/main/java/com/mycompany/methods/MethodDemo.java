package com.mycompany.methods;

import java.util.Scanner;

public class MethodDemo {
    
    public static void main(String[] args) {

        final int MAX = 10;
        final int MIN = 0;
        
       printHi();
       
       double piValue = calculatePi();
       
       int val = add(1, 2);
       int val2 = add(1,15);
       
       int min = 11;
       int max = 45;
       
       boolean isValid = isValidInput(5, MIN, MAX);
       
               String userInput = getUserInput("Enter your name)");
            int userInput2 = getUserInputInt("Enter an int value");
            
            double piPlus1 = getPiplusOne();

    }
    
    public static double getPiplusOne(){
    
        return 4.14;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void printHi() {
        System.out.println("hi");
    }
   
    public static double calculatePi() {
        int day = 4;
        
        switch (day) {
            case 4:
                    return 3.14159;
            case 5:         
                    return 3.14159;
        }
        
        
        return 3.14169;       
}
    
    public static int add1And1() {
        return 1 +1;
    }
    
    public static int add1And2() {
        return 1 +2;
    }
    
    public static int add(int x, int y){
        return x + y;
    }
    
    public static boolean isValidInput(int input, int min, int max) {
        
        if input >= min && input <= max) {
            return true;
        } else {
            return false;
        }
        
        public static String getUserInput(String message) {
            
            Scanner sc = new Scanner(System.in);
            
            return sc.nextLine();
        }
        }
        

        public static int getUserInputInt(String message) {

        System.out.println(message);

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        int intInput = Integer.parseInt(input);

        return intInput;
}

}

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
    
  
