package com.mycompany.flooringmasteryproject.controllers;

import com.mycompany.flooringmasteryproject.dao.ConfigDao;
import com.mycompany.flooringmasteryproject.dao.OrdersDao;
import com.mycompany.flooringmasteryproject.dao.ProductsDao;
import com.mycompany.flooringmasteryproject.dao.TaxesDao;
import com.mycompany.flooringmasteryproject.dto.ConfigDto;
import com.mycompany.flooringmasteryproject.dto.OrdersDto;
import com.mycompany.flooringmasteryproject.dto.ProductsDto;
import com.mycompany.flooringmasteryproject.dto.TaxesDto;
import com.mycompany.flooringmasteryproject.ui.ConsoleIo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class FlooringController {

    private final ConsoleIo consoleio = new ConsoleIo();
    private final OrdersDao ordersdao = new OrdersDao();
    private final ProductsDao productsdao = new ProductsDao();
    private final TaxesDao taxesdao = new TaxesDao();
    private final ConfigDao configdao = new ConfigDao();
    private final ProductsDto productsdto = new ProductsDto();
    private final TaxesDto taxesdto = new TaxesDto();
    private String FILENAME = "";
    private ConfigDto config = new ConfigDto();
    private boolean test = false;

    public void run() {

        test = configdao.getConfig();

        boolean runAgain = true;

        while (runAgain) {
            displayMenu();

            int menuSelection = consoleio.getUserInputInt("\nPlease choose a menu option: ", 1, 5);

            switch (menuSelection) {

                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    runAgain = false;
                    consoleio.print("Goodbye");

            }
        }
    }

    private void displayMenu() {
        consoleio.print("\n1. Display Orders\n"
                + "2. Add an Order\n"
                + "3. Edit an Order\n"
                + "4. Remove an Order\n"
                + "5. Exit");
    }

    private void displayOrders() {
        boolean noOrders = true;

        String date = getUserDateInput();

        ordersdao.setOrderDate(date);

        List<OrdersDto> orders = ordersdao.list();

        for (OrdersDto theOrders : orders) {
            consoleio.println("Order Number: " + theOrders.getOrderNumber() + "\nName: " + theOrders.getName() + "\nCustomer State: "
                    + theOrders.getCustomerState() + "\nTaxRate: " + theOrders.getTaxRate() + "\nProduct Type: "
                    + theOrders.getProductType() + "\nArea: " + theOrders.getArea() + "\nCost Per Square Foot: "
                    + theOrders.getMaterialsCostPerSqFoot() + "\nLabor Cost Per Square Foot: " + "\nMaterial Cost: "
                    + theOrders.getMaterialsCost() + "\nLabor Cost: " + theOrders.getLaborCost() + "\nTax: "
                    + theOrders.getTotalTax() + "\nTotal Cost: " + theOrders.getGrandTotal() + "\n");
            noOrders = false;
        }

        if (noOrders == true) {
            consoleio.println("Sorry, there are no orders for that date.");
        }

    }

    private void addOrder() {
        int counter = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        double materialCostPerSquareFoot = 0;
        double laborCostPerSquareFoot = 0;
        double materialCost = 0;
        double laborCost = 0;
        double total = 0;
        double tax = 0;
        double taxRate = 0;
        double totalFinal = 0;
        boolean fileFound = false;
        OrdersDto order = new OrdersDto();

        String date = getUserDateInput();

        ordersdao.setOrderDate(date);
        ordersdao.findFileMap();

        if (ordersdao.findFileMap() != null) {
            fileFound = true;
        }

        if (fileFound = false) {
            ordersdao.createOrderFile(FILENAME);
        }

        String customerName = consoleio.getUserInputString("What is the customer's name?");

        List<TaxesDto> stateTaxes = taxesdao.getTaxesList();

        for (TaxesDto entry : stateTaxes) {
            counter++;
        }
        String[] stateArray = new String[counter];

        for (TaxesDto entry : stateTaxes) {
            stateArray[counter2] = (entry.getState());
            counter2++;
        }

        String state = consoleio.getMultiChoiceString("Please choose a state from the list above", stateArray);

        for (TaxesDto taxR : stateTaxes) {
            if (state.equals(taxR.getState())) {
                taxRate = taxR.getTaxRate();
            }
        }

        List<ProductsDto> productType = productsdao.getProductsList();
        for (ProductsDto products : productType) {
            products.getProductType();
            counter3++;
        }

        String[] productsArray = new String[counter3];

        for (ProductsDto products : productType) {
            productsArray[counter4] = products.getProductType();
            counter4++;
        }

        String prodType = consoleio.getMultiChoiceString("Please choose a product type from the list above", productsArray);

        double area = consoleio.getUserInputDouble("What is the area of the product to be purchased (in Square Feet)?");

        for (ProductsDto products : productType) {
            if (products.getProductType().equals(prodType)) {
                materialCostPerSquareFoot = products.getMaterialCostPerSqFoot();
                laborCostPerSquareFoot = products.getLaborCostPerSqFoot();
            }
        }

        materialCost = calculateMaterial(area, materialCostPerSquareFoot);
        laborCost = calculateLabor(area, laborCostPerSquareFoot);
        total = calculateTotal(materialCost, laborCost);
        tax = calculateTax(total, taxRate);
        totalFinal = calculateTrueTotal(total, tax);

        order.setName(customerName);
        order.setCustomerState(state);
        order.setTaxRate(taxRate);
        order.setProductType(prodType);
        order.setArea(area);
        order.setMaterialsCostPerSqFoot(materialCostPerSquareFoot);
        order.setLaborCostPerSqFoot(laborCostPerSquareFoot);
        order.setMaterialsCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTotalTax(tax);
        order.setGrandTotal(totalFinal);

        ordersdao.create(order);

        List<OrdersDto> orders = ordersdao.list();

        for (OrdersDto theOrders : orders) {
            consoleio.println("Order Number: " + theOrders.getOrderNumber() + "\nName: " + theOrders.getName() + "\nCustomer State: "
                    + theOrders.getCustomerState() + "\nTaxRate: " + theOrders.getTaxRate() + "\nProduct Type: "
                    + theOrders.getProductType() + "\nArea: " + theOrders.getArea() + "\nCost Per Square Foot: "
                    + theOrders.getMaterialsCostPerSqFoot() + "\nLabor Cost Per Square Foot: " + "\nMaterial Cost: "
                    + theOrders.getMaterialsCost() + "\nLabor Cost: " + theOrders.getLaborCost() + "\nTax: "
                    + theOrders.getTotalTax() + "\nTotal Cost: " + theOrders.getGrandTotal() + "\n");
        }
    }

    public void editOrder() {
        int counter = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        int counter5 = 0;
        int counter6 = 0;
        String fileName = "";
        OrdersDto orders = null;
        double materialCostPerSquareFoot = 0;
        double laborCostPerSquareFoot = 0;
        double taxRate = 0;
        double materialCost = 0;
        double laborCost = 0;
        double total = 0;
        double tax = 0;
        double totalFinal = 0;
        boolean editPossible = true;
        Map<Integer, OrdersDto> map = null;

        String date = getUserDateInput();

        ordersdao.setOrderDate(date);

        ordersdao.findFileMap();

        if (ordersdao.findFileMap() != null) {
            consoleio.println("Good job, there are order(s) for " + date);
        } else {
            consoleio.println("Sorry there are no orders for that date!");
            editPossible = false;
        }

        while (editPossible) {

            Set<Map.Entry<Integer, OrdersDto>> set = ordersdao.findFileMap().entrySet();
            for (Map.Entry<Integer, OrdersDto> entry : set) {
                counter++;
            }
            String[] idArray = new String[counter];

            for (Map.Entry<Integer, OrdersDto> entry : set) {
                idArray[counter2] = (entry.getKey().toString());
                counter2++;
            }

            Integer orderNum = consoleio.getMultiChoiceInt("Please input the order number of the order you wish to view: ", idArray);

            for (Map.Entry<Integer, OrdersDto> entry : set) {
                if (orderNum == entry.getKey()) {
                    orders = entry.getValue();
                }
            }

            String name = consoleio.getUserInputString("Enter customer name (" + orders.getName() + "): ");

            if (!name.equals("")) {
                orders.setName(name);
            }

            List<TaxesDto> stateTaxes = taxesdao.getTaxesList();
            for (TaxesDto entry : stateTaxes) {
                counter3++;
            }
            String[] stateArray = new String[counter3];

            for (TaxesDto entry : stateTaxes) {
                stateArray[counter4] = (entry.getState());
                counter4++;
            }
            String state = consoleio.getMultiChoiceString("Enter customer state(" + orders.getCustomerState() + "): ", stateArray);

            if (!state.equals("")) {
                for (TaxesDto taxR : stateTaxes) {
                    if (state.equals(taxR.getState())) {
                        taxRate = taxR.getTaxRate();
                    }
                }
                orders.setCustomerState(state);
                orders.setTaxRate(taxRate);
            }

            List<ProductsDto> productType = productsdao.getProductsList();
            for (ProductsDto products : productType) {
                products.getProductType();
                counter5++;
            }

            String[] productsArray = new String[counter5];

            for (ProductsDto products : productType) {
                productsArray[counter6] = products.getProductType();
                counter6++;
            }

            String prodType = consoleio.getMultiChoiceString("Enter the product type(" + orders.getProductType() + "): ", productsArray);
            double area = consoleio.getUserInputDouble("Enter area of the product(" + orders.getArea() + "): ");
            orders.setArea(area);

            if (!prodType.equals("")) {
                for (ProductsDto products : productType) {
                    if (products.getProductType().equals(prodType)) {
                        materialCostPerSquareFoot = products.getMaterialCostPerSqFoot();
                        laborCostPerSquareFoot = products.getLaborCostPerSqFoot();
                    }
                }
                orders.setProductType(prodType);
                orders.setMaterialsCostPerSqFoot(materialCostPerSquareFoot);
                orders.setLaborCostPerSqFoot(laborCostPerSquareFoot);
            }
            if (!prodType.equals("")) {
                materialCost = calculateMaterial(area, materialCostPerSquareFoot);
                laborCost = calculateLabor(area, laborCostPerSquareFoot);
                total = calculateTotal(materialCost, laborCost);
                orders.setMaterialsCost(materialCost);
                orders.setLaborCost(laborCost);
            } else {
                materialCost = orders.getMaterialsCost();
                laborCost = orders.getLaborCost();
                total = calculateTotal(materialCost, laborCost);
            }

            if (!state.equals("")) {
                tax = calculateTax(total, taxRate);
                orders.setTotalTax(total);
            } else {
                tax = orders.getTotalTax();
            }

            if (!prodType.equals("") || !state.equals("")) {
                totalFinal = calculateTrueTotal(total, tax);
                orders.setGrandTotal(total);
            } else {
                totalFinal = orders.getGrandTotal();
            }

            ordersdao.update(orders);
            editPossible = false;

            List<OrdersDto> ordersList = ordersdao.list();

            for (OrdersDto theOrders : ordersList) {
                consoleio.println("Order Number: " + theOrders.getOrderNumber() + "\nName: " + theOrders.getName() + "\nCustomer State: "
                        + theOrders.getCustomerState() + "\nTaxRate: " + theOrders.getTaxRate() + "\nProduct Type: "
                        + theOrders.getProductType() + "\nArea: " + theOrders.getArea() + "\nCost Per Square Foot: "
                        + theOrders.getMaterialsCostPerSqFoot() + "\nLabor Cost Per Square Foot: " + "\nMaterial Cost: "
                        + theOrders.getMaterialsCost() + "\nLabor Cost: " + theOrders.getLaborCost() + "\nTax: "
                        + theOrders.getTotalTax() + "\nTotal Cost: " + theOrders.getGrandTotal() + "\n");
            }
        }
    }

    public void removeOrder() {

        String fileName = "";
        boolean removePossible = true;
        int counter = 0;
        int counter2 = 0;
        Map<Integer, OrdersDto> map = null;
        OrdersDto orders = null;

        String date = getUserDateInput();

        ordersdao.setOrderDate(date);

        ordersdao.findFileMap();

        if (ordersdao.findFileMap() != null) {
            consoleio.println("Good job, there are order(s) for " + date);
        } else {
            consoleio.println("Sorry there are no orders for that date!");
            removePossible = false;
        }

        while (removePossible) {

            Set<Map.Entry<Integer, OrdersDto>> set = ordersdao.findFileMap().entrySet();
            for (Map.Entry<Integer, OrdersDto> entry : set) {
                counter++;
            }
            String[] idArray = new String[counter];

            for (Map.Entry<Integer, OrdersDto> entry : set) {
                idArray[counter2] = (entry.getKey().toString());
                counter2++;
            }

            Integer orderNum = consoleio.getMultiChoiceInt("Please input the order number of the order you wish to view: ", idArray);

            for (Map.Entry<Integer, OrdersDto> entry : set) {
                if (orderNum == entry.getKey()) {
                    orders = entry.getValue();
                }
            }
            ordersdao.delete(orders);
            removePossible = false;
        }
    }

    public double calculateTax(double total, double taxRate) {
        double tax = total * taxRate;
        return tax;
    }

    public double calculateMaterial(double costPerSquareFoot, double area) {
        double material = costPerSquareFoot * area;
        return material;
    }

    public double calculateLabor(double laborCostPerSquareFoot, double area) {
        double labor = laborCostPerSquareFoot * area;
        return labor;
    }

    public double calculateTotal(double a, double b) {
        double c = 0;
        c = a + b;
        return c;
    }

    public double calculateTrueTotal(double total, double tax) {
        double a = 0;
        a = total + tax;
        return a;
    }

    private String getUserDateInput() {
        Scanner sc = new Scanner(System.in);
        boolean isValidDate = false;
        //SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
        String userInput = "";
        while (isValidDate == false) {

            System.out.println("Type in the date like this: MMddyyyy Enter Date Here: ");
            userInput = sc.nextLine();

            try {
                DateTimeFormatter formatter
                        = DateTimeFormatter.ofPattern("MMddyyyy");
                LocalDate date = LocalDate.parse(userInput, formatter);
                System.out.printf("%s%n", date);
            } catch (DateTimeParseException exc) {
                System.out.printf("%s is not parsable!%n", userInput);
                isValidDate= false;
            }
// 'date' has been successfully parsed
            //String userInputDay = (userInput.substring(0, 2));
            //String userInputMonth = (userInput.substring(2,4));
            //String userInputYear = (userInput.substring(4,8));
            //String userInputFileName = ("Order_"+userInputDay+userInputMonth+userInputYear+ ".txt");
        }
        return userInput;
    }

}
