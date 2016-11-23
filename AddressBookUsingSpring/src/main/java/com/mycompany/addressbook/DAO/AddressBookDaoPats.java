/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addressbook.DAO;

import com.mycompany.addressbook.DTO.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public final class AddressBookDaoPats implements AddressBookDao {

    final static String FILENAME = "addresses.txt";
    final static String DELIMETER = "::";

    private final List<Address> addressList = new ArrayList<>();
    private Integer nextId = 0;
    private static boolean testMode = false;

    public AddressBookDaoPats() {
        decode();

        addressList
            .stream()
            .forEach(a -> {
                if (a.getId() > nextId) {
                    nextId = a.getId() + 1;
                }
            });
    }

    public void setTestMode(boolean mode) {
        this.testMode = mode;
    }
    @Override
    public List<Address> searchByLastName(String lastName) {
        return addressList
                .stream()
                .filter((entry) -> (entry.getLastName().equals(lastName)))
                .collect(Collectors.toList());
    }
    @Override
    public List<Address> searchByCity(String city) {
        return addressList
                .stream()
                .filter((entry) -> (entry.getCity().equals(city)))
                .collect(Collectors.toList());
    }
    @Override
    public List<Address> searchByState(String state) {
        return addressList
                .stream()
                .filter((entry) -> (entry.getState().equals(state)))
                .sorted((a1, a2) -> (a1.getCity().compareToIgnoreCase(a2.getCity())))
                .collect(Collectors.toList());
    }
    @Override
    public List<Address> searchByZip(String zip) {
        return addressList
                .stream()
                .filter((entry) -> (entry.getZip().equals(zip)))
                .collect(Collectors.toList());
    }

    public String[] getKeys() {
        return addressList
                .stream()
                .map(a -> a.getId().toString())
                .sorted((o1, o2) -> o1.compareTo(o2))
                .collect(Collectors.toList())
                .toArray(new String[addressList.size()]);
    }
    @Override
    public void update(Address updatedAddress) {
        if (updatedAddress != null) {
            Address previousAddress = addressList
                    .stream()
                    .filter(x -> Objects.equals(x.getId(), updatedAddress.getId()))
                    .findFirst()
                    .get();
            addressList.remove(previousAddress);
            addressList.add(updatedAddress);
            encode();
        }
    }
    @Override
    public Address get(Integer id) {
        Address copy =
                addressList
                    .stream()
                    .filter(a -> Objects.equals(a.getId(), id))
                    .findFirst()
                    .get();
        //returns a single object
        return copy;
    }
    
    @Override
    public Address create(Address newAddress) {
        newAddress.setId(nextId);
        addressList.add(newAddress);
        nextId++;
        if (!testMode) {
            encode();
        }
        return newAddress;
    }
    @Override
    public void delete(Integer id) {
        boolean exists = addressList.stream().anyMatch(x -> Objects.equals(x.getId(), id));

        if (exists) {
            addressList.remove(addressList
                    .stream()
                    .filter(a -> Objects.equals(a.getId(), id))
                    .findFirst()
                    .get());
            encode();
        }
    }
    
    @Override
    public List<Address> list() {
        List<Address> copy = new ArrayList<>(addressList);

        return copy;
    }

    private void encode() {
        if (!testMode) {
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));

                addressList
                        .stream()
                        .forEach((currentAddress) -> {
                            pw.println(
                                    currentAddress.getFirstName() + DELIMETER
                                    + currentAddress.getLastName() + DELIMETER
                                    + currentAddress.getStreetNumber() + DELIMETER
                                    + currentAddress.getStreetName() + DELIMETER
                                    + currentAddress.getCity() + DELIMETER
                                    + currentAddress.getState() + DELIMETER
                                    + currentAddress.getZip() + DELIMETER
                                    + currentAddress.getId()
                            );
                            pw.flush();
                        });
                pw.close();
            } catch (IOException ex) {
                Logger.getLogger(AddressBookDaoPats.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Address> decode() {
        if (!testMode) {

            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
                while (sc.hasNextLine()) {

                    String currentLine = sc.nextLine();
                    String[] values = currentLine.split(DELIMETER);
                    
                    Address currentAddress = new Address();
                    
                    currentAddress.setFirstName(values[0]);
                    currentAddress.setLastName(values[1]);
                    currentAddress.setStreetNumber(values[2]);
                    currentAddress.setStreetName(values[3]);
                    currentAddress.setCity(values[4]);
                    currentAddress.setState(values[5]);
                    currentAddress.setZip(values[6]);
                    currentAddress.setId(Integer.parseInt(values[7]));

                    addressList.add(currentAddress);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddressBookDaoPats.class.getName()).log(Level.SEVERE, null, ex);
            }

            return addressList;
        } else {
            //Do nothing since in test mode there is no read/write to file
            return null;
        }
    }
}
