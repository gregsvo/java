/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.contactlistmvc;

import com.thesoftwarequild.addresslistmvc.dao.AddressDao;
import com.thesoftwarequild.addresslistmvc.dao.AddressDaoImpl;
import com.thesoftwarequild.addresslistmvc.models.Address;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class JUnitTest {
    
    AddressDao addressDao;
    Address address1;
    
    private Integer id;
    private String firstName = "TestFName";
    private String lastName = "TestLName";
    private String address = "TestAddress";
    private String city = "TestCity";
    private String state = "TestState";
    private String zip = "TestZip";    
    
    public JUnitTest() {
    }
    
    @Before
    public void setUp() {
    
        addressDao = new AddressDaoImpl();
        address1 = new Address();
        
        address1.setFirstName(firstName);
        address1.setLastName(lastName);
        address1.setAddress(address);
        address1.setCity(city);
        address1.setState(state);
        address1.setZip(zip);        
    
        addressDao.add(address1);
        id = address1.getId();
    }
    
    @After
    public void tearDown() {
        
        addressDao.remove(id);
        
    }

    @Test
    public void testCreateAddress() {
              
        Assert.assertNotNull(address1);   

        

    }
    
    @Test
    public void testGetAddress()    {
        
        Assert.assertEquals("TestFName", addressDao.get(id).getFirstName());
        Assert.assertEquals("TestLName", addressDao.get(id).getLastName());
        Assert.assertEquals("TestCity", addressDao.get(id).getCity());       
        Assert.assertEquals("TestState", addressDao.get(id).getState());        
        Assert.assertEquals("TestZip", addressDao.get(id).getZip());
    }
    
    @Test
    public void testUpdate()    {
        
        address1.setFirstName("Test1");
        address1.setLastName("Test2");
        address1.setCity("Test3");
        address1.setState("Test4");
        address1.setZip("Test5"); 
        addressDao.update(address1);  
        
        Assert.assertEquals("Test1", addressDao.get(id).getFirstName());
        Assert.assertEquals("Test2", addressDao.get(id).getLastName());
        Assert.assertEquals("Test3", addressDao.get(id).getCity());       
        Assert.assertEquals("Test4", addressDao.get(id).getState());        
        Assert.assertEquals("Test5", addressDao.get(id).getZip());        
           
    }
    
    @Test
    public void testList()  {
        
        List<Address> contactList = addressDao.list();
        
        Assert.assertNotNull(contactList); //checks to see that contactList actually exists as an object
        
    }
    
//    @Test
//    public void testSearchByLastName() {
//        
//        Assert.assertEquals(1, addressDao.searchByLastName(lastName).size());
//        
//    }
//    
//    @Test
//    public void testSearchByCity(){
//        
//        Assert.assertEquals(1, addressDao.searchByCity(city).size());        
//        
//    }
//    
//    @Test
//    public void testSearchByState(){
//        
//        Assert.assertEquals(1, addressDao.searchByState(state).size());        
//        
//    }
//    
//    @Test
//    public void testSearchByZip(){
//        
//        Assert.assertEquals(1, addressDao.searchByZip(zip).size());       
//        
//  }
}
