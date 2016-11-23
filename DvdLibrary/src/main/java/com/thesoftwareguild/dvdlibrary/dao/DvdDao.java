package com.thesoftwareguild.dvdlibrary.dao;

import com.thesoftwareguild.dvdlibrary.models.Movie;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DvdDao {

    Movie add(Movie dvd);
    Movie get(Integer id);
    List<Movie> list();
    void remove(Integer id);
    void update(Movie dvd);
   public List<Movie> searchAll(String search);
    
}
