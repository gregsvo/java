package DAO;

import DTO.DVD;
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

public class DVDDao {

    private Map<Integer, DVD> dvdMap = new HashMap<>();
    final String DELIMETER = "::";
    final String FILENAME = "DvdLibrary.txt";
    private Integer nextId = 1;

    public DVDDao() {

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

    public void addDVD(DVD dvd) {
        dvd.setId(getNextID());
        dvdMap.put(dvd.getId(), dvd);
        encode();
    }

    public void removeDVD(Integer id) {
        dvdMap.remove(id);

        encode();
    }

    public List<DVD> getDVDs() {
        List<DVD> dvds = new ArrayList<>(dvdMap.values());
        return dvds;
    }

    public List<DVD> getDVDsByTitle(String title) {
        ArrayList<DVD> dvds = new ArrayList<>();

        for (DVD dvd : getDVDs()) {
            if (title.toLowerCase().equals(dvd.getTitle().toLowerCase())) {

                dvds.add(dvd);

            }
        }
        return dvds;
    }
    
    public DVD getDVDById (int id) {
      return dvdMap.get(id);

    }

    public void editDVD(Integer id, DVD dvd) {
        dvdMap.replace(id, dvd);
        encode();

    }
    

    // DECODE     
    public List<DVD> decode() {
        List<DVD> dvds = new ArrayList<DVD>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);

                DVD dvd = new DVD();

                dvd.setId(Integer.parseInt(values[0]));
                dvd.setTitle(values[1]);
                dvd.setDate(values[2]);
                dvd.setDirector(values[3]);
                dvd.setStudio(values[4]);
                dvd.setRating(values[5]);
                dvd.setNote(values[6]);
                dvd.setNote2(values[7]);

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
                        + dvd.getRating() + DELIMETER
                        + dvd.getNote() + DELIMETER
                        + dvd.getNote2() + DELIMETER;

                out.println(line);
                out.flush();
            }

            out.close();
        } catch (IOException e) {

        }

    }

}//END of DaoClass

