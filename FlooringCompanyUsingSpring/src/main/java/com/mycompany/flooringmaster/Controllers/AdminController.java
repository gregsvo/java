/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.Controllers;

import com.mycompany.flooringmaster.DAOs.ConfigDao;
import com.mycompany.flooringmaster.DAOs.OrderDao;
import com.mycompany.flooringmaster.DAOs.ProductDao;
import com.mycompany.flooringmaster.DAOs.TaxesDao;
import com.mycompany.flooringmaster.UI.ConsoleIO;
import java.util.Arrays;

/**
 *
 * @author apprentice
 */
public class AdminController {

    private final ConfigDao configDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final TaxesDao taxesDao;
    private final ConsoleIO io;

    public AdminController(ConfigDao configDao, TaxesDao taxesDao, ProductDao productDao, OrderDao orderDao, ConsoleIO io) {
        this.configDao = configDao;
        this.productDao = productDao;
        this.taxesDao = taxesDao;
        this.orderDao = orderDao;
        this.io = io;
    }

    public void print() {
        int adminChoice;
        do {
            adminChoice = io.getMultiChoiceInt("Admin Menu", new String[]{"Update States", "Update Products", "Toggle Test Mode (currently " + (configDao.getTestMode() ? "Testing" : "Production") + ")", "Return"});
            switch (adminChoice) {
                case 1:
                    updateStates();
                    break;
                case 2:
                    updateProducts();
                    break;
                case 3:
                    configDao.updateTestMode();
                    productDao.setup(configDao.getTestMode());
                    taxesDao.setup(configDao.getTestMode());
                    orderDao.setup(configDao.getTestMode());
                    break;
                case 4:
                    return;
            }
        } while (adminChoice != 4);

    }

    private void updateStates() {
        int statesChoice;
        do {
            int i = 0;
            for (String holder : taxesDao.getStatesTaxes()) {
                if (i % 5 == 0) {
                    io.println("");
                }
                io.print("   \t" + holder);
                i++;
            }
            statesChoice = io.getMultiChoiceInt("\nUpdate States Menu", new String[]{"Add State", "State Name", "Tax Rate", "Remove State", "Return"});
            switch (statesChoice) {
                case 1:
                    addState();
                    break;
                case 2:
                    renameState();
                    break;
                case 3:
                    changeTaxRate();
                    break;
                case 4:
                    removeState();
                    break;
                case 5:
                    break;
            }
        } while (statesChoice != 5);
    }

    @SuppressWarnings("empty-statement")
    private void addState() {
        String newStateName = "";
        double newTaxRate = -1.0;
        do {
            newStateName = io.getUserInputString("Enter a two character state abbreviation: ").toUpperCase().replaceAll("[^A-Z]", "");
        } while ((Arrays.asList(taxesDao.getStates()).contains(newStateName)) || !(newStateName.length() == 2));
        newTaxRate = io.getUserInputDouble("Enter state tax percent rate: ", true, 0, true);
        taxesDao.createState(newStateName, newTaxRate);
    }

    private void renameState() {
        String newStateName;
        String oldStateName = io.getMultiChoiceListString("Select a state", taxesDao.getStates(), true, "Return", 5).toUpperCase();
        if (oldStateName.equals("Return")) {
            return;
        }
        do {
            newStateName = io.getUserInputString("Enter a two character state abbreviation: ").toUpperCase().replaceAll("[^A-Z]", "");
        } while ((Arrays.asList(taxesDao.getStates()).contains(newStateName)) || !(newStateName.length() == 2));
        taxesDao.renameState(oldStateName, newStateName);
        io.println(oldStateName + " successfully renamed to " + newStateName);

    }

    private void changeTaxRate() {
        double newStateTax;
        String stateName = io.getMultiChoiceListString("Select a state", taxesDao.getStates(), true, "Return", 5).toUpperCase();
        if (stateName.equals("Return")) {
            return;
        }
        newStateTax = io.getUserInputDouble("Enter a state tax percent rate: ", true, 0, true);
        taxesDao.changeStateTax(stateName, newStateTax);
        io.println(stateName + "'s tax rate successfully changed to " + newStateTax);

    }

    private void removeState() {
        String removeState = io.getMultiChoiceListString("Select a state", taxesDao.getStates(), true, "Return", 5).toUpperCase();
        if (Arrays.asList(taxesDao.getStates()).contains(removeState) && !(removeState.equals("Return"))) {
            taxesDao.removeState(removeState);
            io.println(removeState + " successfully removed");
        }
    }

    private void updateProducts() {
        int productsChoice;
        do {
            for (String holder : productDao.getProductsCosts()) {
                io.println(holder);
            }
            productsChoice = io.getMultiChoiceInt("\nUpdate Products Menu", new String[]{"Add Product", "Product Name", "Material Cost", "Labor Cost", "Remove Product", "Return"});
            switch (productsChoice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    renameProduct();
                    break;
                case 3:
                    changeMaterialCost();
                    break;
                case 4:
                    changeLaborCost();
                    break;
                case 5:
                    removeProduct();
                    break;
                case 6:
                    break;
            }
        } while (productsChoice != 6);
    }

    private void addProduct() {
        String newProductName = "";
        double newLaborCostSqFt = -1.0, newMaterialCostSqFt = -1.0;
        do {
            newProductName = io.getUserInputString("Enter a new product name: ");
        } while ((Arrays.asList(productDao.getProducts()).contains(newProductName)) || newProductName.equals(""));
        newLaborCostSqFt = io.getUserInputDouble("Enter new labor cost/SqFt: ", true, 0, true);
        newMaterialCostSqFt = io.getUserInputDouble("Enter material cost/SqFt: ", true, 0, true);
        productDao.createProduct(newProductName, newMaterialCostSqFt, newLaborCostSqFt);
    }

    private void renameProduct() {
        String newProductName;
        String oldProductName = io.getMultiChoiceListString("Select a product", productDao.getProducts(), true, "Return", 0);
        if (oldProductName.equals("Return"))return;
            do {
                newProductName = io.getUserInputString("Enter a new product name: ")/*.substring(0, 1).toUpperCase().substring(1).toLowerCase()*/;
            } while ((Arrays.asList(productDao.getProducts()).contains(newProductName)) || newProductName.equals(""));
            productDao.renameProduct(oldProductName, newProductName);
            io.println(oldProductName + " successfully renamed to " + newProductName);
        
    }

    private void changeMaterialCost() {
        double newMaterialCost;
        String productName = io.getMultiChoiceListString("Select a product", productDao.getProducts(), true, "Return", 0);
        if (productName.equals("Return") || productName.equals("")) {
            return;
        }
        newMaterialCost = io.getUserInputDouble("Enter a material cost: ", true, 0, true);
        productDao.changeMaterialCost(productName, newMaterialCost);
        io.println(productName + "'s material cost was successfully changed to " + newMaterialCost);
    }

    private void changeLaborCost() {
        double newLaborCost;
        String productName = io.getMultiChoiceListString("Select a product", productDao.getProducts(), true, "Return", 0);
        if (productName.equals("Return") || productName.equals("")) {
            return;
        }
        newLaborCost = io.getUserInputDouble("Enter a labor cost: ", true, 0, true);
        productDao.changeLaborCost(productName, newLaborCost);
        io.println(productName + "'s labor cost was successfully changed to " + newLaborCost);
    }

    private void removeProduct() {
        String productName = io.getMultiChoiceListString("Select a product", productDao.getProducts(), true, "Return", 0);
        if (productName.equals("Return") || productName.equals("")) {
            return;
        }
        if (Arrays.asList(productDao.getProducts()).contains(productName)) {
            productDao.removeProduct(productName);
            io.println(productName + " successfully removed");
        }
    }
}
