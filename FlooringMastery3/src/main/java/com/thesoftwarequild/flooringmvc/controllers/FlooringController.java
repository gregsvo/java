/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.controllers;

import com.thesoftwarequild.flooringmvc.commands.AddOrderCommand;
import com.thesoftwarequild.flooringmvc.dao.OrderDao;
import com.thesoftwarequild.flooringmvc.models.Order;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value="/flooring")
public class FlooringController {
    
    private OrderDao orderDao;
    
    @Inject
    public FlooringController(OrderDao dao){
        this.orderDao = dao;
    }
    
    public String generateFileName(String date) {
        String noSlash = date.replaceAll("/", "");

        return "Order_" + noSlash + ".csv";
    }
    
    public String generateNoSlashDate(String date) {
        String noSlash = date.replaceAll("/", "");

        return noSlash;
    }    
    //CREATE ORDER
    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public Order createOrder(@RequestBody AddOrderCommand order){  
        
        
        String date1 = generateNoSlashDate(order.getDate());
        
        String fileName = generateFileName(order.getDate());
        
        Order orderReturned = orderDao.createOrder(fileName, date1, order.getCustomerName(), order.getState(), order.getProductType(), order.getArea());
        
        
        return orderReturned;
    }
    
    @RequestMapping(value="/{id}/{date}",  method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer orderId, @PathVariable("date") String date ) {
       
        orderDao.getOrder(date, orderId);
        
        String fileName = generateFileName(orderDao.getOrder(date, orderId).getDate());
        
        orderDao.removeOrder(date, orderId, fileName);
        
        
    } 
    
    @RequestMapping(value="/{id}/{date}", method = RequestMethod.GET)
    @ResponseBody
    public Order show(@PathVariable("id") Integer orderId, @PathVariable("date") String date) {
        
        Order order = orderDao.getOrder(date, orderId);
        
        
        return order;
        
    }    
    
    @RequestMapping(value="/showEdit/{id}/{date}")
    public String showEdit(@PathVariable("id") Integer orderId, @PathVariable("date") String date, Model model){
        
        Order order = orderDao.getOrder(date, orderId);
        
        model.addAttribute("order", order);
        
        
        return "showEdit";
        
    }
    
    @RequestMapping(value="/{id}/{date}", method = RequestMethod.PUT)
    @ResponseBody
    public Order edit(@RequestBody AddOrderCommand order, @PathVariable("id") Integer orderId, @PathVariable("date") String date) { 
//            @ModelAttribute Order order, BindingResult bindingResult, @PathVariable("id") Integer orderId, @PathVariable("date") String date, Model model){
        
        String fileName = generateFileName(date);
        
        List<Order> order1 = orderDao.retrieveOrderDate(date, fileName);
        
        Order order2 = orderDao.editOrder(orderDao.getOrder(date, orderId), order.getCustomerName(), order.getState(), order.getProductType(), order.getArea(), date, fileName);

        return order2;
        
    }    
    
    
}
