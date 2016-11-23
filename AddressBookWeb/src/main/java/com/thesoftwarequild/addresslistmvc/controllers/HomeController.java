/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.addresslistmvc.controllers;

import com.thesoftwarequild.addresslistmvc.models.Address;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.thesoftwarequild.addresslistmvc.dao.AddressDao;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {
    
    private AddressDao addressDao;
    
    
    @Inject
    public HomeController(AddressDao dao) {
        this.addressDao = dao;
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(Model model){
                
        List<Address> address = addressDao.list();
        
        
        model.addAttribute("addressList", address);
        
        
        return "index";
    }
    
    
}
