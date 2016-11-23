/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.addresslistmvc.controllers;

import com.thesoftwarequild.addresslistmvc.models.Address;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.thesoftwarequild.addresslistmvc.dao.AddressDao;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value="/address")
public class AddressController {
    
    
    private AddressDao addressDao;
    
    @Inject
    public AddressController(AddressDao dao) {
        this.addressDao = dao;
    }
    
    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseBody
    public Address add(@Valid @RequestBody Address address){
        
        Address addedAddress = addressDao.add(address);
        
        
        return addedAddress;
        
    }
    
    
    @RequestMapping(value="/{id}")
    @ResponseBody
    public Address show(@PathVariable("id") Integer addressId) {
        
        Address address = addressDao.get(addressId);
        
        
        return address;
        
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer addressId) {
       
        addressDao.remove(addressId);
        
        
    }
    
    
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Address edit(@Valid @RequestBody Address address){
        
        addressDao.update(address);
        
        
        return address;
        
    }   
    
}
