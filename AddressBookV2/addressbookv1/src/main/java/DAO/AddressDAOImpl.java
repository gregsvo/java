package DAO;

import DTO.Address;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class AddressDAOImpl implements AddressDAO {

    private Map<Integer, Address> addressMap = new HashMap<>();
    final String DELIMETER = "::";
    final String FILENAME = "AddressBook.txt";
    private Integer nextId = 1;

    public AddressDAOImpl() {
        List<Address> addressList = decode();

        for (Address indexOfAddress : addressList) {
            addressMap.put(indexOfAddress.getId(), indexOfAddress);
            if (indexOfAddress.getId() > nextId) {
                nextId = indexOfAddress.getId();
            }

        }

    }

    @Override
    public List<Address> list() {
        List<Address> addressArrayList = new ArrayList<>(addressMap.values());

        return addressArrayList;
    }

    @Override
    public Address addAddress(Address address) {
        address.setId(getNextID());
        addressMap.put(address.getId(), address);
        encode();
        return address;
    }

    private int getNextID() {
        nextId++;
        return getNextId();
    }

    @Override
    public Set<Integer> deleteAddress(Integer id) {
        addressMap.remove(id);
        encode();
        Set<Integer> remainingIds = addressMap.keySet();
        return remainingIds;
    }

    // DECODE     
    private List<Address> decode() {
        List<Address> addressList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);

                Address address = new Address();

                address.setId(Integer.parseInt(values[0]));
                address.setFirstName(values[1]);
                address.setLastName(values[2]);
                address.setStreet(values[3]);
                address.setCity(values[4]);
                address.setState(values[5]);
                address.setZip(values[6]);

                addressList.add(address);

            }

        } catch (FileNotFoundException ex) {

        }
        return addressList;
    }

    // ENCODE
    private void encode() {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME)));
            List<Address> addressList = list();
            String line = "";
            for (Address address : addressList) {

                line = address.getId() + DELIMETER
                        + address.getFirstName() + DELIMETER
                        + address.getLastName() + DELIMETER
                        + address.getStreet() + DELIMETER
                        + address.getCity() + DELIMETER
                        + address.getState() + DELIMETER
                        + address.getZip();

                out.println(line);
                out.flush();
            }

            out.close();
        } catch (IOException e) {

        }

    }

    /**
     * @return the nextId
     */
    @Override
    public Integer getNextId() {
        nextId++;
        return nextId;
    }

    @Override
    public List<Address> searchByName(String lastName) {
       List<Address> addressList = list();
       List<Address> lastNameList = new ArrayList<>();
       for (Address last : addressList){
           if(last.getLastName().equalsIgnoreCase(lastName)){
               lastNameList.add(last);
           }
       }
       return lastNameList;
    }

    @Override
    public List<Address> searchByCity(String city) {
     List<Address> addressList = list();
       List<Address> cityList = new ArrayList<>();
       for (Address cities : addressList){
           if(cities.getCity().equalsIgnoreCase(city)){
               cityList.add(cities);
           }
       }
       return cityList;
    }

    @Override
    public List<Address> searchByState(String state) {
       List<Address> addressList = list();
        List<Address> stateList = new ArrayList<>();
        for (Address states : addressList){
            if(states.getState().equalsIgnoreCase(state)){
                stateList.add(states);
            }
        }
       
        Collections.sort(stateList, new CustomComparator());
        
        return stateList;
                
                //.sort((Address A, Address B)->A.getCity().compareTo(B.getCity()));
    }

    @Override
    public List<Address> searchByZip(String zip) {
    List<Address> addressList = list();
    List<Address> zipList = new ArrayList<>();
    for (Address zipAddress : addressList) {
        if(zipAddress.getZip().equalsIgnoreCase(zip))   {
            zipList.add(zipAddress);
        }
    }
        return zipList;
    
    }

}//END Address DAO

