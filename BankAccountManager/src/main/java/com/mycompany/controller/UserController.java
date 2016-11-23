/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.CheckingAccountDAO;
import com.mycompany.dao.CustomerDAO;
import com.mycompany.dao.SavingsAccountDAO;
import com.mycompany.dto.Customer;
import com.mycompany.dto.Checking;
import com.mycompany.dto.Savings;
import com.mycompany.ui.ConsoleIO;

/**
 *
 * @author apprentice
 */
public class UserController {

    CheckingAccountDAO checkingDAO = new CheckingAccountDAO();
    AdminController admin = new AdminController();
    String firstChoice = null;
    Integer pinInput = 0;
    ConsoleIO console = new ConsoleIO();

    CheckingAccountDAO checkDao = new CheckingAccountDAO();
    SavingsAccountDAO savingsDao = new SavingsAccountDAO();
    CustomerDAO customerDao = new CustomerDAO();

    public void run() {
        console.println("You walk up to a ATM Machine\n");

        boolean keepUsing = false;
        boolean initialPrompt = true;
        boolean isAfterAdmin = false;

        while (initialPrompt) {
            firstChoice = console.getMultiChoiceString("What would you like to do?",
                    new String[]{"Enter PIN", "Enter Admin Mode"}, true, "");

            if (firstChoice.equals("Exit")) {
                keepUsing = false;
                initialPrompt = false;

            } else if (firstChoice.equals("Enter PIN")) {
                pinInput = console.getUserInputInt("What is your pin?:");
                CustomerDAO customerDao = new CustomerDAO();
                boolean isValid = customerDao.isAValidAccount(pinInput);
                    if (isValid) {
                        keepUsing = true;
                        initialPrompt = false;
                    }
                
            } else {
                admin.run();
                isAfterAdmin = true;
            }
            if (isAfterAdmin) {
            } else if (!keepUsing) {
                String consentToRetry = console.getMultiChoiceString("Not a Valid Entry!\n\nWould You Like To Retry?", new String[]{"Retry"}, true, "Cancel");
                if (consentToRetry.equals("Cancel")) {
                    initialPrompt = false;
                }
            }
        }

        while (keepUsing) {

            String userAction = console.getMultiChoiceString("What would you like to do?", new String[]{"Fast "
                + "Cash ($60)", "Withdraw", "Deposit", "Print Balance"}, true, "");
            switch (userAction) {
                case "Fast Cash ($60)":
                    checkingDAO.decode();
                    Checking checkingAccount = checkingDAO.getCheckingAccount(pinInput);
                    checkingAccount.withdraw(60d);
                    checkingDAO.encode();
                    break;

                case "Withdraw":
                    withdraw(pinInput);

                    break;
                case "Deposit":
                    deposit(pinInput);
                    break;
                case "Print Balance":
                    printBalance(pinInput);
                    break;
                case "Exit":
                    
                    break;
            }
        }

    }

    private void withdraw(int pinInput) {
        String userWithdraw = console.getMultiChoiceString("Please choose account to withdraw from:", new String[]{"Checking"
            + "Savings" + "EXIT to Main Menu"});

        switch (userWithdraw) {
            case "Checking":
                Double moneyOut = console.getUserInputDouble("How much do you input?", true, 0);
                Checking account = checkingDAO.getCheckingAccount(pinInput);
                
                boolean consentToSave = console.askUserYesOrNo("Is this action final?");
                if (consentToSave) {
                    boolean completed = account.withdraw(moneyOut);
                    if (!completed) {
                        console.println("Either not enough funds or account is suspended. Ask a manager for assistance");
                    }
                }
                break;

            case "Savings":
                Double moneyOutSavings = console.getUserInputDouble("How much do you input?", true, 0);
                Savings accountSavings = savingsDao.getSavingsAccount(pinInput);
                boolean consentToSaveSavings = console.askUserYesOrNo("Is this action final?");
                if (consentToSaveSavings) {
                    boolean completed = accountSavings.withdraw(moneyOutSavings);
                    if (!completed) {
                        console.println("Either not enough funds or account is suspended. Ask a manager for assistance");
                    }
                }
                break;

            case "EXIT":

                break;
        }
    }

    private void deposit(int pinInput) {
        String userDeposit = console.getMultiChoiceString("Please choose account to deposit:", new String[]{"Checking"
            + "Savings" + "EXIT to Main Menu"});

        switch (userDeposit) {
            case "Checking":
                Double moneyIn = console.getUserInputDouble("How much do you deposit?", true, 0);
                Checking account = checkingDAO.getCheckingAccount(pinInput);
                boolean consentToSave = console.askUserYesOrNo("Is this action final?");
                if (consentToSave) {
                    boolean completed = account.deposit(moneyIn);
                    if (!completed) {
                        console.println("Too much to deposit via ATM. Ask a manager for assistance");
                    }
                }
                break;

            case "Savings":
                Double moneyInSavings = console.getUserInputDouble("How much do you deposit?", true, 0);
                Savings accountSavings = savingsDao.getSavingsAccount(pinInput);
                boolean consentToSaveSavings = console.askUserYesOrNo("Is this action final?");
                if (consentToSaveSavings) {
                    boolean completed = accountSavings.deposit(moneyInSavings);
                    if (!completed) {
                        console.println("Too much to deposit via ATM. Ask a manager for assistance");
                    }
                }
                break;

            case "EXIT":

                break;
        }
    }

    private void printBalance(int pinInput) {
        String userChoice = console.getMultiChoiceString("Please choose account to deposit:", new String[]{"Checking"
            + "Savings" + "EXIT to Main Menu"});
        
        switch(userChoice){
            case "Checking":
                console.println(checkDao.accountToDisply(pinInput));
                break;
            case "Savings":
                console.println(savingsDao.accountToDisply(pinInput));
                break;
            case "EXIT to Main Menu":
                break;
        }
    }

}
