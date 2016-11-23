package com.mycompany.minmaxsumavg;

/**
 * @author Greg Svoboda
 */
import java.util.Scanner;
import org.w3c.dom.ls.LSOutput;

public class MinMaxSumAvg {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isValid = false;
        boolean tryAgain = true;
        String userInputString = "";
        int userInput = 0;
        int i = 0;
        int min = 0;
        int u;
        int[] intArray = new int[10];
        int max = intArray[0];
        int sum = 0;
        int avg = intArray[0];

        while (tryAgain) {
            while (!isValid) {

                for (i = 0; i < intArray.length; i++) {
                    try {
                        System.out.print("Please give me integer, greater than zero. #" + (i + 1) + " (of 10):");
                        userInputString = sc.nextLine();
                        userInput = Integer.parseInt(userInputString);

                        if (userInput > 0) {

                            intArray[i] = userInput;
                        }
                    } catch (NumberFormatException nfe) {
                        System.out.println("I SAID AN INTEGER THAT WAS GREATER THAN ZERO!");
                        
                    }
                }
                min = intArray[0];
                isValid = true;
            }

            try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Thinking... ");

            try {
                Thread.sleep(1000); //1000 milliseconds = 1 second
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            for (u = 0; u < intArray.length; u++) {
                if (intArray[u] > max) {
                    max = intArray[u];
                } else if (intArray[u] < min) {
                    min = intArray[u];
                }

            }
            System.out.println("Highest number of your choosing: " + max);
            System.out.println("Lowest number of your choosing: " + min);

            for (int x : intArray) {
                sum += x;
            }

            System.out.println("Added up, they equal: " + sum);
            System.out.println("The numbers you chose average: " + (sum / intArray.length));

            System.out.println("Would you like to try more integers? Y or N");
            userInputString = sc.nextLine().toLowerCase();

            if (userInputString.equals("y")) {
                tryAgain = true;
            }
                else if (userInputString.equals("n")) {
                tryAgain = false;
            }
        

    }
} //END of MAIN
}//END 
