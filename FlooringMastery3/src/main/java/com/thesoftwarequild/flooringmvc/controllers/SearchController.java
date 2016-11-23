/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.controllers;

import com.thesoftwarequild.flooringmvc.dao.OrderDao;
import com.thesoftwarequild.flooringmvc.models.Order;
import java.util.List;
import javax.inject.Inject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@org.springframework.stereotype.Controller
@RequestMapping(value="flooring")
public class SearchController {
    
    private OrderDao orderDao;
    
    @Inject
    public SearchController(OrderDao dao){
        this.orderDao = dao;
    }
    
   @RequestMapping(value="/search")
    public String search(){
        
        return "search";
    }
    
    @RequestMapping(value="/search", method = RequestMethod.POST) 
    @ResponseBody
    public List<Order> view(@RequestParam("search1") String search)    {
        
       List<Order> order = orderDao.searchAll(search);
                
        
        return order;
    
}
    
//    @RequestMapping(value="/viewsearchall", method = RequestMethod.GET)
//    public String viewSearch(@RequestParam("search1")String search, Model model){
//        
//        List order = orderDao.searchAll(search);
//        
//        
//        model.addAttribute("orderList", order);        
//        
//        return "viewSearchAll";
//    }       
    
}
