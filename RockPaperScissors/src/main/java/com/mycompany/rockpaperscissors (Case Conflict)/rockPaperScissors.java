
/**
 *
 * @author Greg Svoboda
 */
package com.mycompany.rockPaperScissors;


import java.util.Scanner; 

public class rockPaperScissors {
static Scanner sc = new Scanner(System.in);
   
//*****************************MAIN******************************    

            
            
public static void main (String[] args){  
    
    int userThrow;
    int botThrow;
    boolean playAgain = true;
    System.out.println("Welcome to Rock, Paper, Scissors");
    
    while (playAgain){
    userThrow = startGame();
    delayGame(2000);
    botThrow = getBotThrow();
    playAgain = determineWinner ((int) userThrow, (int) botThrow);

  System.out.println("");
    }
}
//*****************************END OF MAIN***********************   


//*****************************BEGIN METHODS*********************  

//METHOD: GREET USER, GET USER INPUT, PARSE TO INT, VERIFY VALID, RETURN

public static int startGame() {
        boolean isValid = false;
        int userInput = 0;
        String userInputString;



        while (!isValid){

            try {
                System.out.println("Prepare yourself!");
                System.out.println("Choose your weapon from the selections below:");
                System.out.println("ROCK (1)            PAPER (2)            SCISSORS (3)");
                userInputString = sc.nextLine();
                userInput = Integer.parseInt(userInputString);
                if (userInput==1||userInput==2||userInput==3) {
                    isValid = true;
                }
                
            } catch (NumberFormatException nfe) {
                System.out.println("You have played with an unknown hand gesture.");
            }
        }
        return userInput;
    }


//METHOD: DELAY GAME

public static void delayGame(int requestedDelay) {

    try {
    Thread.sleep(requestedDelay); //1000 milliseconds = 1 second
}   catch(InterruptedException ex) {
    Thread.currentThread().interrupt();
}
}
   
//METHOD: GET RANDOM BOT THROW 
public static int getBotThrow() { 
   
    int botThrow;
         
botThrow = (int) (Math.random() * 3) + 1;

    
    return botThrow;
}

//METHOD: DETERMINE A WINNER, PRINT RESULTS TO SCREEN

public static boolean determineWinner(int userThrow, int botThrow) 
{

    int u = userThrow;
    int b = botThrow;
    int gameEnd = 0;
    String userInputString;
    int userInput;

        if ((u==1) && (b==1)) {gameEnd=1;}
        if ((u==1) && (b==2)) {gameEnd=2;}
        if ((u==1) && (b==3)) {gameEnd=3;}
        if ((u==2) && (b==1)) {gameEnd=4;}
        if ((u==2) && (b==2)) {gameEnd=5;}
        if ((u==2) && (b==3)) {gameEnd=6;}
        if ((u==3) && (b==1)) {gameEnd=7;}
        if ((u==3) && (b==2)) {gameEnd=8;}
        if ((u==3) && (b==3)) {gameEnd=9;}
    
        switch (gameEnd) 
            {
    
            case 1:
                System.out.println("Your ROCKS smash together...");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("...and you TIE.");
                     break;
                     
            case 2:
                        System.out.println("Your ROCK is covered by his PAPER.");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("You LOSE.");

                     break;
                     
            case 3:
                        System.out.println("Your ROCK crushes his SCISSORS!");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("You WIN!");
           
                     break;
                     
            case 4:
                        System.out.println("Your PAPER covers his ROCK.");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("You WIN!");
    
                     break;
                     
            case 5:
                        System.out.println("Your PAPER flops together with his PAPER...");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("...and you TIE.");
                     break;
                     
            case 6:
                        System.out.println("Your PAPER is sliced by his SCISSORS!");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("You LOSE.");
                     break;
                     
            case 7:
                        System.out.println("Your SCISSORS are destroyed by his ROCK!");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("You LOSE.");

                     break;
                     
            case 8:
                        System.out.println("Your SCISSORS slice his PAPER.");
                try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("You WIN!");
  
                     break;
                     
            case 9:
                        System.out.println("Your SCISSORS clink together with his Scissors harmlessly.");
                try {
                Thread.sleep(1500); //1000 milliseconds = 1 second
                }
                catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                System.out.println("You tie.");
                     break;
                }
                System.out.println("Do you want to play again? 1 = YES and 2 = NO");
                
                userInputString = sc.nextLine();
                userInput = Integer.parseInt(userInputString);
                if (userInput==1 ){
                    return true;
                }
                if (userInput==2) {
                    return false;
                }
                return false;
} 
















}  //END OF GAME    



