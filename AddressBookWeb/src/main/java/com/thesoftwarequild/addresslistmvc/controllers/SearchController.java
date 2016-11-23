/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.addresslistmvc.controllers;

import com.thesoftwarequild.addresslistmvc.dao.AddressDao;
import com.thesoftwarequild.addresslistmvc.models.Address;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value="/address")
public class SearchController {
    
    private AddressDao addressDao;
    
    @Inject
    public SearchController(AddressDao dao){
        this.addressDao = dao;
    }
    
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String search(){
        
        return "search";
    }
    
//    @RequestMapping(value="/viewsearch", method = RequestMethod.GET)
//    public String viewSearch(@RequestParam("search")String search,@RequestParam("name") String name, Model model){
//        
//        List address = null;
//        
//        switch(name){
//            case "firstName": address = addressDao.searchByFirstName(search);
//                              break;
//            case "lastName": address = addressDao.searchByLastName(search);
//                             break;
//            case "address": address = addressDao.searchByLastName(search);
//                            break;
//            case "city": address = addressDao.searchByCity(search);
//                         break;
//            case "stat": address = addressDao.searchByState(search);
//                         break;
//            case "zip": address = addressDao.searchByZip(search);
//                        break;      
//        }
//                
////        List address = addressDao.searchByLastName(search);
//        
//        
//        model.addAttribute("addressList", address);        
//        
//        return "viewSearch";
//    } 
    
    @RequestMapping(value="/viewsearchall", method = RequestMethod.GET)
    public String viewSearch(@RequestParam("search1")String search, Model model){
        
        List address = addressDao.searchAll(search);
        
        
        model.addAttribute("addressList", address);        
        
        return "viewSearch";
    }       
    
}
