
package com.mycompany.primefinder;



//@author Greg and Larry

import java.util.Scanner;


    public class primeFinder {
       
static Scanner sc = new Scanner(System.in);

        
        
    public static void main(String[] args) {

        int number = 0;
        int x;

        number = checkUserInput();
        System.out.println("Let's dig " + number + " for primes!");

        
        for (x=1; x < number; x++){
        findPrimes(x);
        }

    }
    //BEGIN METHODS ***********************************

    //METHOD: Get User Number to Factor       
    
public static int checkUserInput() {
        boolean isValid = false;
        int userInput = 0;
        String userInputString;

        while ((!isValid) || (userInput <= 0)) {

            try {
                System.out.println("What number (greater than zero) would you like to dig for prime numbers?");
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
    
    //METHOD: PRINT NUMBRS, NOTIFY WHICH ARE PRIME
    
        public static void findPrimes(int number){
        boolean isPrime = false;
        int temp;
    
       for (int i = 2; i <= number / 2; i++) {
           temp = number % i;
           if (temp == 0) {
               isPrime = false;
               break;
           }
       }
       
       //If isPrime is true then the number is prime else not
       
       if (isPrime) {
           System.out.println(number + " is a Prime Number");
      
       }
       
    }
}
