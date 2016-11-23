
package com.mycompany.whatisyournumber;

/**
 * @author Greg Svoboda
 */

import java.util.Scanner;
public class WhatIsYourNumber {

static Scanner sc = new Scanner(System.in);

public static void main (String[] args){
        
    boolean isValid = false;
        String userInputString = "";
        int userInput = 0;
        
        while (!isValid){

            try {
                System.out.print("Please give me an integer, greater than zero: ");
                userInputString = sc.nextLine();
                userInput = Integer.parseInt(userInputString);
                if (userInput>0) {
                    isValid = true;
                    System.out.print("Thanks! You gave me: " + userInput + " Pat will most certainly be impressed.");
                }
               
            } catch (NumberFormatException nfe) {
                System.out.println("I SAID AN INTEGER THAT WAS GREATER THAN ZERO, FOOL!");
            }
        }

            for (int x=0; x<userInput+1; x++){
               System.out.println(x);
               
            }
            
            System.out.println("THANK YOU FOR PLAYING!");
            
}//END OF MAIN
}//END OF PROGRAM
