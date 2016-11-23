package com.mycompany.factorizor;

public class GetPrime {

    public void GetPrime(int number) {

        if ((number == 1) || (number == 2)) {
            System.out.println(number + " is a prime number.");
        }

        if ((number % 2 == 0) && (number > 2)) {
            System.out.println(number + " is not a prime number.");
        } 
        
        else {

        for (int i = 3; i <= number; i += 2) {
            if (i == number) {
                System.out.println(number + " is a prime number.");
            } else if (number % i == 0) {
                System.out.println(number + " is not a prime number.");
                break;
            }
        }
        }

    }

}
