package com.fortim.ddcode.ui;

import Email_DTO.Email;
import java.util.Scanner;
import Email_DAO.EmailDAO;

public class ConsoleIO {

    EmailDAO dao = new EmailDAO();
    Scanner keyboard = new Scanner(System.in);

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.print("\n" + message);
    }

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
    }

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

    }

    public int getUserInputInt(String question, int min, int max, boolean equalTo) {
        Scanner sc = new Scanner(System.in);
        String userResponse = "";
        int userInt = 0;
        String eMsg = "\nSorry, please enter a menu choice of 1 through 6.";

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
    }

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

    public String getEmailInput(String question, String error) {

        boolean valid = false;
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([ \\w]+\\.)+[\\w]+[\\w]$";
        String input = "fffff";
        while (!valid) {

            try {
                print("\n" + question);
                input = keyboard.nextLine();

                if (!input.matches(EMAIL_REGEX) || (input.equals(""))) {
                    print("\n" + error);

                    if (dao.listSubscribed().contains(input)) {

                        System.out.println(" This email is already subscribed. ");
                    }

                } else {
                    valid = true;

                }

            } catch (Exception ex) {
                print("\n" + error);
            }

        }
        return input;
    }

    public void printEmail(Email email) {
        System.out.println(email.getId() + "---" + email.getAddress() + " Subscribed: " + email.getStatus() + " Updated: " + email.getLastUpdated());
        System.out.println("---------------------------");
    }

    public void printStats(int numOfsubs, int numOfunsub, int numOfresub) {
        System.out.println(" Right now: ");
        System.out.println("Subscribed: " + numOfsubs);
        System.out.println("---------------------------");
        System.out.println("Unsubscribed: " + numOfunsub);
        System.out.println("---------------------------");
        System.out.println("Resubscribed: " + numOfresub);
    }

}
