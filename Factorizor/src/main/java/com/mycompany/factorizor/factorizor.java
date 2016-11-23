package com.mycompany.factorizor;

// @author Greg Svoboda
public class factorizor {



    public static void main(String[] args) {

        
        int number = 0;
        
        GetInput userInput = new GetInput();
        number = userInput.getInput();
        GetFactors userFactor = new GetFactors();
        GetPerfect userPerfect = new GetPerfect();
        GetPrime userPrime = new GetPrime();
        userFactor.getFactors(number);
        userPerfect.GetPerfect(number);
        userPrime.GetPrime(number);
    }
}