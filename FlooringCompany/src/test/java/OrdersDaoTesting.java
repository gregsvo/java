/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.flooringmasteryproject.dao.OrdersDao;
import com.mycompany.flooringmasteryproject.dto.OrdersDto;
import java.util.List;
import java.util.Map;
import java.util.Set;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrdersDaoTesting {
    
    OrdersDao orders;
    OrdersDto order;
    
    public OrdersDaoTesting() {
        
    }
    
    @Before
    public void setUp() {
        orders = new OrdersDao();
        order = new OrdersDto();
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
    public void createTest(){
        //arrange
        boolean nameWorked = false;

        //act
        order.setName("Matt");
        orders.create(order);
        List<OrdersDto> list = orders.list();
        
        for (OrdersDto o : list ) {
            if(o.getName() == "Matt" && o.getOrderNumber()!=0){
                nameWorked = true;
                break;
            }
        }
        
        //assert
        Assert.assertTrue(nameWorked);
        
//        Assert.assertEquals(result.getOrderNumber(), new Integer(13));
//        Assert.assertEquals(result.getName(), "Matt");
    }
    
    @Test
    public void updateTest(){
        OrdersDao target = new OrdersDao();
        
        OrdersDto expected = new OrdersDto();
        
        expected.setName("Helen");
        target.create(order);
        //Change name to 'World'
        expected.setName("World");
        //Call update on Dao
        target.update(order);
        
        OrdersDto actual = target.getOrderById(expected.getOrderNumber());
        assertEquals(expected.getName(), actual.getName());

    }

        
    
    
}
