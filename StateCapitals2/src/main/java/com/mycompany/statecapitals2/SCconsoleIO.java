package com.mycompany.statecapitals2;

import java.util.Scanner;

public class SCconsoleIO {

    boolean isValid;
    public Scanner sc = new Scanner(System.in);

    // #1 TAKE IN QUESTION, TAKE INPUT, PARSE TO INT, SEND BACK INT
    public int getAndVerifyInt(String question) {
        int answer = 0;
        isValid = false;
        while (!isValid) {
            try {
                System.out.println(question);
                String answerInput = sc.nextLine();
                answer = Integer.parseInt(answerInput);

                if (answer > 0) {
                    isValid = true;

                } else {
                    System.out.println(" Your input must be more than zero. ");
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return answer;
    }

    // #2 TAKE IN MIN AND MAX RANGE, TAKE QUESTION STRING, PARSE TO INT, SEND BACK INT
    public int getAndVerifyIntMinMax(int min, int max, String question) {
        isValid = false;
        int answer = 0;

        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Integer.parseInt(answerInput);

                if ((answer >= min) && (answer <= max)) {
                    isValid = true;

                } else {
                    System.out.println(" Your input must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return answer;
    }

    // #3 TAKE IN STRING QUESTION, VERIFY, SEND BACK STRING
    public String getString(String question) {
        isValid = false;
        String answer = "";

        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = answerInput;

                isValid = true;

            } catch (NumberFormatException nfe) {
                System.out.println(" Invalid Input. ");
            }
        }

        return answer;
    }

    // #4 TAKE IN STRING QUESTION, ASK FOR FLOAT, VERIFY, SEND BACK FLOAT    
    public Float getFloat(String question) {
        isValid = false;
        Float answer = 0f;

        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Float.parseFloat(answerInput);

                isValid = true;

            } catch (NumberFormatException nfe) {
                System.out.println(" Invalid Input. ");

            }
        }
        return answer;

    }

    // #5 TAKE IN MIN AND MAX FLOAT VALUES, STRING QUESTION. ASK FOR FLOAT, VERIFY, SEND BACK FLOAT
    public Float getFloatMinMax(int min, int max, String question) {
        isValid = false;
        Float answer = 0f;

        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Float.parseFloat(answerInput);

                if ((answer >= min) && (answer <= max)) {
                    isValid = true;

                } else {
                    System.out.println(" Your input must be between " + min + ", and " + max);
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return answer;
    }

    // #6 TAKE IN STRING QUESTION, ASK FOR DOUBLE, TEST FOR VALID, RETURN.
    public Double getDouble(String question) {
        isValid = false;
        Double answer = 0d;

        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Double.parseDouble(answerInput);

                isValid = true;

            } catch (NumberFormatException nfe) {
                System.out.println(" Invalid Input. ");

            }
        }
        return answer;

    }

    // #7 TAKE IN STRING QUESTION, DOUBLE MIN, DOUBLE MAX,  ASK FOR DOUBLE, TEST FOR VALID, RETURN.       
    public Double getDoubleMinMax(Double min, Double max, String question) {

        Double answer = 0d;
        isValid = false;
        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Double.parseDouble(answerInput);

                if ((answer >= min) && (answer <= max)) {
                    isValid = true;

                } else {
                    System.out.println(" Your input must be between " + "25000" + ", and " + "7,836,453");
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return answer;
    }

    // #8 TAKE IN STRING
    public void getStringAndPrintIt(String question) {

        String answer = "";
        System.out.println(question);

    }

    //#9 PLAY AGAIN? CHOOSE 1 OR 2- SEND BACK BOOLEAN
    public boolean playAgain(String question) {
        isValid = false;
        boolean playAgain = false;
        int answer = 0;

        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Integer.parseInt(answerInput);

                if (answer == 1) {
                    playAgain = true;
                    isValid = true;
                }            
                if (answer == 2) {
                        playAgain = false;
                        isValid = true;
                }

                 else {
                    System.out.println(" Your input must be either '1' (play again) or '2' (quit). ");
                }
            
            } catch (NumberFormatException nfe) {
                System.out.println(" Invalid Input. ");
            }
        }

        return playAgain;
    }
}//CONSOLE IO END
