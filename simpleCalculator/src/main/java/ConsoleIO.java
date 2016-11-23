import java.util.Scanner;

public class ConsoleIO {

    
    public Scanner sc = new Scanner(System.in);

// #1 TAKE IN QUESTION, TAKE STRING, PARSE TO INT, SEND BACK INT
    public int getAndVerifyInt(String question) {
        int answer =0;
        boolean isValid = false;    
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

        int answer = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Integer.parseInt(answerInput);

                if ((answer >= min) && (answer <= max)) {
                    isValid = true;
                    return answer;
                } else {
                    System.out.println(" Your input must be between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return 0;
    }

// #3 TAKE IN STRING QUESTION, ASK FOR STRING INPUT, VERIFY, SEND BACK STRING
    public String getString(String question) {

        String answer;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = answerInput;

                isValid = true;
                return answer;
            } catch (NumberFormatException nfe) {
                System.out.println(" Invalid Input. ");
            }
        }

        return "";
    }

// #4 TAKE IN STRING QUESTION, ASK FOR FLOAT, VERIFY, SEND BACK FLOAT    
    public Float getFloat(String question) {

        Float answer = 0f;
        boolean isValid = false;
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

    // #5 TAKE IN MIN AND MAX INT VALUES, STRING QUESTION. ASK FOR FLOAT, VERIFY, SEND BACK FLOAT
    public Float getFloatMinMax(int min, int max, String question) {

        Float answer;
        boolean isValid = false;    
        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Float.parseFloat(answerInput);

                if ((answer >= min) && (answer <= max)) {
                    isValid = true;
                    return answer;
                } else {
                    System.out.println(" Your input must be between " + min + ", and " + max);
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return null;
    }

    // #6 TAKE IN STRING QUESTION, ASK FOR DOUBLE, TEST FOR VALID, RETURN.
    public Double getDouble(String question) {

        Double answer = 0d;
        boolean isValid = false;
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

    // #7 TAKE IN STRING QUESTION, ASK FOR FLOAT, TEST FOR VALID, RETURN.       
    public Double getDoubleMinMax(int min, int max, String question) {
        boolean isValid = false;    
        Double answer;

        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Double.parseDouble(answerInput);

                if ((answer >= min) && (answer <= max)) {
                    isValid = true;
                    return answer;
                } else {
                    System.out.println(" Your input must be between " + min + ", and " + max);
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return null;
    }

    // #8 TAKE IN STRING
    public void getStringAndPrintIt(String question) {

        String answer = "";
        boolean isValid = false;
        
        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                System.out.println(answerInput);
                isValid = true;

            } catch (NumberFormatException nfe) {
                System.out.println(" Invalid Input. ");

            }

        }

    }

}//CONSOLE IO END

//ConsoleIO flimFlam = new ConsoleIO();
//int test1 = flimFlam.getAndVerifyInt(" Give me an int: ");
//System.out.println(test1);
//int test2 = flimFlam.getAndVerifyIntMinMax(0,100," Give me and int: ");
//System.out.println(test2);
//String test3 = flimFlam.getString(" Give me a string: ");
//System.out.println(test3);
//Float test4 = flimFlam.getFloat(" Give me a float: ");
//System.out.println(test4);
//Float test5 = flimFlam.getFloatMinMax(0, 100, " Give me a float: ");
//System.out.println(test5);
//Double test6 = flimFlam.getDouble(" Give me a double: ");
//System.out.println(test6);
//Double test7 = flimFlam.getDoubleMinMax(0,100, "Give me a double:");
//System.out.println("test7");
        //flimFlam.getStringAndPrintIt(" Give me a string: ");