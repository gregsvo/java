package com.mycompany.flooringmasteryproject.dao;

import com.mycompany.flooringmasteryproject.dto.TaxesDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaxesDao {

    private List<TaxesDto> taxesList = new ArrayList<>();
    final String DELIMETER = ",";
    private String FILENAME = "taxes.txt";
    private Integer nextId = 1;

    public TaxesDao() {

        decode();
    }

    // DECODE     
    public List<TaxesDto> decode() {

        ////List<TaxesDto> taxesList = new ArrayList<TaxesDto>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            String currentLineReading = sc.nextLine();
            while (sc.hasNextLine()) {

                currentLineReading = sc.nextLine();
                String[] values = currentLineReading.split(DELIMETER);

                TaxesDto stateTax = new TaxesDto();
                stateTax.setState(values[0]);
                stateTax.setTaxRate(Double.parseDouble(values[1]));

                taxesList.add(stateTax);

            }

        } catch (FileNotFoundException ex) {

        }
            return this.taxesList;
    }
    
    public List<TaxesDto> getTaxesList() {
    
        return this.taxesList;
    }

}//END Class TaxesDao
