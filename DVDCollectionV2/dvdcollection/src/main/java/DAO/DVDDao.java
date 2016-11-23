/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DVD;
import java.util.List;

/**
 *
 * @author Ollie
 */
public interface DVDDao {

    public DVD addDVD(DVD dvd);

    void editDVD(Integer id, DVD dvd);

    public DVD getDVDById(int id);

    public List<DVD> getDVDs();
    
    public List<DVD> findDvdsNewerThan(Integer age);
    
    public List<DVD> findDvdsByYear(Integer year);
    
    public List<DVD> findAllDvdsWithMPAA(String rating);

    public List<DVD> getDVDsByTitle(String title);

    void removeDVD(Integer id);
    
    public List<DVD> findAllDvdsByDirector(String director);
    
    public double findAverageAge();
    
    public double findAverageNotes();
    
    public List<DVD> findAllDvdsByStudio(String studio);
    
}
