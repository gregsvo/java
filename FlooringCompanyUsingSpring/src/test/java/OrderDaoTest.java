/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.flooringmaster.DAOs.ConfigDao;
import com.mycompany.flooringmaster.DAOs.OrderDao;
import com.mycompany.flooringmaster.DAOs.ProductDao;
import com.mycompany.flooringmaster.DAOs.TaxesDao;
import com.mycompany.flooringmaster.DTOs.Order;
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
public class OrderDaoTest {

    private OrderDao dao;
    private ConfigDao configDao;
    private ProductDao productDao;
    private TaxesDao taxesDao;
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private boolean initialMode;
    private final String FILE = "Orders_01011999.txt";

    public OrderDaoTest() {
        this.configDao = ctx.getBean("configDao", ConfigDao.class);
        this.dao = ctx.getBean("orderDao", OrderDao.class);
        this.productDao = ctx.getBean("productDao", ProductDao.class);
        this.taxesDao = ctx.getBean("taxesDao", TaxesDao.class);
    }

    @Before
    public void setUp() {

        initialMode = configDao.getTestMode();
        configDao.setTestMode(true);
        dao = new OrderDao(true);
        dao.createOrder(new Order(1, "Test One", "OH", 5.75, "Wood", 10, 5.15, 4.75, 51.5, 47.5, 5.69, 104.69, FILE));
        dao.createOrder(new Order(2, "Test Two", "OH", 5.75, "Wood", 10, 5.15, 4.75, 51.5, 47.5, 5.69, 104.69, FILE));

    }

    @After
    public void tearDown() {
        configDao.setTestMode(initialMode);
    }

    @Test
    public void gfolTest() {
        int result = dao.getFileOrderList(FILE).size();

        Assert.assertEquals(2, result);
    }

    @Test
    public void coTest() {
        dao.createOrder(new Order(3, "Test Three", "OH", 5.75, "Wood", 10, 5.15, 4.75, 51.5, 47.5, 5.69, 104.69, FILE));

        int result = dao.getFileOrderList(FILE).size();
        Assert.assertEquals(3, result);
    }

    @Test
    public void gniTest() {
        int result = dao.getNextId(FILE);

        Assert.assertEquals(3, result);
    }

    @Test
    public void doTest() {
        dao.deleteOrder(dao.getOrder(1, FILE));

        int result = dao.getFileOrderList(FILE).size();

        Assert.assertEquals(1, result);
    }

    @Test
    public void uoTest() {
        dao.getOrder(1, FILE).setArea(1);
        dao.updateOrder(dao.getOrder(1, FILE), productDao.getProduct(dao.getOrder(1, FILE).getProductType()).getLaborCostSqFt(), productDao.getProduct(dao.getOrder(1, FILE).getProductType()).getCostSqFt(), taxesDao.getTax(dao.getOrder(1, FILE).getState()));

        Assert.assertEquals(10.47, dao.getOrder(1, FILE).getTotalCost(), 1e-8);
    }

    @Test
    public void goTest() {
        Assert.assertEquals("Test One", dao.getOrder(1, FILE).getName());
    }

    @Test
    public void gfnTest() {
        Assert.assertEquals(FILE, dao.getOrder(1, FILE).getFileName());
    }

    @Test
    public void gdTest() {
        Assert.assertEquals("01/01/1999", dao.getDate(FILE));
    }

    @Test
    public void gsoTest() {
        Assert.assertEquals(2, dao.getStringOrders(FILE).length);
    }
}
