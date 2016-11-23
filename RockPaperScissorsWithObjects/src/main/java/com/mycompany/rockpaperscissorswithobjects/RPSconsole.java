package com.mycompany.rockpaperscissorswithobjects;

import java.util.Scanner;

public class RPSconsole {

    Scanner sc = new Scanner(System.in);
//Print introduction to app

    public void intro() {
        System.out.println(" Welcome to Rock, Paper, Scissors");
    }

//Get and verify from app user input for user throw choice
    public int startGame() {
        boolean isValid = false;
        int userInput = 0;
        String userInputString;

        while (!isValid) {

            try {
                System.out.println("Prepare yourself!");
                System.out.println("Choose your weapon from the selections below:");
                System.out.println("ROCK (1)            PAPER (2)            SCISSORS (3)");
                userInputString = sc.nextLine();
                userInput = Integer.parseInt(userInputString);

                if (userInput == 1) {
                    System.out.println("You throw ROCK.");
                }
                if (userInput == 2) {
                    System.out.println("You throw PAPER.");
                }
                if (userInput == 3) {
                    System.out.println("You throw ROCK.");
                }
                if (userInput == 1 || userInput == 2 || userInput == 3) {
                    isValid = true;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("You have played with an unknown hand gesture.");
            }
        }
        return userInput;
    }

//get user and bot throws from app for printout of winner    
    public void gameResult(int userThrow, int botThrow) {

        int u = userThrow;
        int b = botThrow;
        int gameEnd = 0;

        if ((u == 1) && (b == 1)) {
            gameEnd = 1;
        }
        if ((u == 1) && (b == 2)) {
            gameEnd = 2;
        }
        if ((u == 1) && (b == 3)) {
            gameEnd = 3;
        }
        if ((u == 2) && (b == 1)) {
            gameEnd = 4;
        }
        if ((u == 2) && (b == 2)) {
            gameEnd = 5;
        }
        if ((u == 2) && (b == 3)) {
            gameEnd = 6;
        }
        if ((u == 3) && (b == 1)) {
            gameEnd = 7;
        }
        if ((u == 3) && (b == 2)) {
            gameEnd = 8;
        }
        if ((u == 3) && (b == 3)) {
            gameEnd = 9;
        }

        switch (gameEnd) {

            case 1:
                System.out.println("Your ROCKS smash together...");

                System.out.println("...and you TIE.");
                break;

            case 2:
                System.out.println("Your ROCK is covered by his PAPER.");

                System.out.println("You LOSE.");

                break;

            case 3:
                System.out.println("Your ROCK crushes his SCISSORS!");

                System.out.println("You WIN!");

                break;

            case 4:
                System.out.println("Your PAPER covers his ROCK.");

                System.out.println("You WIN!");

                break;

            case 5:
                System.out.println("Your PAPER flops together with his PAPER...");

                System.out.println("...and you TIE.");
                break;

            case 6:
                System.out.println("Your PAPER is sliced by his SCISSORS!");

                System.out.println("You LOSE.");
                break;

            case 7:
                System.out.println("Your SCISSORS are destroyed by his ROCK!");

                System.out.println("You LOSE.");

                break;

            case 8:
                System.out.println("Your SCISSORS slice his PAPER.");

                System.out.println("You WIN!");

                break;

            case 9:
                System.out.println("Your SCISSORS clink together with his Scissors harmlessly.");

                System.out.println("You tie.");
                break;
        }

    }

//ask user to play again, send boolean to app   
    public boolean playAgain() {
        boolean goodInput = false;
        boolean playAgain = true;

        while (!goodInput) {
            System.out.println("Do you want to play again? 1 = YES and 2 = NO");

            try {
                String userInputString = sc.nextLine();

                int userInput = Integer.parseInt(userInputString);

                if (userInput == 1) {
                    playAgain = true;
                    goodInput = true;
                }
                if (userInput == 2) {
                    playAgain = false;
                    goodInput = true;
                }

            } catch (NumberFormatException nfe) {
                System.out.println("You have played with an unknown hand gesture.");
            }
        }
        return playAgain;
    }
}
