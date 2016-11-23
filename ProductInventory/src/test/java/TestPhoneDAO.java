/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.dao.PhoneDAO;
import com.mycompany.dto.Phone;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPhoneDAO {

    PhoneDAO phoneDao = new PhoneDAO();

    public TestPhoneDAO() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateNothing() {
        try {
            phoneDao.create("II");
            Assert.assertFalse(true);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

}
