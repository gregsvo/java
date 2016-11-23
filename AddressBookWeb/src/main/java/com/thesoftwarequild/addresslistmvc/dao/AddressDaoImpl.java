/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.addresslistmvc.dao;

import com.thesoftwarequild.addresslistmvc.models.Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class AddressDaoImpl implements AddressDao {
    
    private static Integer nextId = 0;
    private Map<Integer, Address> addressMap =  new HashMap();
    
    
    public AddressDaoImpl(){
        
//        Address address1 = new Address();
//        address1.setFirstName("Bob");
//        address1.setLastName("Smith");
//        address1.setAddress("50 jones street");
//        address1.setCity("Cleveland");
//        address1.setState("Oh");
//        address1.setZip("44444");
//        
//        add(address1);
//        
//        Address address2 = new Address();
//        address2.setFirstName("Chris");
//        address2.setLastName("Smith");
//        address2.setAddress("123 main street");
//        address2.setCity("New York");
//        address2.setState("NY");
//        address2.setZip("54321");
//        
//        add(address2);     
//        
//        Address address3 = new Address();
//        address3.setFirstName("John");
//        address3.setLastName("Smith");
//        address3.setAddress("578 Keyboard Ave");
//        address3.setCity("Chicago");
//        address3.setState("Test");
//        address3.setZip("33432");
//        
//        add(address3);     
//        
    }
    
 

    @Override
    public Address add(Address address) {

        nextId++;
        
        address.setId(nextId);
        addressMap.put(address.getId(), address);
        
        return address;
       
    }

    @Override
    public Address get(Integer id) {
        return addressMap.get(id);     
    }

    @Override
    public void remove(Integer id) {
        addressMap.remove(id);
    }

    @Override
    public void update(Address address) {
        addressMap.put(address.getId(), address);
    }

    @Override
    public List<Address> list() {
        return new ArrayList(addressMap.values());
    }
    
    
    @Override
    public List<Address> searchAll(String search) {

        return addressMap.values()
                .stream()
                .filter(contact -> contact.getFirstName().equalsIgnoreCase(search) || 
                        contact.getLastName().equalsIgnoreCase(search) ||
                        contact.getAddress().equalsIgnoreCase(search) ||
                        contact.getCity().equalsIgnoreCase(search) ||
                        contact.getState().equals(search) ||
                        contact.getZip().equals(search))
                .collect(Collectors.toList());

    }
}
