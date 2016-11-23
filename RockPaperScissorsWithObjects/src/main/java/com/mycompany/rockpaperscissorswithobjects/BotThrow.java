package com.mycompany.rockpaperscissorswithobjects;

public class BotThrow {

    //Get random bot throw
    public static int getBotThrow() {

        int botThrow;

        botThrow = (int) (Math.random() * 3) + 1;

        return botThrow;
    }

}
