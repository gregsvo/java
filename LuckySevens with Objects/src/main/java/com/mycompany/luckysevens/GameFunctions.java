package com.mycompany.luckysevens;

public class GameFunctions {

//Take and add money according to roll amount    
    
    public int hornSwoggle(int remainingBet, int diceTotal) {

        if (diceTotal == 7) {
            remainingBet += 4;

        } else {
            remainingBet -= 1;
        }

        return remainingBet;
    }
}
