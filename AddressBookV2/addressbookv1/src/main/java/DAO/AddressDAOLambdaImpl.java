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
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class AddressDAOLambdaImpl implements AddressDAO {

    private Map<Integer, Address> addressMap = new HashMap<>();
    final String DELIMETER = "::";
    final String FILENAME = "AddressBook.txt";
    private Integer nextId = 1;

    public AddressDAOLambdaImpl() {
        List<Address> addressList = decode();

        for (Address indexOfAddress : addressList) {
            addressMap.put(indexOfAddress.getId(), indexOfAddress);
            if (indexOfAddress.getId() > nextId) {
                nextId = indexOfAddress.getId();
            } //refactor to use lambda maybe?

        }
    }

    @Override
    public Address addAddress(Address address) {
        address.setId(getNextId());
        addressMap.put(address.getId(), address);
        encode();
        return address;
    }

    private List<Address> decode() {

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

    @Override
    public Set<Integer> deleteAddress(Integer id) {
        addressMap.remove(id);
        encode();
        Set<Integer> remainingIds = addressMap.keySet();
        return remainingIds;
    }

    @Override
    public Integer getNextId() {
        nextId++;
        return nextId;

    }
    @Override
    public List<Address> list() {

        List<Address> addressArrayList = new ArrayList<>(addressMap.values());

        return addressArrayList;

    }

    public List<Address> searchByName(String lastName) {
        List<Address> addressList = list();
        return addressList
                .stream()
                .filter(address -> address.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public List<Address> searchByCity(String city) {
        List<Address> addressList = list();

        return addressList
                .stream()
                .filter(addresses -> addresses.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());

    }

    //SEARCH BY STATE
    public List<Address> searchByState(String state) {
        List<Address> addressList = list();

        return addressList
                .stream()
                .filter(addresses -> addresses.getState().equalsIgnoreCase(state))
                .sorted((Address A, Address B) -> A.getCity().compareTo(B.getCity()))
                .collect(Collectors.toList());

    }

    public List<Address> searchByZip(String zip) {
        List<Address> addressList = list();

        return addressList
                .stream()
                .filter(addresses -> addresses.getZip().equalsIgnoreCase(zip))
                .collect(Collectors.toList());
    }

    
    
   private void encode() {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME)));
            List<Address> addressList = list();
            String line = new String();
            addressList
                    .forEach((Address address) -> {
                        String concat = line.concat(
                                address.getId() + DELIMETER
                                + address.getFirstName() + DELIMETER
                                + address.getLastName() + DELIMETER
                                + address.getStreet() + DELIMETER
                                + address.getCity() + DELIMETER
                                + address.getState() + DELIMETER
                                + address.getZip()
                        );
                        out.println(concat);
                        out.flush();    
                    }
                    );

            out.close();
        } catch (IOException e) {

        }

    }
}
