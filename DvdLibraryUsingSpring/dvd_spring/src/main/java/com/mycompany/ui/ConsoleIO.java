/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ui;

import java.util.Scanner;

/**
 * @author Kyle Sickels
 */
public class ConsoleIO {

    Scanner keyboard = new Scanner(System.in);
//BEGIN: print

    public void print(String message) {
        System.out.print(message);
    }//end print()
//END: print

//BEGIN: println
    public void println(String message) {
        System.out.print("\n" + message);
    }//end println()
//END: println

//BEGIN: getUserInputString    
    //asks <question> then validates <acceptableAnswers[]> and provides <eMsg>
    public String getUserInputString(String question, String acceptableAnswers[], String eMsg) {
        String answer = "";
        boolean notValid = true;
        if (eMsg.equals("")) {
            eMsg = "Sorry, try a different response...";
        }
        while (notValid) {
            try {
                print("\n" + question);
                answer = keyboard.nextLine();
                //"" is a sentinel value to indicate the input doesn't matter
                if (acceptableAnswers[0].equals("")) {
                    notValid = false;
                    //verifies input is an acceptable answer 
                } else {
                    for (int i = 0; i < acceptableAnswers.length; i++) {
                        if (answer.equals(acceptableAnswers[i])) {
                            notValid = false;
                        }
                    }
                }
                //error message for unacceptable input
                if (notValid) {
                    print("\n" + eMsg);
                }
            } catch (Exception ex) {
                print("\n" + eMsg);
            }
        }
        return answer;
    }// end getUserInputString()

    public String getUserInputString(String question, String acceptableAnswers[]) {
        return getUserInputString(question, acceptableAnswers, "");
    }

    public String getUserInputString(String question) {
        return getUserInputString(question, new String[]{""}, "");
    }
//END: getUserInputString
//BEGIN: getTrueFalse    
    //asks <question> then validates <acceptableAnswers[]> and provides <eMsg>

    public boolean getTrueFalse(String question, String eMsg) {
        boolean answer = false;
        String response = "";
        boolean notValid = true;
        if (eMsg.equals("")) {
            eMsg = "Sorry, try a different response...";
        }
        while (notValid) {
            try {
                print(question);
                response = keyboard.nextLine().toLowerCase();
                if (response.equals("yes") || response.equals("y")) {
                    answer = true;
                    notValid = false;
                } else if (response.equals("no") || response.equals("n")) {
                    answer = false;
                    notValid = false;
                } else {
                    notValid = true;
                }
            } catch (Exception ex) {
                print(eMsg);
            }
        }
        return answer;
    }// end getTrueFalse()

    public boolean getTrueFalse(String question) {
        return getTrueFalse(question, "");
    }
//END: getTrueFalse
//BEGIN: getMultiChoiceInt
    //asks <question> with <choices[]> and returns the int of the valid choice selected

    public String getMultiChoiceString(String question, String choices[], boolean addToExit, String finalChoice) {
        String userResponse = "";
        String eMsg = "\nSorry, please try something else...";
        if (finalChoice.equals("")) {
            finalChoice = "Exit";
        }
        while (true) {
            if (addToExit) {
                try {
                    print("\n" + question);
                    String[] newChoices = new String[choices.length+1];
                    for (int i = 1; i <= (choices.length + 1); i++) {
                        if (i < (choices.length + 1)) {
                            print("\n\t" + i + ") " + choices[i - 1]);
                            newChoices[i-1] = choices[i-1];
                        } else {
                            print("\n\t" + i +") "+ finalChoice);
                            newChoices[choices.length] = finalChoice;
                        }
                    }

                    System.out.print("\nChoice: ");
                    userResponse = keyboard.nextLine();
                    //vaildate the answer is a choice
                    for (int i = 0; i < newChoices.length; i++) {
                        if (newChoices[i].equals(userResponse)) {
                            return userResponse;
                        }
                    }
                    //Backup incase shorthand integer was used instead of string response
                    try {
                        int shortAnswer = Integer.parseInt(userResponse);
                        if (shortAnswer >= 0) {
                            for (int i = 0; i < newChoices.length; i++) {
                                if (i+1==shortAnswer) {
                                    return newChoices[i];
                                }
                            }
                        }
                    } catch (Exception ex) {
                        //PASS
                    }
                    //if response was not one of the presented
                    print(eMsg);
                } catch (Exception ex) {
                    print(eMsg);
                }
            } else{
                try {
                    print("\n" + question);
                    for (int i = 1; i < (choices.length + 1); i++) {
                        if (i < (choices.length + 1)) {
                            print("\n\t" + i + ") " + choices[i - 1]);
                        } 
                    }

                    System.out.print("\nChoice: ");
                    userResponse = keyboard.nextLine();
                    //vaildate the answer is a choice
                    for (int i = 0; i < choices.length; i++) {
                        if (choices[i].equals(userResponse)) {
                            return userResponse;
                        }
                    }
                    //Backup incase shorthand integer was used instead of string response
                    try {
                        int shortAnswer = Integer.parseInt(userResponse);
                        if (shortAnswer >= 0) {
                            for (int i = 0; i < choices.length; i++) {
                                if (choices[i].equals(choices[shortAnswer - 1])) {
                                    return choices[shortAnswer - 1];
                                }
                            }
                        }
                    } catch (Exception ex) {
                        //PASS
                    }
                    //if response was not one of the presented
                    print(eMsg);
                } catch (Exception ex) {
                    print(eMsg);
                }

            }
        }
    }
//end getMultiChoice()
//END: getMultiChoiceString
public String getMultiChoiceString(String question, String[]choices){
    return getMultiChoiceString(question, choices, false, "");
}

//BEGIN: getUserInputInt    
    //asks <question>, validates from <min> to <max> with option for the comparisons to be <equalTo>

    public int getUserInputInt(String question, int min, int max, boolean equalTo) {
        Scanner sc = new Scanner(System.in);
        String userResponse = "";
        int userInt = 0;
        String eMsg = "\nSorry, please enter a value between: " + min + " - " + max;
        while (true) {
            try {
                print("\n" + question);
                userResponse = sc.nextLine();
                userInt = Integer.parseInt(userResponse);
                //validate less=/greater= to input or less/greater to input
                if (equalTo && userInt >= min && userInt <= max) {
                    return userInt;
                } else if (!equalTo && userInt > min && userInt < max) {
                    return userInt;
                } else {
                    print(eMsg);
                }
            } catch (Exception ex) {
                print(eMsg);
            }
        }
    }//end getUserInputInt()

    public int getUserInputInt(String question, int min, int max) {
        return getUserInputInt(question, min, max, false);
    }//Not equal to min or max    

    public int getUserInputInt(String question, boolean isMin, int eitherMinMax, boolean equalTo) {
        if (isMin) {
            return getUserInputInt(question, eitherMinMax, Integer.MAX_VALUE, equalTo);
        } else {
            return getUserInputInt(question, Integer.MIN_VALUE, eitherMinMax, equalTo);
        }
    }//Either min or max and whether it should be equal to it

    public int getUserInputInt(String question, boolean isMin, int eitherMinMax) {
        return getUserInputInt(question, isMin, eitherMinMax, false);
    }//Either min or max

    public int getUserInputInt(String question) {
        return getUserInputInt(question, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
    }//Any int
//END: getUserInputInt
//BEGIN: getUserInputDouble
    //asks <question>, validates from <min> to <max> with option for the comparisons to be <equalTo>

    public double getUserInputDouble(String question, double min, double max, boolean equalTo) {
        String userResponse = "";
        double userDbl = 0.0;
        String eMsg = "\nSorry, try a different response...";
        while (true) {
            try {
                System.out.print("\n" + question);
                userResponse = keyboard.nextLine();
                userDbl = Double.parseDouble(userResponse);
                //validate less=/greater= input or less/greater
                if (equalTo && userDbl >= min && userDbl <= max) {
                    return userDbl;
                } else if (!equalTo && userDbl > min && userDbl < max) {
                    return userDbl;
                } else {
                    print(eMsg);
                }
            } catch (Exception ex) {
                print(eMsg);
            }
        }
    }//end getUserInputDouble()    

    public double getUserInputDouble(String question, double min, double max) {
        return getUserInputDouble(question, min, max, false);
    }//Not equal to min or max    

    public double getUserInputDouble(String question, boolean isMin, double eitherMinMax, boolean equalTo) {
        if (isMin) {
            return getUserInputDouble(question, eitherMinMax, Double.POSITIVE_INFINITY, equalTo);
        } else {
            return getUserInputDouble(question, Double.NEGATIVE_INFINITY, eitherMinMax, equalTo);
        }
    }//Either min or max and whether it should be equal to it

    public double getUserInputDouble(String question, boolean isMin, double eitherMinMax) {
        return getUserInputDouble(question, isMin, eitherMinMax, false);
    }//Either min or max

    public double getUserInputDouble(String question) {
        return getUserInputDouble(question, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false);
    }//Any double
//END: getUserInputDouble
//BEGIN: getUserInputFloat
    //asks <question>, validates from <min> to <max> with option for the comparisons to be <equalTo>

    public float getUserInputFloat(String question, float min, float max, boolean equalTo) {
        String userResponse = "";
        float userFloat = 0.0f;
        String eMsg = "\nSorry, try a different response...";
        while (true) {
            try {
                System.out.print("\n" + question);
                userResponse = keyboard.nextLine();
                userFloat = Float.parseFloat(userResponse);
                //validate less=/greater= input or less/greater
                if (equalTo && userFloat >= min && userFloat <= max) {
                    return userFloat;
                } else if (!equalTo && userFloat > min && userFloat < max) {
                    return userFloat;
                } else {
                    print(eMsg);
                }
            } catch (Exception ex) {
                print(eMsg);
            }
        }
    }//end getUserInputFloat()    

    public float getUserInputFloat(String question, float min, float max) {
        return getUserInputFloat(question, min, max, false);
    }//Not equal to min or max    

    public float getUserInputFloat(String question, boolean isMin, float eitherMinMax, boolean equalTo) {
        if (isMin) {
            return getUserInputFloat(question, eitherMinMax, Float.POSITIVE_INFINITY, equalTo);
        } else {
            return getUserInputFloat(question, Float.NEGATIVE_INFINITY, eitherMinMax, equalTo);
        }
    }//Either min or max and whether it should be equal to it

    public float getUserInputFloat(String question, boolean isMin, float eitherMinMax) {
        return getUserInputFloat(question, isMin, eitherMinMax, false);
    }//Either min or max

    public float getUserInputFloat(String question) {
        return getUserInputFloat(question, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, false);
    }//Any float
//END: getUserInputFloat

    //BEGIN: getMultiChoiceInt
    //asks <question> with <choices[]> and returns the int of the valid choice selected
    public int getMultiChoiceInt(String question, String choices[], boolean addExitToChoices, String finalChoice, boolean trueForIndexFalseForSelection) {
        int add = trueForIndexFalseForSelection ? 0 : 1;

        String userResponse = "";
        String eMsg = "\nSorry, please try something else...";

        if (finalChoice.equals("")) {
            finalChoice = "Exit";
        }
        String[] finalChoices;
        if (addExitToChoices) {
            finalChoices = new String[choices.length + 1];
            int i = 0;
            for (String index : choices) {
                finalChoices[i] = choices[i];
                i++;
            }
            finalChoices[choices.length] = finalChoice;
        } else {
            finalChoices = choices;
        }
        while (true) {
            try {
                print("\n" + question);
                for (int i = 1; i < (finalChoices.length + 1); i++) {
                    print("\n\t" + i + ") " + finalChoices[i - 1]);
                }
                System.out.print("\nChoice: ");
                userResponse = keyboard.nextLine();
                //vaildate the answer is a choice
                for (int i = 0; i < finalChoices.length; i++) {
                    if (finalChoices[i].equals(userResponse)) {
                        return i + add;
                    }
                }
                //Backup incase shorthand integer was used instead of string response
                try {
                    int shortAnswer = Integer.parseInt(userResponse);
                    if (shortAnswer >= 0) {
                        for (int i = 0; i < finalChoices.length; i++) {
                            if (finalChoices[i].equals(finalChoices[shortAnswer - 1])) {
                                return i + add;
                            }
                        }
                    }
                } catch (Exception ex) {
                    //PASS
                }
                //if response was not one of the presented
                print(eMsg);
            } catch (Exception ex) {
                print(eMsg);
            }
        }
    }//end getMultiChoice()

    public int getMultiChoiceInt(String question, String choices[]) {
        return getMultiChoiceInt(question, choices, false, "", false);
    }
//END: getMultiChoiceInt
}
