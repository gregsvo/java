/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Checking;
import com.mycompany.dto.Customer;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class CheckingAccountDAO {
    
    private Map<Integer, Checking> accountRecord= new HashMap<>();
    private final String FILENAME = "CheckingAccounts.txt";
    private final String DELIMETER = ",";
    
    public CheckingAccountDAO(){
        decode();
    }
    
    public void createCheckingAccount(Integer pin, Checking account){
        account.setPinNumber(pin);
        accountRecord.put(pin, account);
        encode();
    }
    
    public void updateCheckingAccount(Integer pinNumber, Checking checking){
         for (Integer currentID: accountRecord.keySet()) {
            if(currentID == pinNumber){
                accountRecord.put(currentID, checking);
            }
        }
        
        encode();
    }
    
    public Checking getCheckingAccount(Integer pin){
        decode();
        for(Integer currentPin: accountRecord.keySet()){
            if(Objects.equals(pin, currentPin)){
                return accountRecord.get(pin);
            }
        }
        return null;
    }
    
    public void removeSavingsAccount(Integer idToRemove) {
        accountRecord.remove(idToRemove);
        encode();
    }
    
    public void encode(){
        
            try {
                CSVWriter writer = new CSVWriter(new FileWriter(FILENAME));
                
                String[] lines = new String[5];
                for(Checking current : accountRecord.values()){
                    lines[0] = String.valueOf(current.getPinNumber());
                    lines[1] = String.valueOf(current.getBalance());
                    lines[2] = String.valueOf(current.getSuspendedFunds());
                    lines[3] = String.valueOf(current.getOverDraftCounter());
                    lines[4] = String.valueOf(current.isIsSuspended());
                    writer.writeNext(lines);
                    writer.flush();
                }
                writer.close();
                
                
            } catch (IOException ex) {
                System.out.print("Fix Encode\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }
        
    }
    
    public void decode(){
        try {
            CSVReader reader = new CSVReader(new FileReader(FILENAME), ',', '\"');
            
            String[] nextLine;
            
            while ((nextLine = reader.readNext()) != null) {

                Checking account = new Checking();

                account.setPinNumber(Integer.parseInt(nextLine[0]));
                account.setBalance(Double.parseDouble(nextLine[1]));
                account.setSuspendedFunds(Double.parseDouble(nextLine[2]));
                account.setOverDraftCounter(Integer.parseInt(nextLine[3]));
                account.setIsSuspended(Boolean.getBoolean(nextLine[4]));

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
                line = "Current Checking Balance: "+accountRecord.get(currentPin).getBalance();
            }
        }
        return line;
    }
}
