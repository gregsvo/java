package com.mycompany.rockpaperscissorswithobjects;

public class RPSapp {

    public static void main(String[] args) {

        int userThrow;
        int botThrow;
        boolean playAgain = true;

        RPSconsole console = new RPSconsole();
        DelayGame delay = new DelayGame();

        while (playAgain) {
            console.intro(); //Print introduction to game
            userThrow = console.startGame();//Get and verify user input for user throw choice
            delay.delay(1000);//delay game 1 second (1000 milliseconds = 1 second)
            botThrow = BotThrow.getBotThrow();//Get random bot throw
            delay.delay(1000);//delay game 1 second (1000 milliseconds = 1 second)
            console.gameResult(userThrow, botThrow);//send user and bot throws to console for printout of winner
            delay.delay(3000);//delay game 1 second (3000 milliseconds = 3 seconds)
            playAgain = console.playAgain();//ask to play again
        }
    }
}
