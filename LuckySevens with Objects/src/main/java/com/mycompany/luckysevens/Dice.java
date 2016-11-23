package com.mycompany.luckysevens;

public class Dice {

    //Get random roll total amount, new for every roll.
    
    public int getDiceTotal() {
        int diceTotal = (1 + (int) (Math.random() * ((6 - 1) + 1)) + (1 + (int) (Math.random() * ((6 - 1) + 1))));

        return diceTotal;
    }

}// END Dice
