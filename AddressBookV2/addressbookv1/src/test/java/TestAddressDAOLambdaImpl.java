
import Controller.Controller;
import DAO.AddressDAOImpl;
import DTO.Address;
import java.util.ArrayList;
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
public class TestAddressDAOLambdaImpl {
    
    public TestAddressDAOLambdaImpl() {
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
    public void testAddAddress() {
        AddressDAOImpl dao = new AddressDAOImpl();
        Address aAddress = new Address();
        Integer expected = dao.getNextId() + 2;
        
        aAddress.setId(1834);
        aAddress.setFirstName("first");
        aAddress.setLastName("test11554");
        aAddress.setStreet("street");
        aAddress.setState("state");
        aAddress.setCity("city");
        aAddress.setZip("111834");
        
        Address bAddress = dao.addAddress(aAddress);
        Integer result = bAddress.getId();
        
        Assert.assertEquals(expected, result);
        
        
    }
        @Test
    public void testSearch() {
        Controller ctrl = new Controller();
        Address aAddress = new Address();
        aAddress.setId(1834);
        aAddress.setFirstName("first");
        aAddress.setLastName("test11554");
        aAddress.setStreet("street");
        aAddress.setState("state");
        aAddress.setCity("city");
        aAddress.setZip("111834");
        
        AddressDAOImpl dao = new AddressDAOImpl();
        Address bAddress = dao.addAddress(aAddress);
        String expected = bAddress.getFirstName();
        Address target = ctrl.searchAddress("test11554");
        String result = target.getFirstName();
        
        Assert.assertEquals(expected, result);
        
    }
    
    @Test
    public void testDelete() {
        AddressDAOImpl dao = new AddressDAOImpl();
        List<Address> allAds = dao.list();
        Integer id = 0;
        for (Address a : allAds) {
            if (a.getLastName().equals("test11554")) {
                id = a.getId();
                break;
            }
        }
        dao.deleteAddress(id);
        boolean expected = true;
        boolean result = true;
        List<Address>allAdsUpdated = dao.list();
        for (Address b : allAdsUpdated) {
            if (b.getId().equals(id)) {
                result = false;
            }
        }
            
        Assert.assertEquals(expected, result);
        
    }
    
    @Test
    public void testList() {
        AddressDAOImpl dao = new AddressDAOImpl();
        List<Address> blank = new ArrayList<>();
        List<Address> allAddresses = dao.list();
        
        Assert.assertFalse(blank.equals(allAddresses));
        
        
        
    }
    
  
    

    
    
}