/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.dao;

import com.opencsv.CSVWriter;
import com.thesoftwarequild.flooringmvc.models.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class TaxesDao {

    private final String FILENAME = "taxesFile.csv"; // replace with actual file name
    private final String DELIMETER = ",";
    private Taxes taxes = new Taxes();
    private Map<String, Taxes> taxesMap = new HashMap<>();

    public TaxesDao() {
        List<Taxes> taxList = decode();

        for (Taxes tax : taxList) {
            taxesMap.put(tax.getState(), tax);
        }

    }

    public double calculateTax(double subtotal, String state) {

        // Take in the subtotal and the state.
        return (subtotal * retrieveTaxRate(state));

    }

    public double retrieveTaxRate(String state) {

        // Use the imported "state" as the key to the Map, so that you can return the associated tax rate
        return taxesMap.get(state).getTaxRate();
    }

    private void encode() {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(FILENAME));

            List<Taxes> storage = new ArrayList<>();

            String[] line = ("State,TaxRate").split(DELIMETER);
            writer.writeNext(line, false);
            for (Taxes tax : storage) {
                String[] newLine = {
                    tax.getState(),
                    String.valueOf(tax.getTaxRate())
                };               
                writer.writeNext(newLine, false);
            }           
            writer.close();

        } catch (IOException e) {

        }
    }

    private List<Taxes> decode() {
        List<Taxes> taxes = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            String currentLine = sc.nextLine();
            while (sc.hasNextLine()) {
                String currentLine2 = sc.nextLine();
                String[] values = currentLine2.split(DELIMETER);

                Taxes tax = new Taxes();

                tax.setState(values[0]);
                tax.setTaxRate(Double.parseDouble(values[1]));

                taxes.add(tax);
            }
        } catch (FileNotFoundException ex) {

        }

        return taxes;
    }

//    public void addState(Taxes tax, String state,) {
//        tax.setState(FILENAME);
//    }
}
