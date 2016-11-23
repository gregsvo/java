package com.thesoftwareguild.dvdlibrary.controllers;

import com.thesoftwareguild.dvdlibrary.dao.DvdDao;
import com.thesoftwareguild.dvdlibrary.models.Movie;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 *
 * @author Ollie
 */
@Controller
@RequestMapping(value="/dvd")
public class SearchController {
    
    private DvdDao dvdDao;
    
    @Inject
    public SearchController(DvdDao dvdDao)  {
        this.dvdDao = dvdDao;
    }

   
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String search() {
        
        return "search";
    }
    
    @RequestMapping(value="/viewsearch", method = RequestMethod.GET)
    public String viewSearch(@RequestParam("search") String search, Model model)  {
        
        List<Movie> dvdList = dvdDao.searchAll(search);
        model.addAttribute("dvdList", dvdList);
        model.addAttribute("search", search);
        
        return "viewsearch";
        
        
        
    }

}
