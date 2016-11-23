
package com.mycompany.factorizor;

import java.util.Scanner;

public class GetInput {
    
static Scanner sc = new Scanner(System.in);        
    
    public int getInput(){
        
    
        boolean isValid = false;
        int userInput = 0;
        String userInputString;

        while ((!isValid) || (userInput <= 0)) {

            try {
                System.out.print("Enter a number (greater than zero) you would like to factor: ");
                userInputString = sc.nextLine();
                userInput = Integer.parseInt(userInputString);
                if (userInput >= 0) {
                    isValid = true;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("You have not entered a valid number.");
            }
        }
        return userInput;
    }
}