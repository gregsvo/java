/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibrary.models;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author parallels
 */
public class Movie {
    
    private Integer id;
    
    @NotEmpty(message = "You must supply a value for title.")
    private String title;
    
    @NotEmpty(message="You must supply a value for director.")
    private String director;
    
    @NotEmpty(message="You must supply a value for studio.") 
    private String studio;
    
    @NotEmpty(message="You must supply a value for rating.") 
    private String mpaaRating;
    
    @NotEmpty(message="You must supply a 4-digit year the movie was released.") 
    private String releaseYear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }


}
