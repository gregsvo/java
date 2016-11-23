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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute AddOrderCommand order, BindingResult bindingResult, Model model){  
        
        String date1 = generateNoSlashDate(order.getDate());
        
        String fileName = generateFileName(order.getDate());
        
        orderDao.createOrder(fileName, date1, order.getCustomerName(), order.getState(), order.getProductType(), order.getArea());
        
        
        return "redirect:/";
    }
    
    @RequestMapping(value="/delete/{id}/{date}")
    public String delete(@PathVariable("id") Integer orderId, @PathVariable("date") String date ) {
       
        orderDao.getOrder(date, orderId);
        
        String fileName = generateFileName(orderDao.getOrder(date, orderId).getDate());
        
        orderDao.removeOrder(date, orderId, fileName);
        
        return "redirect:/";
        
    } 
    
    @RequestMapping(value="/view/{id}/{date}")
    public String show(@PathVariable("id") Integer orderId, @PathVariable("date") String date, Model model) {
        
        Order order = orderDao.getOrder(date, orderId);
        
        
        model.addAttribute("order", order);
        
        
        return "viewOrder";
        
    }    
    
    @RequestMapping(value="/showEdit/{id}/{date}")
    public String showEdit(@PathVariable("id") Integer orderId, @PathVariable("date") String date, Model model){
        
        Order order = orderDao.getOrder(date, orderId);
        
        model.addAttribute("order", order);
        
        
        return "showEdit";
        
    }
    
    @RequestMapping(value="/edit/{id}/{date}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Order order, BindingResult bindingResult, @PathVariable("id") Integer orderId, @PathVariable("date") String date, Model model){
        
        String fileName = generateFileName(order.getDate());
        
        List<Order> order1 = orderDao.retrieveOrderDate(date, fileName);
        
        orderDao.editOrder(orderDao.getOrder(date, orderId), order.getCustomerName(), order.getState(), order.getProductType(), order.getArea(), order.getDate(), fileName);
        
        
        return "redirect:/";
        
    }    
    
//    @RequestMapping(value="/generate/{date}")
//    public String generate(@PathVariable("date") String date){
//        
//        String date1 = generateNoSlashDate(date);
//        
//        return "redirect:/";
//        
//    }
    
}
