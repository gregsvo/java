package com.mycompany.rockpaperscissorswithobjects;

public class DelayGame {

//1000 milliseconds = 1 second    
    public void delay(int requestedDelay) {

        try {
            Thread.sleep(requestedDelay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
