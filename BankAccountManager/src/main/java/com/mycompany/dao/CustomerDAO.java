/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Customer;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author apprentice
 */
public class CustomerDAO{

    private Map<Integer, Customer> customerRecord = new HashMap<>();
    private int pinNumberGenerator = 1000;
    private final String FILENAME = "Customer.txt";
    private final String DELIMETER = ",";
    
    public CustomerDAO(){
        decode();
    }
    
    public void createCustomer(Customer customer) {
        customer.setPinNumber(pinNumberGenerator);
        pinNumberGenerator++;
        
        customerRecord.put(customer.getPinNumber(), customer);
        encode();

    }

    public void updateCustomer(Integer pin, Customer customer) {
        for(Integer pinNumber : customerRecord.keySet()){
            if(pin == pinNumber){
                customerRecord.put(pinNumber, customer);
                break;
            }
        }
        encode();

    }

    public Customer getCustomer(Integer pin) {
        decode();
        Customer customer = null;
        for (Entry customerInfo : customerRecord.entrySet()) {
         if (customerInfo.getKey() == pin) {
                customer = (Customer) customerInfo.getValue();
            }   
        }
        return customer;
    }

    public void removeCustomer(int pinNumber) {
        for(Integer pin : customerRecord.keySet()){
            if(pin == pinNumber){
                customerRecord.remove(pin);
                break;
            }
        }
        encode();
    }

    private void encode() {
            
            try {
                CSVWriter writer = new CSVWriter(new FileWriter(FILENAME));
                
                String[] lines = new String[3];
                for(Customer current : customerRecord.values()){
                    lines[0] = String.valueOf(current.getPinNumber());
                    lines[1] = current.getLastName();
                    lines[2] = current.getFirstName();
                    writer.writeNext(lines);
                    writer.flush();
                }
                writer.close();
                
                
            } catch (IOException ex) {
                System.out.print("Fix Encode\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }
                
          
        
    }

    private void decode() {

        try {
            CSVReader reader = new CSVReader(new FileReader(FILENAME), ',', '\"');
            
            String[] nextLine;
            
            while ((nextLine = reader.readNext()) != null) {

                Customer customer = new Customer();

                customer.setPinNumber(Integer.parseInt(nextLine[0]));
                customer.setLastName(nextLine[1]);
                customer.setFirstName(nextLine[2]);

                customerRecord.put(customer.getPinNumber(), customer);
                
                if(customer.getPinNumber()>= pinNumberGenerator){
                    pinNumberGenerator = customer.getPinNumber()+1;
                }
            }

        } catch (Exception x) {

        }

    }

    public List<Customer> list() {
        decode();
        List<Customer> customerList = new ArrayList();

        customerRecord.values().stream().forEach((currentCustomer) -> {
            customerList.add(currentCustomer);
        });

        return customerList;
    }

    public String[] existingCustomers() {
        decode();
        String[] existing = new String[customerRecord.size()];

        int i = 0;
        for (Customer current : customerRecord.values()) {
            existing[i] = current.getPinNumber() + ": " + current.getLastName();
            i++;
        }
        return existing;
    }
    public boolean isAValidAccount(int pinNumber){
        return customerRecord.keySet().stream().anyMatch((currentPin) -> (currentPin == pinNumber));
    }
}
