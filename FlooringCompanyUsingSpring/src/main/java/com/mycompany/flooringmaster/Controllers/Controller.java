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
import com.mycompany.flooringmaster.DTOs.Order;
import com.mycompany.flooringmaster.UI.ConsoleIO;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author apprentice
 */
public class Controller {
    private final ConsoleIO io;                 /* = new ConsoleIO();*/
    
    private final ConfigDao configDao;          /* = new ConfigDao();*/
                                                //private final boolean testMode = configDao.getTestMode();
    private final ProductDao productDao;        /* = new ProductDao(testMode);*/
    private final TaxesDao taxesDao;            /* = new TaxesDao(testMode);*/
    private final OrderDao orderDao;            /* = new OrderDao(testMode);*/
    private final AdminController adminMenu;    /* adminMenu = new AdminController(configDao, taxesDao, productDao, orderDao, io);*/

    public Controller(ConfigDao configBean, ProductDao productBean, TaxesDao taxesBean, OrderDao orderBean, AdminController adminBean, ConsoleIO ioBean){
        this.configDao = configBean;
        this.productDao = productBean;
        this.taxesDao = taxesBean;
        this.orderDao = orderBean;
        this.adminMenu = adminBean;
        this.io = ioBean;
    }
    
    public void run() {
        while (true) {
            int selection = io.getMultiChoiceInt("\nMenu:", new String[]{"Display Orders", "Add an Order", "Edit an Order", "Remove an Order", "Admin Menu", "Exit"});
            switch (selection) {
                case 1:
                    displayOrder();
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
                    adminMenu.print();
                    break;
                case 6:
                    io.println("Goodbye");
                    return; 
           }
        }
    }

    private String displayOrder() {
        String strDate = getFileName();
        DecimalFormat df = new DecimalFormat("#.00");
        if (orderDao.getFileOrderList(strDate).isEmpty()) {
            io.println("There are no orders for this day");
            strDate = "invalid";
        } else {
            for (Order x : orderDao.getFileOrderList(strDate)) {
                io.println("Order " + x.getOrderNum() + "\t\tName " + x.getName() + " :: Taxes $" + df.format(x.getTaxCost()) + " :: Total Cost $" + df.format(x.getTotalCost()));
                io.println("\t\tArea " + x.getArea() + " :: Product Type " + x.getProductType() + " :: Cost SqFt $" + df.format(x.getCostSqFt()) + " :: Labor Cost SqFt $" + df.format(x.getLaborCostSqFt()));
                io.println("\t\tState " + x.getState() + " :: Tax Rate " + x.getTaxRate() + "% :: Material Cost $" + df.format(x.getMaterialCost()) + " :: Labor Cost $" + df.format(x.getLaborCost()) + "\n");
            }
        }
        return strDate;
    }

    private void addOrder() {
        String name, state, productType;
        double area;
        do {
            name = io.getUserInputString("Enter your name: ");
        } while (name.equals(""));
        do {
            state = io.getMultiChoiceListString("Select your state: ", taxesDao.getStates(), false, "", 5).toUpperCase();
        } while (state.equals(""));
        do {
            productType = io.getMultiChoiceString("Select a product: ", productDao.getProducts());
        } while (productType.equals(""));
        String strArea;
        do {
            area = -1;
            strArea = io.getUserInputString("Enter your area (sq/ft): ");
            strArea = strArea.replaceAll("[^\\d.\\-]", "");

            try {
                area = Double.parseDouble(strArea);
            } catch (Exception ex) {
            }
        } while (area <= 0);

        String strDate = getFileName();

        double taxRate = taxesDao.getTax(state);
        double costSqFt = productDao.getProduct(productType).getCostSqFt();
        double laborCostSqFt = productDao.getProduct(productType).getLaborCostSqFt();
        double materialCost = costSqFt * area;
        double laborCost = laborCostSqFt * area;
        double taxCost = (taxRate / 100.0) * (laborCost + materialCost);
        double totalCost = laborCost + materialCost + taxCost;

        Integer orderNum = orderDao.getNextId(strDate);

        orderDao.createOrder(new Order(orderNum, name, state, taxRate, productType, area, costSqFt, laborCostSqFt, materialCost, laborCost, taxCost, totalCost, strDate));
        io.println("Order placed");
    }

    private void editOrder() {
        String strDate = displayOrder();

        if (!strDate.equals("invalid")) {
            Integer orderNum = Integer.parseInt(io.getUserInputString("Order number: ", orderDao.getStringOrders(strDate)));
            Order editOrder = orderDao.getOrder(orderNum, strDate);
            String name, state, product, area;
            int selection = 0;
            boolean editable = true;
            while (editable) {
                orderDao.updateOrder(editOrder,productDao.getProduct(editOrder.getProductType()).getLaborCostSqFt(),productDao.getProduct(editOrder.getProductType()).getCostSqFt(),taxesDao.getTax(editOrder.getState()));
                if (editOrder.getTaxRate() == -1.0) {
                    io.println("Sorry, your state is no longer valid");
                    do {
                        state = io.getMultiChoiceListString("Select a new state: ", taxesDao.getStates(), false, "", 5).toUpperCase();
                    } while (state.equals(""));
                    editOrder.setState(state);
                    orderDao.updateOrder(editOrder,productDao.getProduct(editOrder.getProductType()).getLaborCostSqFt(),productDao.getProduct(editOrder.getProductType()).getCostSqFt(),taxesDao.getTax(editOrder.getState()));
                }
                if (editOrder.getCostSqFt() == -1) {
                    io.println("Sorry, your product is no longer valid");
                    do {
                        product = io.getMultiChoiceString("Select a product: ", productDao.getProducts());
                    } while (product.equals(""));
                    editOrder.setProductType(product);
                    orderDao.updateOrder(editOrder,productDao.getProduct(editOrder.getProductType()).getLaborCostSqFt(),productDao.getProduct(editOrder.getProductType()).getCostSqFt(),taxesDao.getTax(editOrder.getState()));
                }

                io.println("Which field would you like to edit:");
                io.println("\t1. Name (" + editOrder.getName() + ")");
                io.println("\t2. State (" + editOrder.getState() + ")");
                io.println("\t3. Product type (" + editOrder.getProductType() + ")");
                io.println("\t4. Area (" + editOrder.getArea() + ")");
                io.println("\t5. Date (" + orderDao.getDate(editOrder.getFileName()) + ")");
                io.println("\t6. Return");

                selection = io.getUserInputInt("Field to edit: ", 1, 6, true);
                switch (selection) {
                    case 1:
                        name = io.getUserInputString("Enter your name: ");
                        if (!name.equals("")) {
                            editOrder.setName(name);
                        }
                        break;
                    case 2:
                        state = io.getMultiChoiceListString("Select your state: ", taxesDao.getStates(), false, "", 5).toUpperCase();
                        if (!state.equals("")) {
                            editOrder.setState(state);
                        }
                        break;
                    case 3:
                        product = io.getMultiChoiceListString("Select a product: ", productDao.getProducts(), false, "", 1);
                        if (!product.equals("")) {
                            editOrder.setProductType(product);
                        }
                        break;
                    case 4:
                        double newArea;
                        do {
                            newArea = -1;
                            area = io.getUserInputString("Enter your area (sq/ft): ");
                            area = area.replaceAll("[^\\d.\\-]", "");

                            try {
                                newArea = Double.parseDouble(area);
                            } catch (Exception ex) {
                            }
                            if (newArea != -1) {
                                editOrder.setArea(newArea);
                            }
                        } while (newArea <= 0);
                        break;
                    case 5:
                        String newDate = getFileName(true, editOrder.getFileName());
                        if (!newDate.equals(strDate)) {
                            editOrder.setOrderNum(orderDao.getNextId(newDate));
                        }
                        editOrder.setFileName(newDate);
                        orderDao.updateOrder(editOrder,productDao.getProduct(editOrder.getProductType()).getLaborCostSqFt(),productDao.getProduct(editOrder.getProductType()).getCostSqFt(),taxesDao.getTax(editOrder.getState()));
                        break;
                    case 6:
                        editable = false;
                        break;
                }
            }
        }
    }

    private void removeOrder() {
        String strDate = displayOrder();
        if (orderDao.getStringOrders(strDate).length > 0) {
            Integer orderNum;
            try{
                io.println("(Or enter \"return\" to go back)\n");
                orderNum = Integer.parseInt(io.getUserInputString("Order number: ",(String[])ArrayUtils.addAll(orderDao.getStringOrders(strDate), new String[] {"Return", "return", "r", "R"})));
            } catch (Exception ex){
                return;
            }
            Order editOrder = orderDao.getOrder(orderNum, strDate);
            orderDao.deleteOrder(editOrder);
        }
    }

    private String getFileName() {
        return getFileName(false, "");
    }

    private String getFileName(boolean acceptAnything, String date) {
        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        Date currentDate = new Date();
        String strDate = "Orders_" + dateFormat.format(currentDate) + ".txt";
        DateFormat yearFormat = new SimpleDateFormat("YYYY");
        int currentYear = Integer.parseInt(yearFormat.format(currentDate));
        boolean notValid = true, answer = true;

        while (notValid) {
            try {
                String response = io.getUserInputString("Use today's date for the order? (y/n): ");
                if (acceptAnything && response.equals("")) {
                    return date;
                } else if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
                    answer = true;
                    notValid = false;
                } else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
                    answer = false;
                    notValid = false;
                } else {
                    notValid = true;
                }
            } catch (Exception ex) {
            }
        }

        if (!answer) {
            String fullDate;
            int year, month, day;
            YearMonth yearMonthObject;
            boolean valid = false;

            do {
                do {
                    fullDate = io.getUserInputString("Date (MMDDYYYY): ");
                    fullDate = fullDate.replaceAll("[^\\d]", "");
                } while (fullDate.length() != 8);
                month = Integer.parseInt(fullDate.substring(0, 2));
                day = Integer.parseInt(fullDate.substring(2, 4));
                year = Integer.parseInt(fullDate.substring(4, 8));
                yearMonthObject = YearMonth.of(year, month);
                if (day > yearMonthObject.lengthOfMonth() || day < 1) {
                    valid = false;
                } else if (month > 12 || month < 1) {
                    valid = false;
                } else if (year > currentYear || year < 2000) {
                    valid = false;
                } else {
                    valid = true;
                }
            } while (!valid);
            strDate = "Orders_" + String.format("%02d", month) + String.format("%02d", day) + year + ".txt";
        }
        return strDate;
    }
}