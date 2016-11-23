/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Checking;
import com.mycompany.dto.Savings;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class SavingsAccountDAO {

    private Map<Integer, Savings> accountRecord = new HashMap<>();
    private final String FILENAME = "SavingsAccounts.txt";
    private final String DELIMETER = ",";
    
    public SavingsAccountDAO(){
        decode();
    }
    
    public void createSavingsAccount(Integer pinNumber, Savings savings) {
        savings.setPinNumber(pinNumber);
        accountRecord.put(pinNumber, savings);
        encode();
    }
    
    public void updateSavingsAccount(Integer pinNumber, Savings savings) {
        
        for (Integer currentID: accountRecord.keySet()) {
            if(currentID == pinNumber){
                accountRecord.put(currentID, savings);
            }
        }
        
        encode();
    }
    
    public Savings getSavingsAccount(Integer pinNumber) {
        decode();
        
        for (Integer currentPin : accountRecord.keySet()) {
            if (pinNumber == currentPin) {
                return accountRecord.get(pinNumber);
            }
        }
        return null;
    }
    
    public void removeSavingsAccount(Integer idToRemove) {
        accountRecord.remove(idToRemove);
        encode();
    }
    
    public void encode() {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(FILENAME));
            
            String[] lines = new String[4];
            for (Savings current : accountRecord.values()) {
                lines[0] = String.valueOf(current.getPinNumber());
                lines[1] = String.valueOf(current.getBalance());
                lines[2] = String.valueOf(current.getSuspendedFunds());
                lines[3] = String.valueOf(current.isIsSuspended());
                writer.writeNext(lines);
                writer.flush();
            }
            writer.close();
            
        } catch (IOException ex) {
            System.out.print("Fix Encode\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }
    
    public void decode() {
        try {
            CSVReader reader = new CSVReader(new FileReader(FILENAME), ',', '\"');
            
            String[] nextLine;
            
            while ((nextLine = reader.readNext()) != null) {
                
                Savings account = new Savings();
                
                account.setPinNumber(Integer.parseInt(nextLine[0]));
                account.setBalance(Double.parseDouble(nextLine[1]));
                account.setSuspendedFunds(Double.parseDouble(nextLine[2]));
                
                account.setIsSuspended(Boolean.getBoolean(nextLine[3]));
                
                accountRecord.put(account.getPinNumber(), account);
                
            }
            
        } catch (Exception x) {
            
        }
    }
    public boolean isAValidAccount(int pinNumber){
        return accountRecord.keySet().stream().anyMatch((currentPin) -> (currentPin == pinNumber));
    }
    public String accountToDisply(int pinNumber){
        String line = null;
        for(Integer currentPin : accountRecord.keySet()){
            if(currentPin == pinNumber){
                line = "Current Savings Balance: "+accountRecord.get(currentPin).getBalance();
            }
        }
        return line;
    }
    
}
