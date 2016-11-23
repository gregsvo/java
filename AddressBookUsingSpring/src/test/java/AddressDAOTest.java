/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.addressbook.DAO.AddressBookDaoPats;
import com.mycompany.addressbook.DTO.Address;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class AddressDAOTest {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    AddressBookDaoPats dao;
    Address a1,a2,a3;
    public AddressDAOTest() {
    }

    @Before
    public void setUp() {
        dao = ctx.getBean("addressBookDaoPats", AddressBookDaoPats.class);
        a1 = new Address();
        a1.setFirstName("Kyle");
        a1.setLastName("Sickels");
        a1.setStreetName("Bad Wolf Ln");
        a1.setStreetNumber("123");
        a1.setCity("Kanto");
        a1.setState("Ohio");
        a1.setZip("1337");
        
        a2 = new Address();
        a2.setFirstName("Heidi");
        a2.setLastName("Sickels");
        a2.setStreetName("Something St.");
        a2.setStreetNumber("5678");
        a2.setCity("Hoenn");
        a2.setState("Ohio");
        a2.setZip("1337");
        
        a3 = new Address();
        a3.setFirstName("Pat");
        a3.setLastName("Toner");
        a3.setStreetName("Dead End Dr.");
        a3.setStreetNumber("789");
        a3.setCity("MiddleOfNoWhere");
        a3.setState("Michigan");
        a3.setZip("00100");
    }

    @After
    public void tearDown() {
    }

    //GET_KEYS[]
    @Test
    public void testGetkeys3() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        String[] testkeys = dao.getKeys();

        boolean result = true;
        int i = 0;
        for (Address index : dao.list()) {
            if (index.getId() != Integer.parseInt(testkeys[i])) {
                result = false;
            }
            i++;
        }

        Assert.assertTrue(result);
    }

    @Test
    public void testGetkeys1() {
        dao.create(a3);
        String[] testkeys = dao.getKeys();

        boolean result = true;
        int i = 0;
        for (Address index : dao.list()) {
            if (index.getId() != Integer.parseInt(testkeys[i])) {
                result = false;
                break;
            }
            i++;
        }

        Assert.assertTrue(result);
    }

    //GET_ADDRESS_BY_LASTNAME
    @Test
    public void searchByLastName2() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        List<Address> result = dao.searchByLastName("Sickels");

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void searchByLastName1() {
        dao.create(a1);

        List<Address> result = dao.searchByLastName("Sickels");

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void searchByLastName0() {

        List<Address> result = dao.searchByLastName("Sickels");

        Assert.assertEquals(0, result.size());
    }

    //UPDATE_ADDRESS
    @Test
    public void update2() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        Address revised = dao.get(1);
        revised.setFirstName("Random");
        dao.update(revised);

        boolean result;
        if (dao.get(1).getFirstName().equals("Random")) {
            result = true;
        } else {
            result = false;
        }

        Assert.assertTrue(result);
    }


    @Test
    public void update0() {
        dao.update(null);

        boolean result;
        if (dao.list().size() == 0) {
            result = true;
        } else {
            result = false;
        }

        Assert.assertTrue(result);
    }

    //CREATE_ADDRESS
    @Test
    public void create3() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        int result = dao.list().size();

        Assert.assertEquals(3, result);
    }

    @Test
    public void create1() {
        dao.create(a1);

        int result = dao.list().size();

        Assert.assertEquals(1, result);
    }

    @Test
    public void create0() {
        int result = dao.list().size();

        Assert.assertEquals(0, result);
    }

    //DELETE_ADDRESS
    @Test
    public void delete3() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        dao.delete(0);
        dao.delete(1);
        dao.delete(2);

        int result = dao.list().size();

        Assert.assertEquals(0, result);
    }

    @Test
    public void delete1() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        dao.delete(0);

        int result = dao.list().size();

        Assert.assertEquals(2, result);
    }

    @Test
    public void delete0() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        //not an id of an index
        dao.delete(7);

        int result = dao.list().size();

        Assert.assertEquals(3, result);
    }

    //GET_LIST
    @Test
    public void list3() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        boolean result = false;

        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;

        for (Address index : dao.list()) {
            if (index.getId() == 0) {
                result1 = true;
            } else if (index.getId() == 1) {
                result2 = true;
            } else if (index.getId() == 2) {
                result3 = true;
            }
        }
        if (result1 && result2 && result3) {
            result = true;
        }

        Assert.assertTrue(result);
    }

    @Test
    public void list2() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        boolean result = false;

        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;

        for (Address index : dao.list()) {
            if (index.getId() == 0) {
                result1 = true;
            } else if (index.getId() == 1) {
                result2 = true;
            } else if (index.getId() == 5) {
                result3 = true;
            }
        }
        if (result1 && result2 && result3) {
            result = true;
        }

        Assert.assertFalse(result);
    }

    @Test
    public void list1() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        boolean result = false;

        boolean result1 = false;
        boolean result2 = false;
        boolean result3 = false;

        for (Address index : dao.list()) {
            if (index.getId() == 0) {
                result1 = true;
            }
        }
        if (result1 && result2 && result3) {
            result = true;
        }

        Assert.assertFalse(result);
    }

    @Test
    public void searchByCity0() {
        int result = dao.searchByCity("").size();
        Assert.assertEquals(0, result);
    }

    @Test
    public void searchByCity1() {

        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        int result = dao.searchByCity("Hoenn").size();
        Assert.assertEquals(1, result);
    }


    @Test
    public void searchByState0() {
        int result = dao.searchByState("").size();
        Assert.assertEquals(0, result);
    }

    @Test
    public void searchByState1() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        int result = dao.searchByState("Michigan").size();
        Assert.assertEquals(1, result);
    }

    @Test
    public void searchByState2() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        int result = dao.searchByState("Ohio").size();
        Assert.assertEquals(2, result);
    }

    @Test
    public void searchByZip0() {
        int result = dao.searchByZip("").size();
        Assert.assertEquals(0, result);
    }

    @Test
    public void searchByZip1() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        int result = dao.searchByZip("00100").size();
        Assert.assertEquals(1, result);
    }

    @Test
    public void searchByZip2() {
        dao.create(a1);
        dao.create(a2);
        dao.create(a3);

        int result = dao.searchByZip("1337").size();
        Assert.assertEquals(2, result);
    }

}
