package com.mycompany.dto;

import com.mycompany.dto.Account;

/**
 *
 * @author apprentice
 */
public class Savings extends Account{
    
    public boolean deposit(Double moneyInput){
        return super.deposit(moneyInput);
    }
    
    public boolean withdraw(Double moneyOutput){
        return super.withdraw(moneyOutput);
    }
    
    public double getBalance(){
        return this.balance;
    }
    
    public void setBalance(double setTo){
        this.setBalance((Double) setTo);
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return the pinNumber
     */
    public Integer getPinNumber() {
        return pinNumber;
    }

    /**
     * @param pinNumber the pinNumber to set
     */
    public void setPinNumber(Integer pinNumber) {
        this.pinNumber = pinNumber;
    }

    /**
     * @return the suspendedFunds
     */
    public Double getSuspendedFunds() {
        return suspendedFunds;
    }

    /**
     * @param suspendedFunds the suspendedFunds to set
     */
    public void setSuspendedFunds(Double suspendedFunds) {
        this.suspendedFunds = suspendedFunds;
    }

    /**
     * @return the isSuspended
     */
    public boolean isIsSuspended() {
        return isSuspended;
    }

    /**
     * @param isSuspended the isSuspended to set
     */
    public void setIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }

    
}
