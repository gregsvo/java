/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmasteryproject.dao;

import com.mycompany.flooringmasteryproject.dto.OrdersDto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class OrdersDao {

    private String FILENAME = "Orders_";
    private String orderDate;
    private final String DELIMETER = ",";
    private Map<Integer, OrdersDto> orderNumbers = new HashMap();
    Integer nextId = 0;
    File[] orderFiles = new File(System.getProperty("user.dir")).listFiles();
    boolean config = false;
    private ConfigDao configdao = new ConfigDao();
    private boolean test = false;

    public OrdersDao() {
        setConfig(configdao.getConfig());
    }

    public int getNextId() {
        nextId++;
        return nextId;
    }

    public OrdersDto create(OrdersDto order) {
        try {
            nextId = Collections.max(orderNumbers.keySet());
        } catch (Exception ex) {

        }
        order.setOrderNumber(getNextId());

        orderNumbers.put(order.getOrderNumber(), order);
        if (!config) {
        encode();
        }
        return order;
    }

    private String getFilename() {
        return FILENAME + orderDate + ".txt";

        //return FILENAME.replace("{date}", orderDate);
    }

    public String createOrderFile(String createFILENAME) {
        FILENAME = createFILENAME;
        if (!config) {
            encode();
        }
        return FILENAME;
    }

    public void update(OrdersDto order) {
        orderNumbers.put(order.getOrderNumber(), order);
        if (!config) {
            encode();
        }
    }

    public void delete(OrdersDto order) {
        orderNumbers.remove(order.getOrderNumber(), order);
        if (!config) {
            encode();
        }
    }

     public List<OrdersDto> list() {

        List<OrdersDto> ordersList = new ArrayList<>();
        ordersList.addAll(orderNumbers.values());
    
        return ordersList;
    }
 
    public void encode() {

        boolean config = true;
        try {
            PrintWriter out = new PrintWriter(new FileWriter(getFilename()));

            Map<Integer, OrdersDto> orders = findFileMap();
            Set<Map.Entry<Integer, OrdersDto>> set = orders.entrySet();
            for (Map.Entry<Integer, OrdersDto> o : set) {
                String line = o.getKey() + DELIMETER
                        + o.getValue().getName() + DELIMETER
                        + o.getValue().getCustomerState() + DELIMETER
                        + o.getValue().getTaxRate() + DELIMETER
                        + o.getValue().getProductType() + DELIMETER
                        + o.getValue().getArea() + DELIMETER
                        + o.getValue().getMaterialsCostPerSqFoot() + DELIMETER
                        + o.getValue().getLaborCostPerSqFoot() + DELIMETER
                        + o.getValue().getMaterialsCost() + DELIMETER
                        + o.getValue().getLaborCost() + DELIMETER
                        + o.getValue().getTotalTax() + DELIMETER
                        + o.getValue().getGrandTotal();
                out.println(line);
                out.flush();
            }
        } catch (IOException ex) {

        }

    }

    public Map<Integer, OrdersDto> decode() {

        Map<Integer, OrdersDto> readOrders = new HashMap();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(getFilename())));

            while (sc.hasNextLine()) {
                String thisLine = sc.nextLine();
                String[] values = thisLine.split(DELIMETER);

                OrdersDto orders = new OrdersDto();

                orders.setOrderNumber(Integer.parseInt(values[0]));
                orders.setName(values[1]);
                orders.setCustomerState(values[2]);
                orders.setTaxRate(Double.parseDouble(values[3]));
                orders.setProductType(values[4]);
                orders.setArea(Double.parseDouble(values[5]));
                orders.setMaterialsCostPerSqFoot(Double.parseDouble(values[6]));
                orders.setLaborCostPerSqFoot(Double.parseDouble(values[7]));
                orders.setMaterialsCost(Double.parseDouble(values[8]));
                orders.setLaborCost(Double.parseDouble(values[9]));
                orders.setTotalTax(Double.parseDouble(values[10]));
                orders.setGrandTotal(Double.parseDouble(values[11]));

                readOrders.put(orders.getOrderNumber(), orders);
                orderNumbers = readOrders;
            }
        } catch (FileNotFoundException fnf) {

        }

        return orderNumbers;

    }

    public void setOrderDate(String date) {
        this.orderDate = date;
    }
    
    public void setConfig (boolean test){
        this.config = test;
    }

    public Map findFileMap() {
        boolean noOrders = true;
        Map<Integer, OrdersDto> map = null;

        for (File files : orderFiles) {
            if (files.getName().contains(getFilename())) {
                map = decode();
            }
        }
        
        return this.orderNumbers;

    }

    public OrdersDto getOrderById(Integer orderNumber) {
        return this.orderNumbers.get(orderNumber);
    }
 
}
