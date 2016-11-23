/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controllers;

import com.mycompany.vendingmachine.dao.VendingDao;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snack;
import com.mycompany.vendingmachine.ui.ConsoleIO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingController {

    private final ConsoleIO io = new ConsoleIO();
    private final VendingDao dao = new VendingDao();

    public void run() {
        boolean runAgain = true;

        io.print("Welcome to Snack-O-Matic 3000XL!\nThe items listed below\n"
                + "are available for your snacking pleasure.\n==============================\n");
        List<Snack> snacks = listAllAvailableSnacks();
        int snackOptions = snacks.size();

        runAgain = io.askUserYesOrNo("\nDo you want to buy a yummy treat? Enter your answer\n1: Yes\n2: No");

        while (runAgain) {
            double cash = io.getUserInputDoubleMinMax("Please type in the amount of money and press enter", 0, 2147483647);
            int choice = io.getUserInputIntMinMax("\nEnter the number next to the snack you want to buy and press enter:\n", 0, snackOptions + 1);
            Snack aSnack = new Snack();
            aSnack = snacks.get(choice - 1);

            if (cash >= aSnack.getCost()) {
                updateInventory(aSnack);
                double refund = cash - aSnack.getCost();
                if (refund > 0) {
                    Change myChange = makeChange(refund);
                    
                    if (myChange.getQuarter() == 0 && myChange.getDime() == 0 && myChange.getNickel() == 0 && myChange.getPenny()==0) {
                        io.print("You have no change");
                    }
                    else {
                        io.printSameLine("Your change is: ");
                        if (myChange.getQuarter() > 0) {
                        io.printSameLine(myChange.getQuarter() + " quarters ");
                    }
                    if (myChange.getDime() > 0) {
                        io.printSameLine(myChange.getDime() + " dimes ");
                    }
                    if (myChange.getNickel() > 0) {
                        io.printSameLine(myChange.getNickel() + " nickels ");
                    }
                    if (myChange.getPenny() > 0) {
                        io.printSameLine(myChange.getPenny() + " pennies");
                    }
                    }
                    
                }

                io.print("\nEnjoy your " + aSnack.getName() + "!");

            } else {
                io.print("You didn't enter enough money.\n"
                        + "Take your change below and go dig out \n"
                        + "some more change from your couch cushions \n"
                        + "and come back when you can afford our\n"
                        + "fantastic products.");
            }

            runAgain = io.askUserYesOrNo("\nDo you want to buy a yummy treat? Enter your answer\n1: Yes\n2: No");
            if (runAgain) {
                io.print("Welcome to Snack-O-Matic 3000XL!\nThe items listed below\n"
                        + "are available for your snacking pleasure.");
                listAllAvailableSnacks();
                snackOptions = snacks.size();
            }

        }
        io.print("Bye!");
    }

    public List<Snack> listAllAvailableSnacks() {
        List<Snack> snacks = dao.listAllAvailableSnacks();
        int i = 1;
        for (Snack s : snacks) {

            io.print(i + ": " + s.getName() + " $" + s.getCost());
            i++;

        }
        return snacks;
    }

    public Change makeChange(double refund) {
        Change myChange = new Change();
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        BigDecimal bs = new BigDecimal(refund);
        bs = bs.setScale(2, RoundingMode.FLOOR);
        refund = bs.doubleValue();
        
        if (refund >= 0.25) {
            double temp = refund / .25;
            quarters = (int) temp;
            myChange.setQuarter(quarters);
            refund -= quarters * 0.25;
        }
        if (refund < 0.25) {
            double temp = refund / .10;
            dimes = (int) temp;
            myChange.setDime(dimes);
            refund -= dimes * 0.1;
        }
        if (refund < .10) {
            double temp = refund / .05;
            nickels = (int) temp;
            myChange.setNickel(nickels);
            refund -= nickels * 0.05;
        }
        if (refund < .05) {
            BigDecimal bd = new BigDecimal(refund);
            bd = bd.setScale(2, RoundingMode.CEILING);
            refund = bd.doubleValue();
            pennies = (int) (refund * 100);
            myChange.setPenny(pennies);
        }

        return myChange;
    }

    private void updateInventory(Snack aSnack) {

        dao.updateInventory(aSnack);

    }

}
