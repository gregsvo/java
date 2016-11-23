/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {

    void changeDate(Order order, String date, String fileName);

    void createOrder(String fileName, String date, String customerName, String state, String productType, double area);

    void editOrder(Order order, String customerName, String state, String productType, Double area, String date, String fileName);

    Order getOrder(String date, Integer orderNumber);

    void removeOrder(String date, Integer orderNumber, String fileName);

    List<Order> retrieveOrderDate(String date, String fileName);

    boolean retrieveOrderID(Integer orderNumber, String date, String fileName);

    double roundTwoDecimals(double value);
    
    public List<Order> list();
    
    public List<Order> searchAll(String search);
}    
