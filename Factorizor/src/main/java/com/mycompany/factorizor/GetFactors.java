package com.mycompany.factorizor;

public class GetFactors {

    public void getFactors(int number) {
    System.out.println("The factors of " + number + " are:");
        int factorNumber = 1;

        while (factorNumber < number) {

            if (number % factorNumber == 0) {
                System.out.println(factorNumber);
            }
            factorNumber++;

        }

    }

}
