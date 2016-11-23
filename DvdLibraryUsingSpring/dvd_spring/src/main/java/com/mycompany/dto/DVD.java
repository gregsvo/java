/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVD {
    private String title;
    private Integer releaseDate;
    private String mpaaRating;
    private String studio;
    private Integer id;
    private List<String> comments = new ArrayList<String>();
    
    public DVD(){
        //for the JUnitTest
    }

   public DVD(String title, Integer releaseDate, String mpaaRating, String studio, List<String> comments){
       this.title = title;
       this.releaseDate = releaseDate;
       this.mpaaRating= mpaaRating;
       this.studio = studio;
       this.comments= comments;
       
   }
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getRealseDate() {
        return releaseDate;
    }

    
    public void setReleaseDate(Integer realseDate) {
        this.releaseDate = realseDate;
    }

   
    public String getMpaaRating() {
        return mpaaRating;
    }

  
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    
    public String getStudio() {
        return studio;
    }

 
    public void setStudio(String studio) {
        this.studio = studio;
    }

  
    public Integer getId() {
        return id;
    }

  
    public void setId(Integer id) {
        this.id = id;
    }

  
    public List<String> getComments() {
        return comments;
    }

  
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
    
    
    
}
