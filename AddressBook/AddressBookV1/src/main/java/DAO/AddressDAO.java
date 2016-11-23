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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AddressDAO {

    private Map<Integer, Address> addressMap = new HashMap<>();
    final String DELIMETER = "::";
    final String FILENAME = "AddressBook.txt";
    private Integer nextId = 1;

    public AddressDAO() {
        List<Address> addressList = decode();

        for (Address indexOfAddress : addressList) {
            addressMap.put(indexOfAddress.getId(), indexOfAddress);
            if (indexOfAddress.getId() > nextId) {
                nextId = indexOfAddress.getId();
            }

        }

    }

    public List<Address> list() {
        List<Address> addressArrayList = new ArrayList<>(addressMap.values());

        return addressArrayList;
    }

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

    public Set<Integer> deleteAddress(Integer id) {
        addressMap.remove(id);
        encode();
        Set<Integer> remainingIds = addressMap.keySet();
        return remainingIds;
    }

    // DECODE     
    public List<Address> decode() {
        List<Address> addressList = new ArrayList<Address>();

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
    public Integer getNextId() {
        return nextId;
    }

}//END Address DAO

