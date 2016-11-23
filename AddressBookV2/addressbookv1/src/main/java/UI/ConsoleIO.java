package UI;

import java.util.Scanner;

/**
 *
 * @author Kyle is the best!
 */
public class ConsoleIO {

    Scanner keyboard = new Scanner(System.in);

//BEGIN: print
    /**
     *
     * @param message
     */
    public void print(String message) {
        System.out.print(message);
    }//end print()
//END: print

//BEGIN: println
    /**
     *
     * @param message
     */
    public void println(String message) {
        System.out.print("\n" + message);
    }//end println()
//END: println

//BEGIN: getUserInputString    
    //asks <question> then validates <acceptableAnswers[]> and provides <eMsg>
    /**
     *
     * @param question
     * @param acceptableAnswers
     * @param eMsg
     * @return
     */
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

    /**
     *
     * @param question
     * @param acceptableAnswers
     * @return
     */
    public String getUserInputString(String question, String acceptableAnswers[]) {
        return getUserInputString(question, acceptableAnswers, "");
    }

    /**
     * This method presents a question, and expects a String input.
     *
     * @param question
     * @return
     */
    public String getUserInputString(String question) {
        return getUserInputString(question, new String[]{""}, "");
    }
//END: getUserInputString

//BEGIN: getTrueFalse    
    //asks <question> then validates <acceptableAnswers[]> and provides <eMsg>
    /**
     *
     * @param question
     * @param eMsg
     * @return
     */
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

    /**
     *
     * @param question
     * @return
     */
    public boolean getTrueFalse(String question) {
        return getTrueFalse(question, "");
    }
//END: getTrueFalse

//BEGIN: getMultiChoiceInt
    //asks <question> with <choices[]> and returns the int of the valid choice selected
    /**
     *
     * @param question
     * @param choices
     * @return
     */
    public int getMultiChoiceInt(String question, String choices[]) {
        String userResponse = "";
        int userInt = 0;
        String eMsg = "\nSorry, please try something else...";

        while (true) {
            try {
                print("\n" + question);
                for (int i = 1; i < (choices.length + 1); i++) {
                    print("\n\t" + i + ") " + choices[i - 1]);
                }
                userResponse = keyboard.nextLine();
                userInt = Integer.parseInt(userResponse);
                //vaildate the answer is a choice
                if (userInt >= 1 && userInt <= choices.length) {
                    return userInt;
                } else {
                    print(eMsg);
                }
            } catch (Exception ex) {
                print(eMsg);
            }
        }

    }//end getMultiChoice()
//END: getMultiChoiceInt

//BEGIN: getMultiChoiceString
    //asks <question> with <choices[]> and returns the String of the valid choice selected
    /**
     *
     * @param question
     * @param choices
     * @return
     */
    public String getMultiChoiceString(String question, String choices[]) {
        String userResponse = "";
        String eMsg = "\nSorry, please try something else...";

        while (true) {
            try {
                print("\n" + question);
                for (int i = 1; i < (choices.length + 1); i++) {
                    print("\n\t" + i + ") " + choices[i - 1]);
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

    }//end getMultiChoice()
//END: getMultiChoiceString

//BEGIN: getUserInputInt    
    //asks <question>, validates from <min> to <max> with option for the comparisons to be <equalTo>
    /**
     *
     * @param question
     * @param min
     * @param max
     * @param equalTo
     * @return
     */
    public int getUserInputInt(String question, int min, int max, boolean equalTo) {
        Scanner sc = new Scanner(System.in);
        String userResponse = "";
        int userInt = 0;
        String eMsg = "\nSorry, please enter a valid menu option.";

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

    }

    /**
     *
     * @param question
     * @param min
     * @param max
     * @return
     */
    public int getUserInputInt(String question, int min, int max) {
        return getUserInputInt(question, min, max, false);
    }//Not equal to min or max    

    /**
     *
     * @param question
     * @param isMin
     * @param eitherMinMax
     * @param equalTo
     * @return
     */
    public int getUserInputInt(String question, boolean isMin, int eitherMinMax, boolean equalTo) {
        if (isMin) {
            return getUserInputInt(question, eitherMinMax, Integer.MAX_VALUE, equalTo);
        } else {
            return getUserInputInt(question, Integer.MIN_VALUE, eitherMinMax, equalTo);
        }
    }//Either min or max and whether it should be equal to it

    /**
     *
     * @param question
     * @param isMin
     * @param eitherMinMax
     * @return
     */
    public int getUserInputInt(String question, boolean isMin, int eitherMinMax) {
        return getUserInputInt(question, isMin, eitherMinMax, false);
    }//Either min or max

    /**
     *
     * @param question
     * @return
     */
    public int getUserInputInt(String question) {
        return getUserInputInt(question, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
    }//Any int
//END: getUserInputInt

//BEGIN: getUserInputDouble
    //asks <question>, validates from <min> to <max> with option for the comparisons to be <equalTo>
    /**
     *
     * @param question
     * @param min
     * @param max
     * @param equalTo
     * @return
     */
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

    /**
     *
     * @param question
     * @param min
     * @param max
     * @return
     */
    public double getUserInputDouble(String question, double min, double max) {
        return getUserInputDouble(question, min, max, false);
    }//Not equal to min or max    

    /**
     *
     * @param question
     * @param isMin
     * @param eitherMinMax
     * @param equalTo
     * @return
     */
    public double getUserInputDouble(String question, boolean isMin, double eitherMinMax, boolean equalTo) {
        if (isMin) {
            return getUserInputDouble(question, eitherMinMax, Double.POSITIVE_INFINITY, equalTo);
        } else {
            return getUserInputDouble(question, Double.NEGATIVE_INFINITY, eitherMinMax, equalTo);
        }
    }//Either min or max and whether it should be equal to it

    /**
     *
     * @param question
     * @param isMin
     * @param eitherMinMax
     * @return
     */
    public double getUserInputDouble(String question, boolean isMin, double eitherMinMax) {
        return getUserInputDouble(question, isMin, eitherMinMax, false);
    }//Either min or max

    /**
     *
     * @param question
     * @return
     */
    public double getUserInputDouble(String question) {
        return getUserInputDouble(question, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, false);
    }//Any double
//END: getUserInputDouble

//BEGIN: getUserInputFloat
    //asks <question>, validates from <min> to <max> with option for the comparisons to be <equalTo>
    /**
     *
     * @param question
     * @param min
     * @param max
     * @param equalTo
     * @return
     */
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

    /**
     *
     * @param question
     * @param min
     * @param max
     * @return
     */
    public float getUserInputFloat(String question, float min, float max) {
        return getUserInputFloat(question, min, max, false);
    }//Not equal to min or max    

    /**
     *
     * @param question
     * @param isMin
     * @param eitherMinMax
     * @param equalTo
     * @return
     */
    public float getUserInputFloat(String question, boolean isMin, float eitherMinMax, boolean equalTo) {
        if (isMin) {
            return getUserInputFloat(question, eitherMinMax, Float.POSITIVE_INFINITY, equalTo);
        } else {
            return getUserInputFloat(question, Float.NEGATIVE_INFINITY, eitherMinMax, equalTo);
        }
    }//Either min or max and whether it should be equal to it

    /**
     *
     * @param question
     * @param isMin
     * @param eitherMinMax
     * @return
     */
    public float getUserInputFloat(String question, boolean isMin, float eitherMinMax) {
        return getUserInputFloat(question, isMin, eitherMinMax, false);
    }//Either min or max

    /**
     *
     * @param question
     * @return
     */
    public float getUserInputFloat(String question) {
        return getUserInputFloat(question, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, false);
    }//Any float

    public int getUserInputInt(String prompt, String error) {
        boolean isValid = false;
        String userInput = "";
        int input = 0;

        //Get input from user for window information
        while (!isValid) {
            try {
                System.out.println(prompt);
                userInput = keyboard.nextLine();
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
     * @param menu
     * @param error
     * @param options
     * @return
     */
    public int getMenuChoice(String menu, String error, int[] options) {

        int userChoice = this.getUserInputInt(menu, error);
        while (true) {
            for (int i : options) {
                if (i == userChoice) {
                    return userChoice;
                }
            }

            this.print(menu);
            this.getUserInputInt("", "You didn't type a number");
        }

    }

}//END: 
