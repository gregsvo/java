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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DVDDaoImpl implements DVDDao {

    private Map<Integer, DVD> dvdMap = new HashMap<>();
    final String DELIMETER = "::";
    final String FILENAME = "DvdLibrary.txt";
    private Integer nextId = 1;

    public DVDDaoImpl() {

        List<DVD> dvds = decode();

        for (DVD i : dvds) {
            dvdMap.put(i.getId(), i);

            if (i.getId() > nextId) {
                nextId = i.getId() + 1;
            }

        }
    }

    private int getNextID() {
        nextId++;
        return nextId;
    }

    @Override
    public DVD addDVD(DVD dvd) {
        dvd.setId(getNextID());
        dvdMap.put(dvd.getId(), dvd);
        encode();
        return dvd;
    }

    @Override
    public void removeDVD(Integer id) {
        dvdMap.remove(id);

        encode();
    }

    @Override
    public List<DVD> getDVDs() {
        List<DVD> dvds = new ArrayList<>(dvdMap.values());
        return dvds;
    }

    @Override
    public List<DVD> getDVDsByTitle(String title) {
        ArrayList<DVD> dvds = new ArrayList<>();

        for (DVD dvd : getDVDs()) {
            if (title.toLowerCase().equals(dvd.getTitle().toLowerCase())) {

                dvds.add(dvd);

            }
        }
        return dvds;
    }

    @Override
    public DVD getDVDById(int id) {
        return dvdMap.get(id);

    }

    @Override
    public void editDVD(Integer id, DVD dvd) {
        dvdMap.replace(id, dvd);
        encode();

    }

    // DECODE     
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

    // ENCODE
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

    @Override
    public List<DVD> findDvdsNewerThan(Integer age) {
        List<DVD> dvdList = getDVDs();
        List<DVD> newestDvds = new ArrayList<>();
        for (DVD newest : dvdList) {
            if (2016 - (newest.getDate()) > age) {
                newestDvds.add(newest);
            }
        }
        return newestDvds;
    }

    @Override
    public List<DVD> findDvdsByYear(Integer year) {
        List<DVD> dvdList = getDVDs();
        List<DVD> years = new ArrayList<>();
        for (DVD byYear : dvdList) {
            if (byYear.getDate() == year) {
                years.add(byYear);
            }
        }
        return years;
    }

    @Override
    public List<DVD> findAllDvdsWithMPAA(String rating) {
        List<DVD> dvdList = getDVDs();
        List<DVD> mpaa = new ArrayList<>();
        for (DVD ratings : dvdList) {
            if (ratings.getRating().equalsIgnoreCase(rating)) {
                mpaa.add(ratings);
            }
        }
        return mpaa;
    }

    @Override
    public List<DVD> findAllDvdsByDirector(String director) {
        List<DVD> dvdList = getDVDs();
        List<DVD> direct = new ArrayList<>();
        for (DVD d : dvdList) {
            if (d.getRating().equalsIgnoreCase(director)) {
                direct.add(d);
            }
        }
        return direct;
    }

    @Override
    public double findAverageAge() {
        double total;
        double trueTotal = 0;
        List<DVD> dvdList = getDVDs();
        List<Double> average = new ArrayList<>();
        for (DVD age : dvdList) {
            total = 2016 - age.getDate();
            average.add(total);
        }
        for (Double ages : average) {
            trueTotal += ages;
        }
        double trueAverage = trueTotal / (average.size());
        return trueAverage;
    }

    @Override
    public double findAverageNotes() {
        NoteDao noteDao = new NoteDao();
        ArrayList<Note> notesArrayList = noteDao.getAllNotesList();
        
       return notesArrayList.size() / dvdMap.size();
    }

    @Override
    public List<DVD> findAllDvdsByStudio(String studio) {
        List<DVD> dvdList = getDVDs();
        List<DVD> stud = new ArrayList<>();
        for (DVD s : dvdList) {
            if (s.getStudio().equalsIgnoreCase(studio)) {
                stud.add(s);
            }
        }
        return stud;
    }

}//END of DaoClass

