package com.mycompany.dto;

import com.mycompany.dto.Account;

public class Checking extends Account {

    private int overDraftCounter = 0;
    
    public boolean deposit(Double moneyInput){
        return super.deposit(moneyInput);
    }
    

    @Override
    public boolean withdraw(Double moneyOutput) {
        if (moneyOutput <= getBalance()) {
            this.setBalance((Double) getBalance() - moneyOutput);
            return true;
        } else if (moneyOutput > getBalance() && getOverDraftCounter() < 11 && moneyOutput <=100) {
            this.setBalance((Double) getBalance() - moneyOutput);
            setOverDraftCounter(getOverDraftCounter() + 1);
            return true;
        }else{
            return false;
        }
    }

    /**
     * @return the overDraftCounter
     */
    public int getOverDraftCounter() {
        return overDraftCounter;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @param overDraftCounter the overDraftCounter to set
     */
    public void setOverDraftCounter(int overDraftCounter) {
        this.overDraftCounter = overDraftCounter;
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
