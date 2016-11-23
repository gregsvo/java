package com.mycompany.flooringmasteryproject.dao;

import com.mycompany.flooringmasteryproject.dto.ConfigDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class ConfigDao {
    boolean isTest = true;
    final String FILENAME = "config.txt";
    ConfigDto dto = new ConfigDto();

    public ConfigDao() {
        dto.setIsTest(decode());
    }
    
    public boolean getConfig(){
        return dto.getIsTest();
    }
    
    
    
    private boolean decode(){
    boolean test = false;
        try {
            
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            String testIndicator = sc.nextLine();
                if (testIndicator.equalsIgnoreCase("test")){
                 test = true;
                }
                
    }
    catch (FileNotFoundException ex) {
//            return true;
            
        }
        return test;
    }
    

    
    
}//END
