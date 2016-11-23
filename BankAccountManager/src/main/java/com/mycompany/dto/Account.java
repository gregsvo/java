package com.mycompany.dto;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public abstract class Account {

    protected Double balance;
    protected Integer pinNumber;
    protected Double suspendedFunds = 0d;
    protected boolean isSuspended;
    

    protected boolean withdraw(Double moneyOutput) {
        if (moneyOutput <= balance) {
            this.balance -= moneyOutput;
            return true;
        }else{
            return false;
        }
    }

    protected boolean deposit(Double moneyInput) {
        if (moneyInput > 10000) {
            return false;
        } else {
            this.balance += moneyInput;
            return true;
        }
    }

}
