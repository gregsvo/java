
import java.util.Scanner;



public class InterestCalcConsoleIO {
    Scanner sc = new Scanner(System.in);
    
    private Float compoundRate;
    private int compoundPeriods;
    
    
    
    //Get and verify beginning balance user input from app
    public Float getFloatMinMax(String question) {
        
        Float answer = 0f;
        Boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(question);
                String answerInput = sc.nextLine();
                answer = Float.parseFloat(answerInput);

                if (answer > 0) {
                    isValid = true;
                    
                } else {
                    System.out.println(" Your input must be more than zero.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println(" You didn't enter a number.");
            }
        }

        return answer;
    }    
 
    
    
    //Get and verify compounding rate user input from app
    public int getCompoundSwitchChoice(String question){
        boolean isValid = false;
        int compoundSwitchChoice = 0;
        Float compoundRate = 0f;
        while (!isValid) {
                try {
                    System.out.println(question);
                    String input = sc.nextLine();
                    compoundSwitchChoice = Integer.parseInt(input);
                        if ((compoundSwitchChoice == 1)||(compoundSwitchChoice == 2)||(compoundSwitchChoice == 3)){
                            isValid = true;
                        }
                        else {System.out.println( "Please choose either 1, 2, or 3. ");}
                        
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid menu choice");
                }
            }
            return compoundSwitchChoice;
        }

    
 
}//END of InterestCalcConsoleIO
