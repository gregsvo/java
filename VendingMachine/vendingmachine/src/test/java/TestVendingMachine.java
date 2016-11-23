/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.vendingmachine.dao.VendingDao;
import com.mycompany.vendingmachine.dto.Snack;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class TestVendingMachine {
    
    public TestVendingMachine() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void testUpdateInventory() {
        VendingDao vDao = new VendingDao();
        List<Snack> availableSnacks = vDao.listAllAvailableSnacks();
        
        Snack eSnack = new Snack();
        eSnack = availableSnacks.get(0);
        int expected = eSnack.getCount();
        expected--;
        Snack rSnack = vDao.updateInventory(eSnack);
        int result = rSnack.getCount();
        
        Assert.assertEquals(expected, result);
        
    }
    
    @Test
    public void testListAvailableSnacks() {
        VendingDao vDao = new VendingDao();
        List<Snack> availableSnacks = vDao.listAllAvailableSnacks();
        List<Snack> allSnacks = vDao.getAllSnacks();
        
        Assert.assertFalse(availableSnacks.equals(allSnacks));
    }

}
