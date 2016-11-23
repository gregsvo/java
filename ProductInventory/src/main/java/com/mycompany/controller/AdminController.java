/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.iDAO;
import com.mycompany.ui.ConsoleIO;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 *
 * @author apprentice
 */
public class AdminController {

    ConsoleIO console = new ConsoleIO();
    iDAO productToDo;

    public void run(iDAO productDAO) {
        productToDo = productDAO;
        boolean keepUsing = true;
        while (keepUsing) {
            String toDo = console.getMultiChoiceString("Choose(" + productToDo.getName() + " mode):", new String[]{"Create", "Remove", "Update", "Exit"});
            switch (toDo) {
                case "Create":
                    createPhone();
                    break;
                case "Update":
                    updatePhone();
                    break;
                case "Remove":
                    removePhone();
                    break;
                case "Exit":
                    keepUsing = false;
                    break;
            }
        }
    }

    private void removePhone() {
        String toRemove = console.getMultiChoiceString("Which Phone Would You Like To Remove?:",
                productToDo.getExistingProducts(), true, "Return");
        if (!toRemove.equals("Return")) {
            if (console.getTrueFalse("Would You Like To Save This Decision?")) {
                productToDo.delete(toRemove);
            }
        }
    }

    private void updatePhone() {
        boolean keepUpdating = true;
        boolean validUpdate;

        while (keepUpdating) {
            //Start building my 
            String product = console.getMultiChoiceString("Which Phone Would You Like To Update?",
                    productToDo.getExistingProducts());

            String whatToUpdate = console.getMultiChoiceString("What Would You Like To Do?: ",
                    productToDo.getMethods());

            String updateTo = console.getUserInputString("What Would You Like To Update It To?: ");

            try {
                validUpdate = productToDo.update(new String[]{product, whatToUpdate, updateTo});

                if (validUpdate) {

                    keepUpdating = console.getTrueFalse("**Update Saved!**\n\nWould You Like To Keep Updating?\n> ");

                } else {

                    console.println("Not A Valid Entry\n");

                    if (!console.getTrueFalse("Would You Like To Try Again?: \n> ")) {
                        keepUpdating = false;
                    }
                }
            } catch (Exception ex) {
                return;
            }

        }
    }

    private void createPhone() {
        try {
            String[] inherited = productInheritedFields(productToDo);
            if (inherited != null) {
                String[] specific = productSpecificFields(productToDo.getName());
                if (specific != null) {
                    productToDo.create(concatenate(inherited, specific));
                }
            }
        } catch (Exception excep) {
            console.println("Broke my array generator! Bug Alert!");
        }
    }

    //each product needs this.
    private String[] productInheritedFields(iDAO productToDo) {
        String endangeredStock;
        String max;
        String price;
        String currentStock;

        String phoneName = console.getUserInputString("What's the name?: ");
        if (Arrays.stream(productToDo.getExistingProducts()).anyMatch(pN -> pN.equals(phoneName))) {
            //Can't add a product that already exists, 
            //.getExistingProducts() is super important to have done right!
            console.println("Already exists!!!");
            return null;
        } else {
            do {
                price = console.getUserInputString("What's the price?($DD.cc): ");  //does this until its valid

            } while (!validInput(price, true));
            do {
                max = console.getUserInputString("What's the max Inventory?: ");  //does this until its valid

            } while (!validInput(max, false));
            do {
                endangeredStock = console.getUserInputString("How many left to trigger notification?: ");  //does this until its valid

            } while (!validInput(endangeredStock, false));
            do {
                currentStock = console.getUserInputString("Current Stock: ");  //does this until its valid

            } while (!validInput(currentStock, false));

            if (Integer.parseInt(currentStock) > Integer.parseInt(max)) {//sets it to the max if over
                currentStock = max;
            }
            //Prints info and asks to save:
            if (console.getTrueFalse("Ok To Save?:\n      Name: " + phoneName + "\n      Price: " + price
                    + "\n      Current Stock: " + currentStock + "\n      Max: " + max + "\n      Trigger when: " + endangeredStock + "\n> ")) {
                if (price.substring(0, 1).matches("\\D")) { //removes any non didget character at the beginning 
                    price = price.substring(1);
                }
                //first half of your Product constructor. 
                return new String[]{phoneName, price, max, endangeredStock, currentStock};
                //Your constructor must have these as the first four in this order!
            }
        }
        return null;
    }

    private boolean validInput(String input, boolean isDouble) {
        boolean keepTrying = true;
        while (keepTrying) {
            try {
                //try it always as int first
                return Integer.parseInt(input) > 0;//return true if no exceptions
            } catch (Exception ex) {
                try {
                    if (isDouble) {  //if there's exceptions and you want a Double test

                        if (input.substring(0, 1).matches("[0-9]")) { //allows the user to not input a $
                            input = "$" + input;
                        }
                        double answer = Double.parseDouble(input.substring(1, input.length()));
                        return answer > 0;//skips the first charachter
                        //checks if above zero
                    } else { //if you didn't want it checked as a Double
                        console.println("Not a valid Entry!!!\n");
                        return false;
                    }
                } catch (Exception exc) { //if it tries and parse a Double and can't
                    if (input.matches("[0-9]\\.\\d")) {
                        float answer = Float.parseFloat(input);
                        return answer > 0;
                    }
                    console.println("Not a valid Entry!!!\n");
                    return false;

                }
            }
        }
        return false;
    }

    //look at what is unique to your product;
    private String[] productSpecificFields(String productName) {
        switch (productName) {
            case "Phone":
// 
                boolean notValid = true;
                while (notValid) {
                    boolean consentToSave;

                    String carrier;
                    float screenSize;
                    boolean is4g;

                    carrier = console.getMultiChoiceString("Cell Service Provider", new String[]{"Verizon", "At&t", "Sprint"});
                    screenSize = console.getUserInputFloat("Screen Size (inches)?: ", 2.45f, 10);
                    is4g = console.getTrueFalse("Is it 4g?: ");//very similar to up top. Use console functions now though.

                    consentToSave = console.getTrueFalse("Would you like to save?:\n    Carrier: "
                            + carrier + "\n    Screen Size: " + screenSize + "\n    4g Compatible: " + is4g + "\n> ");

                    if (consentToSave) {
                        //another constructor heavy String[] must match the second half.
                        return new String[]{String.valueOf(screenSize), String.valueOf(is4g), carrier};

                    } else {
                        //I gave them a chance to reEnter these specific fields
                        boolean retry = console.getTrueFalse("Would you like to re-enter? ");
                        if (!retry) {
                            notValid = false;
                        }
                    }

                }

                break;
            case "Computer":
                break;
            case "Tablet":
                break;
        }
        return null;
    }

    public String[] concatenate(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        String[] c = (String[]) Array.newInstance(String.class, aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

}
