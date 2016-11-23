/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Snack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class VendingDao {

    private List<Snack> allSnacks = new ArrayList<>();
    private final static String DELIMETER = "::";
    private final static String FILENAME = "snacks.txt";

    public VendingDao() {
        List<Snack> data = new ArrayList<>();
        data = decode();

        for (Snack s : data) {

            allSnacks.add(s);
        }
    }

    public static List<Snack> decode() {
        List<Snack> snacks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);

                Snack aSnack = new Snack();

                aSnack.setId(Integer.parseInt(values[0]));
                aSnack.setName(values[1]);
                aSnack.setCost(Double.parseDouble(values[2]));
                aSnack.setCount(Integer.parseInt(values[3]));

                snacks.add(aSnack);
            }

        } catch (Exception x) {

        }
        return snacks;
    }

    public List<Snack> listAllAvailableSnacks() {
        List<Snack> snacks = new ArrayList<>();
        snacks = decode();
        for (Snack s : snacks) {
            if (s.getCount()<=0) {
                snacks.remove(s);
            }
        }
        return snacks;

    }

    public Snack updateInventory(Snack aSnack) {
        int index = 0;
        int i = 0;
        Snack snackToUpdate = aSnack;
        for (Snack s : getAllSnacks()) {
            if (s.getId() == snackToUpdate.getId()) {
                int newCount = snackToUpdate.getCount() - 1;
                aSnack.setCount(newCount);
                index = i;
            }
            i++;
        }
        getAllSnacks().set(index, snackToUpdate);
        encode();
        return snackToUpdate;
    }

    private void encode() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));
            
            String line = "";
            for (Snack s : getAllSnacks()) {
                
                line = s.getId() + DELIMETER 
                        + s.getName() + DELIMETER 
                        + s.getCost() + DELIMETER 
                        + s.getCount();
            pw.println(line);
            pw.flush();
            }
            
        }
        catch (Exception x) {
            
        }
    
    }

    /**
     * @return the allSnacks
     */
    public List<Snack> getAllSnacks() {
        return allSnacks;
    }
    
}
