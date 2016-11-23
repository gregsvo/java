/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.addresslistmvc.dao;

import com.thesoftwarequild.addresslistmvc.models.Address;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface AddressDao {
 
    public Address add(Address address);
    public Address get(Integer id);
    public void remove(Integer id);
    public void update(Address address);
    public List<Address> list();
    public List<Address> searchAll(String search);
    
    
}

