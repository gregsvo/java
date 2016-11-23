
public class InterestCalcCalculator {

    //Calculate compound frequency
    public int getCompoundFrequency(int compoundSwitchChoice) {
        boolean isValid = false;
        int compoundFrequency = 0;
        while (!isValid) {

            switch (compoundSwitchChoice) {
                case 1:
                    compoundFrequency = 4;
                    isValid = true;
                    break;
                case 2:
                    compoundFrequency = 12;
                    isValid = true;
                    break;
                case 3:
                    compoundFrequency = 365;
                    isValid = true;
                    break;
                default:
                    System.out.println("Please enter a valid menu choice.");
            }
        }

        return compoundFrequency;
    }
    
    //Calculate yearly return rate
    public Float getCompoundYearlyRate(int compoundSwitchChoice, float rate) {
        boolean isValid = false;
        Float compoundYearlyRate = 0f;

        switch (compoundSwitchChoice) {
            case 1:
                compoundYearlyRate = (rate / 4);
                isValid = true;
                break;
            case 2:
                compoundYearlyRate = (rate / 12);
                isValid = true;
                break;
            case 3:
                compoundYearlyRate = (rate / 365);
                isValid = true;
                break;
        }

        return compoundYearlyRate;
    }
    
    //Calculate interest earned yearly
    public Float getInterestEarned(float beginBalance, float compoundRate, float compoundFrequency) {

        Float currentBalance;

        Float endBalance;
        Float interestEarned = 0f;

        //compoundedRate = (rate / compounded);
        currentBalance = beginBalance;
        //for (int i=1; i<=years; i++) {
        for (int n = 0; n < compoundFrequency; n++) {
            currentBalance *= (1 + compoundRate / 100);

            endBalance = currentBalance;
            interestEarned = endBalance - beginBalance;

            //  }
        }
        return interestEarned;
    }
    
    //Calculate end balance of year
    public Float getYearEndBalance(float beginBalance, float compoundRate, float compoundFrequency) {

        Float currentBalance;

        Float endBalance = 0f;
        Float interestEarned = 0f;

        //compoundedRate = (rate / compounded);
        currentBalance = beginBalance;
        //for (int i=1; i<=years; i++) {
        for (int n = 0; n < compoundFrequency; n++) {
            currentBalance *= (1 + compoundRate / 100);

            endBalance = currentBalance;
            interestEarned = endBalance - beginBalance;

            //  }
        }
        return endBalance;
    }

}//END of InterestCalcCalculator
