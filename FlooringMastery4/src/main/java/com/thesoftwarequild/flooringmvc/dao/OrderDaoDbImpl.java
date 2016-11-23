package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.Order;
import com.thesoftwarequild.flooringmvc.models.Material;
import com.thesoftwarequild.flooringmvc.models.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class OrderDaoDbImpl implements OrderDao {

    private static final String SQL_GET_ORDER = "SELECT * FROM orders WHERE id = ?;";
    private static final String SQL_INSERT_ORDER = "INSERT INTO `orders` (`customer_name`, `date`, `area`, `material_id`, `state_id`, `total_labor_cost`,`total_material_cost`,`total_tax_cost`,`grand_total`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_REMOVE_ORDER = "DELETE FROM orders WHERE id = ?;";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET customer_name = ?, date = ?, area = ?, material_id = ?, state_id = ?, total_labor_cost = ?, total_material_cost = ?, total_tax_cost = ?, grand_total = ? WHERE id = ?";
    private static final String SQL_LIST_ORDER = "SELECT * from orders;";
    private static final String SQL_SEARCH_ORDERS = "SELECT * FROM orders WHERE `customer_name` LIKE CONCAT('%',?,'%') OR `order_id` = ?";

    private JdbcTemplate template;
    private State state;
    private Material material;
    private MaterialDao materialDao;
    private StateDao stateDao;
    
    @Inject
    public OrderDaoDbImpl(JdbcTemplate template, MaterialDao materialDao, StateDao stateDao) {
        
        this.materialDao = materialDao;
        this.stateDao = stateDao;
        
        this.template = template;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Order add(Order order) {
        template.update(SQL_INSERT_ORDER,
                order.getCustomer_name(),
                order.getDate(),
                order.getArea(),
                order.getMaterial().getMaterial_id(),
                order.getState().getState_id(),
                order.getTotal_labor_cost(),
                order.getTotal_material_cost(),
                order.getTotal_tax_cost(),
                order.getGrand_total());

        Integer newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        order.setOrder_id(newId);

        return order;
    }

    @Override
    public Order get(Integer id) {

        Order order = template.queryForObject(SQL_GET_ORDER, new OrderMapper(), id);

        return order;

    }

    @Override
    public List<Order> list() {

        List<Order> orderList = template.query(SQL_LIST_ORDER, new OrderMapper());

        return orderList;

    }

    @Override
    public void remove(Integer id) {

        template.update(SQL_REMOVE_ORDER, id);

    }

    @Override
    public void update(Order order) {

        template.update(SQL_UPDATE_ORDER,
                order.getOrder_id(),
                order.getCustomer_name(),
                order.getDate(),
                order.getArea(),
                order.getMaterial().getMaterial_id(),
                order.getState().getState_id(),
                order.getTotal_labor_cost(),
                order.getTotal_material_cost(),
                order.getTotal_tax_cost(),
                order.getGrand_total(), order.getOrder_id());

    }

    @Override
    public List<Order> searchAll(String search) {
        List<Order> orderList = template.query(SQL_SEARCH_ORDERS, new Object[]{search, search, search, search, search}, new OrderMapper());
        return orderList;
    }

    private class OrderMapper implements RowMapper<Order> {

        public OrderMapper() {
        }

        @Override
        public Order mapRow(ResultSet rs, int i) throws SQLException {

            Order order = new Order();

            order.setOrder_id(rs.getInt("order_id"));
            order.setCustomer_name(rs.getString("customer_name"));
            order.setDate(rs.getString("date"));
            order.setArea(rs.getDouble("area"));
            order.setMaterial(materialDao.get(rs.getInt("material_id")));
            order.setState(stateDao.get(rs.getInt("state_id")));
            order.setTotal_labor_cost(rs.getDouble("total_labor_cost"));
            order.setTotal_material_cost(rs.getDouble("total_material_cost"));
            order.setTotal_tax_cost(rs.getDouble("total_tax_cost"));
            order.setGrand_total(rs.getDouble("grand_total"));
            return order;

        }
    }

}
