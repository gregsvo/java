/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.CheckingAccountDAO;
import com.mycompany.dao.CustomerDAO;
import com.mycompany.dao.SavingsAccountDAO;
import com.mycompany.dto.Checking;
import com.mycompany.dto.Customer;
import com.mycompany.dto.Savings;
import com.mycompany.ui.ConsoleIO;

/**
 *
 * @author apprentice
 */
public class AdminController  {

    String adminPassword = "666";
    CustomerDAO customerDao = new CustomerDAO();
    protected ConsoleIO console = new ConsoleIO();
    
    CheckingAccountDAO checkDao = new CheckingAccountDAO();
    SavingsAccountDAO savingsDao = new SavingsAccountDAO();

    public void run() {
        String passwordCheck = console.getUserInputString("Please enter admin password:   ");

        if (!passwordCheck.equals(adminPassword)) {

        } else {
            boolean keepUsing = true;
            while (keepUsing) {
                String adminOptions = console.getMultiChoiceString("What would you like to do?", new String[]{
                    "Create New Customer", "Remove Customer", "Update Customer"}, true, "");
                switch (adminOptions) {

                    case "Create New Customer":

                        createCustomer();
                        break;

                    case "Remove Customer":
                        removeCustomer();
                        break;

                    case "Update Customer":

                        String updatable = console.getMultiChoiceString("Which customer would you like to update?",
                                customerDao.existingCustomers(), true, "");
                        if (updatable.equals("Exit")) {
                            break;
                        } else {
                            String[] adminChoice = updatable.split(": ");
                            Integer pinNumberChoice = Integer.valueOf(adminChoice[0]);
                            boolean keepUpdating = true;
                            while (keepUpdating) {
                                String howToUpdate = console.getMultiChoiceString("What would you like to update?",
                                        new String[]{"Add Checking Account", "Add Savings Account", "Change Checking Account", "Change Savings Account", "UnFreeze Account"}, true, "");
                                switch (howToUpdate) {
                                    case "Add Checking Account":
                                        Checking account = new Checking();
                                        Double initialBalance = console.getUserInputDouble("Enter new checking account starting balance: ", true, 0);
                                        account.setBalance(initialBalance);
                                        checkDao.createCheckingAccount(pinNumberChoice, account);
                                        break;

                                    case "Add Savings Account":
                                        Savings savings = new Savings();
                                        Double initialSavings = console.getUserInputDouble("Enter new savings account starting balance: ", true, 0);
                                        savings.setBalance(initialSavings);
                                        savingsDao.createSavingsAccount(pinNumberChoice, savings);

                                        break;
                                    case "Change Checking Account":
                                        changeCheckingAccount(pinNumberChoice);
                                        break;
                                    case "Change Savings Account":
                                        changeSavingsAccount(pinNumberChoice);
                                        break;
                                    case "UnFreeze Account":
                                        unfreeze(pinNumberChoice);
                                        break;
                                    case "Exit":
                                        keepUpdating = false;
                                        break;
                                }

                            }
                        }

                        break;

                    case "Exit":
                        keepUsing = false;
                        break;
                }
            }
        }
    }

    private void changeCheckingAccount(Integer pinNumberChoice) {
        Double newCheckingBalance = console.getUserInputDouble("Whats the new balance?");
        Checking updatedChecking = checkDao.getCheckingAccount(pinNumberChoice);
        updatedChecking.setBalance(newCheckingBalance);
        checkDao.updateCheckingAccount(pinNumberChoice,updatedChecking);
    }

    private void changeSavingsAccount(Integer pinNumberChoice) {
        Savings updatedSavings = savingsDao.getSavingsAccount(pinNumberChoice);
        Double newSavingsBalance = console.getUserInputDouble("Whats the new balance?");
        updatedSavings.setBalance(newSavingsBalance);
        savingsDao.updateSavingsAccount(pinNumberChoice, updatedSavings);
    }

    private void unfreeze(Integer pinNumberChoice) {
        Savings toUnfreeze = savingsDao.getSavingsAccount(pinNumberChoice);
        toUnfreeze.setIsSuspended(false);
        double suspendedBalance = toUnfreeze.getSuspendedFunds();
        double actualBalance = toUnfreeze.getBalance();
        toUnfreeze.setSuspendedFunds(0d);
        toUnfreeze.setBalance(suspendedBalance + actualBalance);
    }

    private void removeCustomer() {
        int pinInput = console.getUserInputInt("Who would you like to delete? (ENTER PIN NUMBER): ");
        boolean keepUsing = true;
        Customer preSave = null;
        while (keepUsing) {

            boolean trigger = false;
            for (Customer existingCustomer : customerDao.list()) {
                if (existingCustomer.getPinNumber() == pinInput) {

                    preSave = existingCustomer;
                    trigger = true;

                }
            }

            if (!trigger) {
                String consentToRetry = console.getMultiChoiceString("Not an existing customer!\n\nWould"
                        + "you like to try again?", new String[]{"YES", "NO"});
                if (consentToRetry.equals("NO")) {
                    keepUsing = false;
                }
            } else {
                String consentToDelete = console.getMultiChoiceString("Would you like to save these changes?", new String[]{"YES", "NO"});
                if (consentToDelete.equals("YES")) {
                    customerDao.removeCustomer(preSave.getPinNumber());
                    keepUsing = false;
                } else {
                    keepUsing = false;
                }
            }
        }
    }

    private void createCustomer() {
        String lastName = console.getUserInputString("What is the customers last name?");
        String firstName = console.getUserInputString("What is the customers first name?");
        Customer newCustomer = new Customer(lastName, firstName);
        customerDao.createCustomer(newCustomer);

    }

}
