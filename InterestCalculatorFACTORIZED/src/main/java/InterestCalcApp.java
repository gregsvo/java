
public class InterestCalcApp {

    public static void main(String[] args) {

        //Set Variables        
        InterestCalcConsoleIO console = new InterestCalcConsoleIO();
        InterestCalcCalculator calc = new InterestCalcCalculator();
        Float principal, years, rate, compoundRate;
        int compoundSwitchChoice, compoundFrequency;

        //Get and verify beginning balance
        principal = console.getFloatMinMax(" What is your beginning balance? ");

        //Get and verify length of time in bank            
        years = console.getFloatMinMax(" How many years will the money stay in the bank? ");

        //Get and verify interest rate        
        rate = console.getFloatMinMax("What is your interest rate? ");

        //Get and verify compound frequency       
        compoundSwitchChoice = console.getCompoundSwitchChoice("Please enter the code number for how often"
                + " you want the interest compounded:"
                + "\n1: Quarterly \n"
                + "2: Monthly \n"
                + "3: Daily \n");

        //All necessary values have been entered, begin calculation of values.                                            
        //Get and calculate compound rate
        compoundFrequency = calc.getCompoundFrequency(compoundSwitchChoice);

        //Calculate compound rate based on user input      
        compoundRate = calc.getCompoundYearlyRate(compoundSwitchChoice, rate);

        //Set variables and print    
        float yearBeginBalance = principal;
        float interestEarned = 0;
        float yearEndBalance = 0;
        int year;
        for (int i = 0; i < years; i++) {
            year = i + 1;

            System.out.println(" ***** YEAR " + year + "*****");

            System.out.println(" Beginning Balance: $" + yearBeginBalance);

            interestEarned = calc.getInterestEarned(yearBeginBalance, compoundRate, compoundFrequency);

            System.out.println(" Interest Earned: $" + interestEarned);

            yearEndBalance = calc.getYearEndBalance(yearBeginBalance, compoundRate, compoundFrequency);

            System.out.println(" End Balance: $" + yearEndBalance);

            System.out.println("");

            yearBeginBalance = yearEndBalance;

        }

    }//END OF MAIN

}//END OF InterestCalcApp
