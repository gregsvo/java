/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Phone;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public final class PhoneDAO implements iDAO {

    boolean testMode = false;

    public PhoneDAO() {
        decode();
    }

    public PhoneDAO(boolean isTestMode) {
        if (isTestMode) {
            this.testMode = true;
        }
    }

    private final Set<Phone> phoneInventory = new HashSet<>();
    private final String FILENAME = "phone.txt";

    @Override
    public void create(String... args) {
        try {
            if (args.length == 8) {
                Phone phoneToBe = new Phone(args);
                encode();
                phoneInventory.add(phoneToBe);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (Exception ex) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public boolean update(String... args) {
        Phone phoneToUpdate = phoneInventory
                .stream()
                .filter(phone -> phone.getProductName().equals(args[0]))
                .findFirst()
                .get();

        Method[] availMethods = phoneToUpdate.getClass().getMethods();
        Method methodToUpd = Arrays.stream(availMethods).filter(method -> method.getName().equals(args[1])).findFirst().get();

        try {
            methodToUpd.invoke(phoneToUpdate, args[2]);
            encode();
            return true;
        } catch (IllegalArgumentException ex) {

            if (args[1].contains("Price") || args[1].contains("ScreenSize")) {

                try {
                    methodToUpd.invoke(phoneToUpdate, Double.parseDouble(args[2]));
                    encode();
                    return true;
                } catch (Exception exc) {
                    return false;
                }

            } else if (args[1].contains("Stock")) {

                try {
                    methodToUpd.invoke(phoneToUpdate, Integer.parseInt(args[2]));
                    encode();
                    return true;
                } catch (Exception exce) {
                    return false;
                }

            } else {

                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public Phone get(String... args) {
//        Phone phoneToBe = new Phone();
        return phoneInventory.stream()
                .filter(phone -> phone.getProductName().equals(args[0]))
                .findFirst()
                .get();
    }

    @Override
    public void delete(String... args) {
        Phone phoneToBe = phoneInventory.stream()
                .filter(phone -> phone.getProductName().equals(args[0]))
                .findFirst()
                .get();
        phoneInventory.remove(phoneToBe);
        encode();
    }

    @Override
    public void encode() {
        if (!testMode) {
            try {
                CSVWriter writer = new CSVWriter(new FileWriter(FILENAME));

                phoneInventory.stream().forEach(currentPhone -> {
                    String[] line = new String[8];
                    line[0] = currentPhone.getProductName();
                    line[1] = String.valueOf(currentPhone.getPrice());
                    line[2] = String.valueOf(currentPhone.getMaxStock());
                    line[3] = String.valueOf(currentPhone.getEndangeredStockNumber());
                    line[4] = String.valueOf(currentPhone.getCurrentStock());

                    line[5] = String.valueOf(currentPhone.getScreenSize());
                    line[6] = String.valueOf(currentPhone.isIs4G());
                    line[7] = currentPhone.getCarrier();
                    writer.writeNext(line);

                    try {
                        writer.flush();
                    } catch (IOException ex) {
                    }

                });

            } catch (IOException ex) {

            }
        }
    }

    @Override
    public void decode() {
        if (!testMode) {
            try {
                CSVReader reader = new CSVReader(new FileReader(FILENAME), ',', '\"');

                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    Phone currentPhone = new Phone(nextLine[0], nextLine[1], nextLine[2],
                            nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7]);

                    phoneInventory.add(currentPhone);

                }
//            }

            } catch (Exception x) {

            }
        }
    }

    @Override
    public String getName() {
        return "Phone";
    }

    @Override
    public String[] getExistingProducts() {

        List<String> existingList
                = phoneInventory.stream()
                .map(p -> p.getProductName())
                .collect(Collectors.toList());
        String[] garbage = new String[existingList.size()];
        return existingList.stream().collect(Collectors.toList()).toArray(garbage);

    }

    @Override
    public String[] getMethods() {
        return Arrays.stream(Phone.class.getMethods())
                .map(meth -> meth.getName())
                .filter(name -> name.contains("set"))
                .collect(Collectors.toList())
                .toArray(new String[8]);
    }
}
