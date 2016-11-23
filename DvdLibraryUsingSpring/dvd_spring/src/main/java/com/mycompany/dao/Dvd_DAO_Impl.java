/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public final class Dvd_DAO_Impl implements DvdLibraryDao {

    private final Map<Integer, DVD> dvdInventory = new HashMap<>();
    private Integer idGenerator = 1;
    private final String FILENAME = "DVDS.txt";
    private final String DELIMETER = "::";
    private boolean testMode = false;

    //when created decode the file
    public Dvd_DAO_Impl(boolean testMode) {
        this.testMode = testMode;
        decodeLibrary();
    }

    // create DVD
    @Override
    public void add(DVD dvd) {
        //if the dvd isn't null
        if (dvd != null) {
            //use the idGen to set passed through dvd's id
            dvd.setId(idGenerator);
            //increase generator to ensure uniqueness
            idGenerator++;
            //put dvd in map, id first, actual dvd second
            this.dvdInventory.put(dvd.getId(), dvd);
            //save dvd Library
            encodeLibrary();
        }

    }

    //get Dvd by id number
    @Override
    public DVD getById(int id) {
        //use id passed in and grab from map the id key's value
        DVD info = this.dvdInventory.get(id);
        // return the id key's value
        return info;
    }

    //get Dvd by title name
    @Override
    public List<DVD> getByTitle(String title) {
        List<DVD> dvds = new ArrayList<>();
        // create Iterator interface on dvdInventory's values
        Iterator<DVD> dvdIter = dvdInventory.values().iterator();
        // while the iterator has another DVD
        while (dvdIter.hasNext()) {
            //create a DVD ref variable that holds current dvd being iterated
            DVD next = dvdIter.next();
            //if the DVD has the same title as what was passed
            if (next.getTitle().contains(title)) {
                //return the DVD
                dvds.add(next);
            }
        }
        return dvds;
    }

    @Override
    public List<DVD> getByRating(String rating) {
        List<DVD> dvds = new ArrayList<>();
        // create Iterator interface on dvdInventory's values
        Iterator<DVD> dvdIter = dvdInventory.values().iterator();
        // while the iterator has another DVD
        while (dvdIter.hasNext()) {
            //create a DVD ref variable that holds current dvd being iterated
            DVD next = dvdIter.next();
            //if the DVD has the same title as what was passed
            if (next.getMpaaRating().contains(rating)) {
                //return the DVD
                dvds.add(next);
            }
        }
        return dvds;
    }

    @Override
    public List<DVD> getByStudio(String studio) {
        List<DVD> dvds = new ArrayList<>();
        // create Iterator interface on dvdInventory's values
        Iterator<DVD> dvdIter = dvdInventory.values().iterator();
        // while the iterator has another DVD
        while (dvdIter.hasNext()) {
            //create a DVD ref variable that holds current dvd being iterated
            DVD next = dvdIter.next();
            //if the DVD has the same title as what was passed
            if (next.getStudio().contains(studio)) {
                //return the DVD
                dvds.add(next);
            }
        }
        return dvds;
    }

    // delete dvd
    @Override
    public void remove(int idToRemove) {
        //remove the key equal to the int passed through
        dvdInventory.remove(idToRemove);
        //save the library
        encodeLibrary();

    }

    public void encodeLibrary() {
        if (!testMode) {
            try {
                //create a printwriter object that can write to the filename variable
                PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));
                //for every dvd in the inventory

                dvdInventory.values()
                        .stream()
                        .forEach(d -> {
                            pw.println(
                                    (d.getId() + DELIMETER
                                    + d.getTitle() + DELIMETER
                                    + d.getRealseDate() + DELIMETER
                                    + d.getMpaaRating() + DELIMETER
                                    + d.getStudio() + DELIMETER + d.getComments()));
                            pw.flush();
                        });

            } catch (IOException ex) {
            }

        }
    }

    public void decodeLibrary() {
        //create a new List<DVD>
        if (!testMode) {
            List<DVD> loadedInventory = new ArrayList<>();
            try {
                //create a Scanner object that can read the file saved as an attribute
                Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
                //while the scanner has a line
                while (sc.hasNextLine()) {
                    //store the next line as a string
                    String currentLine = sc.nextLine();
                    //seperate the array by a delimeter
                    String[] values = currentLine.split(DELIMETER);
                    //store the 2nd item in the array as the title
                    String title = (values[1]);
                    //parse the 3rd item into an integer and store
                    Integer release = (Integer.parseInt(values[2]));
                    //store the 4th item in the array as the rating
                    String rating = (values[3]);
                    //store the 5th item in the array as the studio
                    String studio = (values[4]);
                    //break appart the 6th item by shedding the first and last character, "[, ]"
                    String withoutBrace = values[5].substring(1, values[5].length() - 1);
                    //split the string by taking away commas and whitespace
                    List<String> stringListOfComments = Arrays.asList(withoutBrace.split("\\s*,\\s*"));
                    //instantiate the DVD with the parameters
                    DVD dvd = new DVD(title, release, rating, studio, stringListOfComments);
                    //take the first item and parse it into an integer and make it the DVD's id
                    dvd.setId(Integer.parseInt(values[0]));
                    //store into new List<DVD>
                    loadedInventory.add(dvd);

                }

            } catch (FileNotFoundException ex) {
            }
            //create Iterator of DVD's for new List<DVD>
            Iterator<DVD> dvdIter = loadedInventory.iterator();
            //while the iter has a DVD
            while (dvdIter.hasNext()) {
                //store the next DVD
                DVD nextDvd = dvdIter.next();
                //put next DVD's id and DVD refrence in the field attribute Map<Integer, DVD>
                dvdInventory.put(nextDvd.getId(), nextDvd);
                //if the next DVD's id is the same as or greater than the internal ID Generator
                if (nextDvd.getId() >= idGenerator) {
                    //set the ID Generator to the id +1, keeping it unique;
                    idGenerator = nextDvd.getId() + 1;
                }

            }

        }
    }

    // return a List<DVD> of DVD's that curently are stored in the Dao's map
    @Override
    public List<DVD> listAll() {
        //create an ArrayList<DVD>
        List<DVD> dvds = new ArrayList<>();
        //for every Dvd in the Map
        dvdInventory.values()
                .stream()
                .forEach((dvd) -> {
                    dvds.add(dvd);
                });
        //return the new List
        return dvds;
    }

    /**
     * @param testMode the testMode to set
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    @Override
    public void update(DVD dvd) {
        dvdInventory.put(dvd.getId(),dvd);
        encodeLibrary();
    }
}
