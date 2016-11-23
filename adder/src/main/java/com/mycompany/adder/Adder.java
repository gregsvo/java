
package com.mycompany.adder;

import java.util.Scanner;

/**
 * @author Greg Svoboda
 */

public class Adder {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        String userInput1 = "";
        String userInput2 = "";
        int operand1;
        int operand2;
        
        //Ask the user for input
        
        System.out.println("Please enter the first number to be added:");
        
        userInput1 = sc.nextLine();
        operand1 = Integer.parseInt(userInput1);
        
        
        System.out.println("Please enter the second number to be added:");
        
        userInput2 = sc.nextLine();
        operand2 = Integer.parseInt(userInput2);
        
        
        //METHOD CALL
        
        int operand1PlusOperand2 = getInput((int) operand1, (int) operand2);
        
        System.out.println("The result of adding is: " +operand1PlusOperand2);
        
    } //END OF MAIN
    
    
 
    public static int getInput(int operand1, int operand2) {
    
        int operand1PlusOperand2;
        
        
        operand1PlusOperand2 = operand1 + operand2;
        
        return operand1PlusOperand2;
        
        
    }
    
}// END OF ADDER
