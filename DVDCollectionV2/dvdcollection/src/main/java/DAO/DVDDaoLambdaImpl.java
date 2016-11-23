package DAO;

import DTO.DVD;
import DTO.Note;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Ollie
 */
public class DVDDaoLambdaImpl implements DVDDao {

    private Map<Integer, DVD> dvdMap = new HashMap<>();
    final String DELIMETER = "::";
    final String FILENAME = "DvdLibrary.txt";
    private Integer nextId = 1;

    public DVDDaoLambdaImpl() {

        List<DVD> dvds = decode();

        dvds.forEach(i -> {
            dvdMap.put(i.getId(), i);
            if (i.getId() > nextId) {
                nextId = i.getId() + 1;
            }
        });

    }

    @Override
    public DVD addDVD(DVD dvd) {
        dvd.setId(getNextID());
        dvdMap.put(dvd.getId(), dvd);
        encode();
        return dvd;
    }

    private int getNextID() {
        nextId++;
        return nextId;
    }

    @Override
    public void editDVD(Integer id, DVD dvd) {
        dvdMap.replace(id, dvd);
        encode();
    }

    @Override
    public DVD getDVDById(int id) {
        return dvdMap.get(id);
    }

    @Override
    public List<DVD> getDVDs() {
        List<DVD> dvds = new ArrayList<>(dvdMap.values());
        return dvds;
    }

    @Override
    public List<DVD> getDVDsByTitle(String title) {
        
        List<DVD> dvds = getDVDs();

        List<DVD> newList = dvds
                .stream()
                .filter(dvd -> dvd.getTitle().equalsIgnoreCase(title))                
                .collect(Collectors.toList());

        return newList;

    }

    @Override
    public void removeDVD(Integer id) {
        dvdMap.remove(id);
        encode();

    }

    private List<DVD> decode() {
        List<DVD> dvds = new ArrayList<>();

        

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);
                
                DVD dvd = new DVD();

                dvd.setId(Integer.parseInt(values[0]));
                dvd.setTitle(values[1]);
                dvd.setDate(Integer.parseInt(values[2]));
                dvd.setDirector(values[3]);
                dvd.setStudio(values[4]);
                dvd.setRating(values[5]);

                dvds.add(dvd);

            }

        } catch (FileNotFoundException ex) {

        }
        return dvds;
    }

    /**
     *
     * @param age : number of years from now
     * @return returns DVD list of DVD's newer than the parameter years
     */
    @Override
    public List<DVD>findDvdsNewerThan(Integer age) {

        List<DVD> dvdList = getDVDs();
        List<DVD> newDVDs = dvdList.stream().filter(s -> s.getDate()< age).collect(Collectors.toList());
        return newDVDs;

    }

    /**
     *
     * @param rating
     * @return List of DVD's with given rating
     */
    @Override
    public List<DVD> findAllDvdsWithMPAA(String rating) {

        List<DVD> dvdList = getDVDs();
        List<DVD> newList = dvdList.stream()
                .filter(dvd -> dvd.getRating().equalsIgnoreCase(rating))
                .collect(Collectors.toList());

        return newList;
    }

    /**
     *
     * @param director
     * @return List of directors sorted by MPAA rating
     */
    @Override
    public List<DVD> findAllDvdsByDirector(String director) {

        List<DVD> newDvdList = getDVDs();

        List<DVD> newList = newDvdList
                .stream().filter(dvd -> dvd.getDirector().equalsIgnoreCase(director))
                .sorted((DVD A, DVD B) -> A.getDirector().compareTo(B.getDirector()))
                .collect(Collectors.toList());

        return newList;

    }
    @Override
    public List<DVD> findAllDvdsByStudio(String studio) {

        List<DVD> studioList = getDVDs();

        List<DVD> newList = studioList
                .stream().filter(dvd -> dvd.getStudio().equalsIgnoreCase(studio))
                .sorted((DVD A, DVD B) -> A.getStudio().compareTo(B.getStudio()))
                .collect(Collectors.toList());

        return newList;

    }
    @Override
    public List<DVD> findDvdsByYear(Integer year) {
        
        List<DVD> yearList = getDVDs();
        
        List<DVD> newList = yearList
                .stream().filter(s -> s.getDate()< year)
                .collect(Collectors.toList());
                
           return newList;   
    
    }
    @Override
    /**
     *
     * @return average age of movies in list as double
     */

    public double findAverageAge() {

        List<DVD> dvdAgeList = getDVDs();

        double averageAge = dvdAgeList.stream().mapToLong(dvd -> (Calendar.getInstance().get(Calendar.YEAR)) - dvd.getDate()).average().getAsDouble();

        return averageAge;
    }

    public DVD findNewestDvd() {

        List<DVD> dvdYoungest = getDVDs();

        return dvdYoungest.stream()
                .min((a, b) -> Long.compare(a.getDate(), b.getDate()))
                .get();

    }

    public DVD findOldestDvd() {

        List<DVD> dvdOldest = getDVDs();

        return dvdOldest.stream()
                .max((a, b) -> Long.compare(a.getDate(), b.getDate()))
                .get();

    }

    @Override
    public double findAverageNotes() {
        
        NoteDao noteDao = new NoteDao();
        ArrayList<Note> notesArrayList = noteDao.getAllNotesList();
        
       return notesArrayList.size() / dvdMap.size();
               
         
        
}

    private void encode() {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME)));
            List<DVD> dvds = getDVDs();
            String line = "";
            for (DVD dvd : dvds) {

                line = dvd.getId() + DELIMETER
                        + dvd.getTitle() + DELIMETER
                        + dvd.getDate() + DELIMETER
                        + dvd.getDirector() + DELIMETER
                        + dvd.getStudio() + DELIMETER
                        + dvd.getRating();

                out.println(line);
                out.flush();
            }

            out.close();
        } catch (IOException e) {

        }

    }

}
