/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.controllers;


import com.thesoftwareguild.dvdlibrary.dao.DvdDao;
import com.thesoftwareguild.dvdlibrary.models.Movie;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author parallels
 */
@Controller
@RequestMapping(value="/dvd")
public class DvdController {
    
    
    private DvdDao dvdDao;
    
    @Inject
    public DvdController(DvdDao dao) {
        this.dvdDao = dao;
    }
    
    //ADD
    @RequestMapping(value="", method = RequestMethod.POST) //REFERENCED IN APP.JS CREATE (AJAX) WITH "POST".
    @ResponseBody
    public Movie add(@Valid @RequestBody Movie dvd) {
               
        Movie addedDvd = dvdDao.add(dvd);
        return addedDvd;
    }
    
    //SHOW
    @RequestMapping(value="/{id}") 
    @ResponseBody
    public Movie show(@PathVariable("id") Integer dvdId) {
        
        Movie dvd = dvdDao.get(dvdId);
        
        return dvd;
        
    }
    
    
    //DELETE
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer dvdId) {
        
        dvdDao.remove(dvdId);
        
        //return "redirect:/";
        
    }
    
    //SHOW EDITED DVD
//    @RequestMapping(value="/showEdit/{id}")
//    public String showEdit(@PathVariable("id") Integer dvdId, Model model) {
//        
//       Dvd dvd = dvdDao.get(dvdId);
//       
//       model.addAttribute("dvd", dvd);
//        
//       return "showEdit";
//        
//    }
    
    //EDIT DVD
    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Movie edit(@Valid @RequestBody Movie dvd) {
        
   
        
        dvdDao.update(dvd);
        
        
        return dvd;
    }
    
    
    
}
