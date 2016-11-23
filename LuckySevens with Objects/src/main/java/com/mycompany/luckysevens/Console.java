package com.mycompany.luckysevens;

import java.util.Scanner;

public class Console {

    //INTRODUCTION
    public String intro() {

        String intro = ("Hey! You! Yeah... you over there. Let's play LUCKY SEVENS.");

        return intro;
    }

    //Get and verify user, within accepted range.
    public int verifyInput() {

        boolean isValid = false;
        String betInput;
        int initialBet = 0;
        Scanner sc = new Scanner(System.in);

        while (!isValid) {
            try {
                System.out.print("How many dollars do you have? Hand them over: ");
                betInput = sc.nextLine();
                initialBet = Integer.parseInt(betInput);

                if (initialBet > 0) {
                    isValid = true;
                } else {
                    System.out.println("You're broke. You need at least a dollar to play.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("YOU CAN'T PLAY WITH WHATEVER YOU TYPED IN, FOOL! I NEED CASH MONEY.");
            }
        }
        return initialBet;
    }

    //Get results and print them.
    
    public void printResults(int rollNum, int maxBetRoll, int maxBet) {

        System.out.println("You are broke after " + rollNum + " rolls. You should have quit after " + maxBetRoll + " rolls, when you had $" + maxBet + ".");

    }

}// END console
