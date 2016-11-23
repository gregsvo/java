/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.controllers;

import com.thesoftwareguild.dvdlibrary.dao.DvdDao;
import com.thesoftwareguild.dvdlibrary.models.Movie;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author parallels
 */
@Controller
public class HomeController {
    
    private DvdDao dvdDao;
    
    
    @Inject
    public HomeController(DvdDao dao) {
        this.dvdDao = dao;
    }
    
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String home(Model model) {
        
        List<Movie> dvdList = dvdDao.list();
        model.addAttribute("dvdList", dvdList);
        return "index";
    }
    
    
    
    
}
