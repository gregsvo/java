/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.ui;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ConsoleIO {

    private Scanner sc = new Scanner(System.in);

    /**
     *
     * @param message
     */
    public void print(String message) {
        System.out.println(message);
    }
    
    /**
     *
     * @param message
     */
    public void printSameLine(String message) {
        System.out.print(message);
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public float getUserInputFloatMinMax(String prompt, float min, float max) {
        String error = ("Enter a number larger than " + min + " and smaller than " + max);
        float input = getUserInputFloat(prompt, error);
        while (input <= min || input >= max) {
            System.out.println(error);
            input = getUserInputFloat(prompt, error);
        }
        return input;
    }

    /**
     *
     * @param prompt
     * @param error
     * @return
     */
    public String getUserInputString(String prompt, String error) {
        String userInput = "";
        boolean isValid = false;

        //Get input from user for window information
        boolean notAccurate = true;

        while (!isValid) {
            try {
                System.out.println(prompt);
                userInput = sc.nextLine();
                isValid = true;

            } catch (Exception x) {
                System.out.println(error);
            }
        }

        return userInput;

    }

    /**
     *
     * @param prompt
     * @return
     */
    public boolean askUserYesOrNo(String prompt) {

        while (true) {

            System.out.println(prompt);
            int i = this.getUserInputInt("", "Enter 1 or 2");
            switch (i) {
                case 1:

                    return true;
                case 2:

                    return false;
                default:
                    System.out.println("Enter 1 or 2");
                    break;

            }
        }
        //return input;
    }

    /**
     *
     * @param prompt
     * @param error
     * @return
     */
    public String askUserToValidateStringInput(String prompt, String error) {
        String input = this.getUserInputString(prompt, error);
        while (true) {

            System.out.println("Is this the text you want to enter: \"" + input + "\"\n"
                    + "1: Yes\n"
                    + "2: No\n"
                    + "===================\n"
                    + "Enter 1 or 2.");
            int i = this.getUserInputInt("", "Enter 1 or 2");
            switch (i) {
                case 1:

                    return input;
                case 2:

                    input = this.getUserInputString("Try Again:", "error");
                    break;
                default:
                    System.out.println("Enter 1 or 2");
                    break;

            }
        }
        
    }

    /**
     *
     * @param prompt
     * @param error
     * @return
     */
    public float getUserInputFloat(String prompt, String error) {
        boolean isValid = false;
        String userInput = "";
        float input = 0;

        //Get input from user for window information
        while (!isValid) {
            try {
                System.out.println(prompt);
                userInput = sc.nextLine();
                input = Float.parseFloat(userInput);
                isValid = true;

            } catch (Exception x) {
                System.out.println(error);
            }
        }
        return input;
    }

    /**
     *
     * @param prompt
     * @param error
     * @return
     */
    public int getUserInputInt(String prompt, String error) {
        boolean isValid = false;
        String userInput = "";
        int input = 0;

        //Get input from user for window information
        while (!isValid) {
            try {
                System.out.println(prompt);
                userInput = sc.nextLine();
                input = Integer.parseInt(userInput);
                isValid = true;

            } catch (Exception x) {
                System.out.println(error);
            }
        }
        return input;
    }

    /**
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public int getUserInputIntMinMax(String prompt, int min, int max) {
        String error = ("Enter a number larger than " + min + " and smaller than " + max);
        int input = getUserInputInt(prompt, error);
        while (input <= min  || input >= max) {
            System.out.println(error);
            input = getUserInputInt(prompt, error);
        }
        return input;
    }

    /**
     *
     * @param menu
     * @param error
     * @param options
     * @return
     */
    public int getMenuChoice(String menu, String error, int[] options) {
        
        int userChoice = this.getUserInputInt(menu, error);
        while (true) {
            for (int i : options) {
                if (i==userChoice) {
                    return userChoice;
                }
            }

            this.print(error);
            this.getUserInputInt(menu, error);
        }

    }
    
    /**
     *
     * @param prompt
     * @param error
     * @return
     */
    public double getUserInputDouble(String prompt, String error) {
        boolean isValid = false;
        String userInput = "";
        double input = 0.0;

        //Get input from user for window information
        while (!isValid) {
            try {
                System.out.println(prompt);
                userInput = sc.nextLine();
                input = Double.parseDouble(userInput);
                isValid = true;

            } catch (Exception x) {
                System.out.println(error);
            }
        }
        return input;
    }

    /**
     *Enter a prompt and a minimum and maximum value.  Error message is 
     * already made.
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public double getUserInputDoubleMinMax(String prompt, double min, double max) {
        String error = ("Enter a number larger than " + min + " and smaller than " + max);
        double input = getUserInputDouble(prompt, error);
        while (input <= min  || input >= max) {
            System.out.println(error);
            input = getUserInputDouble(prompt, error);
        }
        return input;
    }
    
}
