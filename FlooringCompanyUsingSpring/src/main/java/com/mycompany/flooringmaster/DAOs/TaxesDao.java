/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.DAOs;

import com.mycompany.flooringmaster.DTOs.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public final class TaxesDao {

    final static String FILENAME = "taxRates.txt";
    final static String DELIMETER = ",";

    private List<Taxes> taxList = new ArrayList<>();
    private boolean testMode;
    EncodeDecode ed = new EncodeDecode();

    public TaxesDao(boolean testMode) {
        setup(testMode);
    }

    public void setup(boolean testMode) {
        taxList = ed.decode();
        this.testMode = testMode;
    }

    public String[] getStates() {
        String[] states = new String[taxList.size()];
        int i = 0;
        for (Taxes x : taxList) {
            states[i] = x.getState();
            i++;
        }
        return states;
    }

    public String[] getStatesTaxes() {
        String[] st = new String[taxList.size()];
        int i = 0;
        for (Taxes x : taxList) {
            st[i] = x.getState() + " " + x.getTaxRate() + "%";
            i++;
        }
        return st;
    }

    public double getTax(String stateName) {
        for (Taxes x : taxList) {
            if (x.getState().equalsIgnoreCase(stateName)) {
                return x.getTaxRate();
            }
        }
        return -1;
    }

    public void createState(String newStateName, double newTaxRate) {
        taxList.add(new Taxes(newStateName, newTaxRate));
        ed.encode();
    }

    public void removeState(String removeState) {
        for (Taxes thisTax : taxList) {
            if (thisTax.getState().equals(removeState)) {
                taxList.remove(thisTax);
                break;
            }
        }
        ed.encode();
    }

    public void renameState(String oldStateName, String newStateName) {
        for (Taxes thisTax : taxList) {
            if (thisTax.getState().equals(oldStateName)) {
                thisTax.setState(newStateName);
                break;
            }
        }
        ed.encode();
    }

    public void changeStateTax(String stateName, double newStateTax) {
        for (Taxes thisTax : taxList) {
            if (thisTax.getState().equals(stateName)) {
                thisTax.setTaxRate(newStateTax);
                break;
            }
        }
        ed.encode();
    }

    /**
     * @param testMode the testMode to set
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }
    
     private class EncodeDecode implements FileIO {

        @Override
        public List<Taxes> decode() {
            List<Taxes> taxesList = new ArrayList<>();
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
                //implemented to skip over the header line in text file
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String currentLine = sc.nextLine();
                    String[] values = currentLine.split(DELIMETER);
                    taxesList.add(new Taxes(values[0], Double.parseDouble(values[1])));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            Collections.sort(taxesList, new Comparator<Taxes>() {
                @Override
                public int compare(Taxes o1, Taxes o2) {
                    return o1.getState().compareToIgnoreCase(o2.getState());
                }
            });
            return taxesList;
        }

        @Override
        public void encode() {
            if (!testMode) {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));
                    pw.println("State, TaxRate");
                    for (Taxes currentTaxes : taxList) {
                        String line
                                = currentTaxes.getState() + DELIMETER
                                + currentTaxes.getTaxRate();

                        pw.println(line);
                        pw.flush();

                    }
                    pw.close();
                } catch (IOException ex) {
                    Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
