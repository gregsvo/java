
//AUTHOR: GREG SVOBODA

package com.mycompany.interestcalculatormethodized;


import java.util.Scanner;



//-----------------------------MAIN

public class interestCalculatorMETHODIZED {
    
     public static void main(String[] args) {

        float principal = getPrincipalAmount();
        float years = getTimeInBank();
        float rate = getInterestRate();
        calculateEarnings((float) rate, (float) principal, (float) years);

}//-----------------------------MAIN------END 


//-----------------------------GET PRINCIPAL AMOUNT  
        public static Float getPrincipalAmount(){
        
        String principalInput = "";    
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        float principal = 0;
        
        while (!isValid) {
            try {
                System.out.println("What is the principal amount?");
                principalInput = sc.nextLine();
                principal = Float.parseFloat(principalInput);
                
                if  (principal > 0 )
                    isValid = true;
                else
                    System.out.println("You're broke. Compounding interest can't help you.");
            }
            catch (NumberFormatException nfe) {
                System.out.println("You didn't enter a number.");
            }
        }

            return principal;
            
        }//-----------------------------GET PRINCIPAL AMOUNT------END 

        
//-----------------------------GET TIME IN BANK     
        public static Float getTimeInBank(){  
            
        String yearsInput = "";
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        float years = 0;
        
            
        while (!isValid) {
            try {
                System.out.println("How many years will the money stay in the bank?");
                yearsInput = sc.nextLine();
                years = Float.parseFloat(yearsInput);
                
                if (years > 0 )
                    isValid = true;
                else
                    System.out.println(" 0 years? Really? Patience = Money!");
            }
            catch (NumberFormatException nfe) {
                System.out.println("You didn't enter a number.");
            }
        }
                 return years;
         }//-----------------------------GET TIME IN BANK------END 
 
 //-----------------------------GET INTEREST RATE     
        public static Float getInterestRate(){
            
        String rateInput = "";
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        float rate = 0;
        
        while (!isValid) {
            try {
                System.out.println("What is the interest rate?");
                rateInput = sc.nextLine();
                rate = Float.parseFloat(rateInput);
                
                if (rate > 0 )
                    isValid = true;
                else
                    System.out.println("0 interest? That's the worst savings account EVER! Enter more than 0% interest.");
            }
            catch (NumberFormatException nfe) {
                System.out.println("You didn't enter a number.");
            }
        }
                return rate;
        } //-----------------------------GET INTEREST RATE------END 

 //-----------------------------CALCULATE EARNINGS BASED ON USER INPUT
        public static void calculateEarnings(float rate, float principal, float years ){
            
        float compoundPeriods = 0;
        float compoundedRate = 0;
        float beginBalance, currentBalance, endBalance, interestEarned;
        String compoundedInput = "";
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        int compounded, i, n;


        while (!isValid) {
            try {
                System.out.println("Please enter the code number for how often"
                        + " you want the interest compounded:"
                        + "\n1: Quarterly \n"
                        + "2: Monthly \n"
                        + "3: Daily \n");
                compoundedInput = sc.nextLine();
                compounded = Integer.parseInt(compoundedInput);
                switch (compounded) {
           
                    case 1:
                        compoundedRate = rate/4;
                        isValid = true;
                        compoundPeriods = 4;
                        break;
                    case 2:
                        compoundedRate = rate/12;
                        isValid = true;
                        compoundPeriods = 12;
                        break;
                    case 3:
                        compoundedRate = rate/365;
                        isValid = true;
                        compoundPeriods = 365;
                        break;
                    default:
                        System.out.println("Please enter a valid menu choice.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid menu choice");
            }
        }

 //Calculation by nested loops

        beginBalance = principal;
        //compoundedRate = (rate / compounded);
        currentBalance = principal;
        for (i=1; i<=years; i++) {
            for (n = 0; n < compoundPeriods; n++) {
                currentBalance *=(1+compoundedRate/100);  
            }
            
//Set END OF YEAR Balance to be printed            
            
            endBalance = currentBalance;
            interestEarned = endBalance - beginBalance;
            
//-----------------------------PRINT FINAL REPORT (TO GO AT THE TOP BELOW METHOD CALLS)             
            
            System.out.println("Year: " + i + " Beginning Balance: $" 
                    + beginBalance + " Interest Earned: $" 
                    + interestEarned + " Year-End Balance: $" + endBalance);
            beginBalance = currentBalance;
        }           
        
        }  //-----------------------------getCompoundFrequency()  END

}//END OF INTEREST CALC

