/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.dao;

import com.thesoftwareguild.dvdlibrary.models.Movie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author parallels
 */
public class DvdDaoImpl implements DvdDao{

    private static Integer nextId = 0;
    private Map<Integer, Movie> dvdMap = new HashMap();
    
    
    
    public DvdDaoImpl() {
        Movie dvd1 = new Movie();
        dvd1.setTitle("Big Fish");
        dvd1.setDirector("Clint Eastwood");
        dvd1.setStudio("Paramount");
        dvd1.setMpaaRating("PG-13");
        dvd1.setReleaseYear("2001");
        
        add(dvd1);
        
        Movie dvd2 = new Movie();
        dvd2.setTitle("Mission Impossible");
        dvd2.setDirector("Joe Smith");
        dvd2.setStudio("Paramount");
        dvd2.setMpaaRating("PG-13");
        dvd2.setReleaseYear("1996");
        
        add(dvd2);
        
        Movie dvd3 = new Movie();
        dvd3.setTitle("Blade");
        dvd3.setDirector("Lisa Hammerstein");
        dvd3.setStudio("Fox");
        dvd3.setMpaaRating("PG-13");
        dvd3.setReleaseYear("1996");
        
        add(dvd3);
        
        Movie dvd4 = new Movie();
        dvd4.setTitle("Training Day");
        dvd4.setDirector("Chris Jones");
        dvd4.setStudio("Anapurna Pictures");
        dvd4.setMpaaRating("R");
        dvd4.setReleaseYear("2011");
        
        add(dvd4);
    }
    
    @Override
    public Movie add(Movie dvd) {
        
        nextId++;
        
        dvd.setId(nextId);
        dvdMap.put(dvd.getId(), dvd);
        
        return dvd;
        
    }

    @Override
    public Movie get(Integer id) {
        return dvdMap.get(id);
    }

    @Override
    public void remove(Integer id) {
        dvdMap.remove(id);
    }

    @Override
    public void update(Movie dvd) {
        dvdMap.put(dvd.getId(), dvd);
    }

    @Override
    public List<Movie> list() {
        return new ArrayList(dvdMap.values());
    }
    
    @Override
    public List<Movie> searchAll(String search){
        return dvdMap.values()
        .stream()
        .filter(contact -> contact.getTitle().equalsIgnoreCase(search) ||
                contact.getDirector().equalsIgnoreCase(search)||
                contact.getMpaaRating().equalsIgnoreCase(search)||
                contact.getReleaseYear().equalsIgnoreCase(search)||
                contact.getStudio().equalsIgnoreCase(search)||
                contact.getTitle().equalsIgnoreCase(search))
           .collect(Collectors.toList());

    }
    
}
