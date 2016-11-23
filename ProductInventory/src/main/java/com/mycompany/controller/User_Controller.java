/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.dao.PhoneDAO;
import com.mycompany.dao.ComputerDAO;
import com.mycompany.dao.TabletDAO;
import com.mycompany.dao.iDAO;
import com.mycompany.dto.Phone;
import com.mycompany.ui.ConsoleIO;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class User_Controller {

    private final ConsoleIO console = new ConsoleIO();

    private final iDAO computerDAO = new ComputerDAO();
    private final iDAO phoneDAO = new PhoneDAO();
    private final iDAO tabletDAO = new TabletDAO();
    private iDAO productToDo;
    private final AdminController admin = new AdminController();

    private final List<iDAO> daos = new ArrayList<>();

    public void run() {

        daos.add(phoneDAO);
        daos.add(tabletDAO);
        daos.add(computerDAO);
        boolean validDAO = false;

        while (!validDAO) {
            String whatProduct = console.getMultiChoiceString("Welcome Inventory User:\n\n"
                    + "Please choose a product?: ",
                    new String[]{"Phone", "Computer", "Tablet"});

            productToDo = daos.stream().filter(dao -> dao.getName().equals(whatProduct)).findFirst().get();

            if (productToDo == null) {
                console.println("\n\n**Not a valid choice. Check the dao getName or the String[]**\n\n");

            } else {
                validDAO = true;
            }
        }
        if (console.getTrueFalse("Mode:\n\t(Y)Admin Mode \n\t(N)User\n>>> ")) {
            admin.run(productToDo);
        }
        boolean userModeRunning = true;
        while (userModeRunning) {
            whatNeedsRestocked();

            String prodN = productToDo.getName();

            String whatToDo = console.getMultiChoiceString("What would you like to do?: ",
                    new String[]{"Add Product To Inventory", "Remove Product From Inventory",
                        "Calculate Product Price", "Calculate Inventory Price"}, true, "");

            switch (whatToDo) {
                case "Add Product To Inventory":
                    boolean validEntry = addProductToInventory(prodN);
                    if (!validEntry) {
                        console.println("\n**No successful addition**\n");
                    }
                    break;
                case "Remove Product From Inventory":
                    removeProductInventory(prodN);
                    break;
                case "Calculate Product Price":
                    priceCalc(prodN);
                    break;
                case "Calculate Inventory Price":
                    calcInvPrice(prodN);

                    break;
                case "Exit":

                    break;
            }
        }

    }

    private void calcInvPrice(String prodN) {
        try {
            
            Method currentPrice = Arrays.stream(Phone.class.getMethods())//\\***********//START\\*******//
                    .filter(meth -> meth.getName().contains("getPric"))
                    .findFirst()
                    .get();

            Method getCurrentStock = Arrays.stream(Phone.class.getMethods())
                    .filter(meth -> meth.getName().contains("getCurrentStock"))
                    .findFirst()
                    .get();
            // \\***********//END\\****************//

            String productName = console.getMultiChoiceString("Choose A " + prodN,
                    productToDo.getExistingProducts(), true, "");

            if (!productName.equals("Exit")) {
                
                Phone currentProductToDo = productToDo.get(productName);

                Double indivPrice = (Double) currentPrice.invoke(currentProductToDo);
                Integer currentStock =(Integer) getCurrentStock.invoke(currentProductToDo);
                
                double price = indivPrice*currentStock;

                String line = (String) "The price of the " + productName + " Inventory is: "
                        + price;

                console.println(line +"\n\n");
                
                Thread.sleep(1500);
            }
        } catch (Exception ex) {
            console.println("Bug Alert!!");
        }
    }

    private void priceCalc(String prodN) {
        String productName = console.getMultiChoiceString("Choose A " + prodN,
                productToDo.getExistingProducts(), true, "");
        if (!productName.equals("Exit")) {
            try {
                Phone currentProductToDo = productToDo.get(productName);

                Method currentPrice = Arrays.stream(Phone.class.getMethods())
                        .filter(meth -> meth.getName().contains("getPric"))
                        .findFirst()
                        .get();
                Method currentStock = Arrays.stream(Phone.class.getMethods())
                        .filter(meth -> meth.getName().contains("getCurrentSt"))
                        .findFirst()
                        .get();

                Double price = (Double) currentPrice.invoke(currentProductToDo);// * (Double) currentStock.invoke(currentProductToDo)))
                console.println("The price of this " + productName + " is: " + price+"\n\n");
                Thread.sleep(1500);
            } catch (Exception ex) {
                console.println("Bug Alert!");
            }

        }
    }

    private void whatNeedsRestocked() {
        try {
//***** CODE STARTS HERE  ********//    
            Method currentStock = Arrays.stream(Phone.class.getMethods())
                    .filter(meth -> meth.getName().contains("getCurrent"))
                    .findFirst()
                    .get();

            Method endangeredStock = Arrays.stream(Phone.class.getMethods())
                    .filter(meth -> meth.getName().contains("getEndan"))
                    .findFirst()
                    .get();
            Method prodName = Arrays.stream(Phone.class.getMethods())
                    .filter(meth -> meth.getName().contains("getProd"))
                    .findFirst()
                    .get();

            List<Object> products = Arrays.stream(productToDo.getExistingProducts())
                    .map(pName -> productToDo.get(pName)).collect(Collectors.toList());
//****** CODE ENDS HERE ====kinda??====//
            for (Object currentPhone : products) {

                int current = (Integer) currentStock.invoke(currentPhone);
                int lowLimit = (Integer) endangeredStock.invoke(currentPhone);

                if (current <= lowLimit) {

                    String nameDisplay = (String) prodName.invoke(currentPhone);

                    console.println("***Currently : " + nameDisplay
                            + " Needs Restocked: Current Value: " + current + " !!!****");
                }
            }
            console.println("\n");

        } catch (Exception ex) {

            console.println("Bug Alert!!");
        }

    }

    private boolean addProductToInventory(String productName) {
//***********CODE STARTS HERE
        switch (productName) {
            case "Phone":
                String phoneNameTo = console.getMultiChoiceString(//grabbing the name of the phone
                        "Which phone would you like to add to?", productToDo.getExistingProducts());

                Phone phoneTo = productToDo.get(phoneNameTo);//retrieving the phone
                //howMany they want to add.
                int howMany = console.getUserInputInt("There are " + phoneTo.getCurrentStock()
                        + " out of " + phoneTo.getMaxStock() + ".\nHow many would you like to add?: ");

                //if the input would make it go over the Max Stock
                if (phoneTo.getMaxStock() < (howMany + phoneTo.getCurrentStock())) {
                    //auto fill to the max stock prompt
                    if (console.getTrueFalse("You've Exceeded The Max Amount!\nWould You Like To Set "
                            + "It To The Max?")) {

                        howMany = phoneTo.getMaxStock() - phoneTo.getCurrentStock();

                    } else {
                        //they didn't want to change it
                        return false;
                    }
                }

                if (console.getTrueFalse("Would You Like To Save?:\n> ")) {//save prompt
                    phoneTo.setCurrentStock(phoneTo.getCurrentStock() + howMany);
//*****************CODE ENDS HERE
                    return true;
                }

                break;//put your case below.
        }

        return false;
    }

    private boolean removeProductInventory(String productName) {

        switch (productName) {
            case "Phone":
                String phoneNameTo = console.getMultiChoiceString(//grabbing the name of the phone
                        "Which phone would you like to add to?", productToDo.getExistingProducts());

                Phone phoneTo = productToDo.get(phoneNameTo);//retrieving the phone
                //howMany they want to subtract.
                int howMany = console.getUserInputInt("There are " + phoneTo.getCurrentStock()
                        + " out of " + phoneTo.getMaxStock() + ".\nHow many would you like to remove?: ");

                boolean tooMuch = phoneTo.getCurrentStock() - howMany < 0;
                if (tooMuch) {
                    if (console.getTrueFalse("Would You Like To Set It Too Zero?")) {
                        phoneTo.setCurrentStock(0);
                    } else {
                        return false;
                    }
                }
                int beforeSave = phoneTo.getCurrentStock() - howMany;
                if (console.getTrueFalse("Would You Like To Save?:(cur... " + beforeSave + ")." + "\n> ")) {//save prompt
                    phoneTo.setCurrentStock(beforeSave);
                    return true;
                }

                break;
        }

        return false;
    }

}
