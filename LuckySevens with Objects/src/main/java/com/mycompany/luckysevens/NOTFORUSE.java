package com.mycompany.luckysevens;

import java.util.Scanner;

 //@author Greg Svoboda

public class NOTFORUSE {
    
     public static void main(String[] args) {
 
//Variables placed for overarching scope
        String betInput = "";
        double die1, die2, dieSumDouble;
        int initialBet, remainingBet, maxBet, roll, maxBetroll;

//Methods that controlthe game:

        initialBet = verifyInput();
        maxBet = initialBet;
        remainingBet = initialBet;
        hornSwoggle(remainingBet);
        
}//END OF MAIN METHOD

//BEGIN METHODS

//METHOD: VERIFY INPUT, PARSE TO INT, RETURN TO MAIN     
        public static int verifyInput(){
        
        boolean isValid = false;
        String betInput;
        int initialBet = 0;
        Scanner sc = new Scanner(System.in);
        
         while (!isValid) {
            try {
                System.out.println("How many dollars do you have?");
                betInput = sc.nextLine();
                initialBet = Integer.parseInt(betInput);
                
                if (initialBet > 0 )
                    isValid = true;
                else
                    System.out.println("You're broke. You need at least a dollar to play.");
            }
            catch (NumberFormatException nfe) {
                System.out.println("YOU CAN'T PLAY WITH WHATEVER YOU TYPED IN, FOOL! I NEED CASH MONEY.");
            }
        }   
         return initialBet;
        }
 
//METHOD: ROLL DICE, COMBINE FOR DICE TOTAL
//METHOD: KEEP TRACK OF ROLLS, MONEY, MAXIMUM AMOUNT, END GAME WHEN NO MONEY
        public static void hornSwoggle(int remainingBet) {
           
            int roll= 0;
            int maxBetroll = roll;
            int die1, die2, dieSum;
            int maxBet=remainingBet;
            int maxRoll = roll;
            
           
            while (remainingBet>=0){
                roll++;
                die1 = 1+(int)(Math.random() * ((6 - 1) + 1));
                die2 = 1+(int)(Math.random() * ((6 - 1) + 1));
                dieSum = die1 + die2;

                   if (dieSum == 7){
                   remainingBet += 4;

                        if (remainingBet > maxBet){
                        maxBet = remainingBet;
                        maxBetroll = roll;
                        }
                   }

                   else{
                   remainingBet -= 1;
                   }
        }
        
        System.out.println("You are broke after " + roll + " rolls.");
        System.out.println("You should have quit after " + maxBetroll + " rolls, when you had $" + maxBet);
        }
}
//END!