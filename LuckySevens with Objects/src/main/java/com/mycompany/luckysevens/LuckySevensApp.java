package com.mycompany.luckysevens;

//@author Greg Svoboda
public class LuckySevensApp {

    public static void main(String[] args) {

//initialize values        
        
        Console console = new Console();
        Dice dice = new Dice();
        GameFunctions calc = new GameFunctions();
        int diceTotal;
        int rollNum = 0;
        int maxBet, maxBetRoll;
        int remainingBet;

//Get and verify user input        
        
        System.out.println(console.intro());
        remainingBet = (console.verifyInput());
        maxBet = remainingBet;
        maxBetRoll = rollNum;

//Create new dice and roll them        
        
        while (remainingBet > 0) {
            ++rollNum;
            Dice getDiceRoll = new Dice();
            diceTotal = dice.getDiceTotal();
            remainingBet = calc.hornSwoggle(remainingBet, diceTotal);

 //If amount of money exceeds max amount recorded, update recorded max bet amount, and rolls         
            
            if (remainingBet > maxBet) {
                maxBet = remainingBet;
                maxBetRoll = rollNum;
            }
        }
 
        
//Send result to console for printing        
        console.printResults(rollNum, maxBetRoll, maxBet);
        
        
        
        
    }
}
