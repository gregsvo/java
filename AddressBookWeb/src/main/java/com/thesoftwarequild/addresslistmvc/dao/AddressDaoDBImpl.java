package com.thesoftwarequild.addresslistmvc.dao;

import com.thesoftwarequild.addresslistmvc.models.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AddressDaoDBImpl implements AddressDao {

    private static final String SQL_GET_ADDRESS = "SELECT * FROM addresses WHERE id = ?;";
    private static final String SQL_INSERT_ADDRESS = "INSERT INTO `addresses` (`first_name`, `last_name`, `street_address`, `city`, `state`, `zip`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_REMOVE_ADDRESS = "DELETE FROM addresses WHERE id = ?";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE addresses SET `first_name`=?, `last_name`=?, `street_address`=?, `city`=?, `state`=? WHERE `id`=?;";
    private static final String SQL_LIST_ADDRESSES = "SELECT * FROM addresses;";
    private static final String SQL_SEARCH_ADDRESSES = "SELECT * FROM addresses WHERE `first_name` LIKE CONCAT('%',?,'%') OR `last_name` LIKE CONCAT('%',?,'%') OR `street_address` LIKE CONCAT('%',?,'%') OR `city` LIKE CONCAT('%',?,'%') OR `state` LIKE CONCAT('%',?,'%') OR `zip` LIKE CONCAT('%',?,'%')";
    //private static final String SQL_SEARCH_ADDRESSES = "SELECT * FROM addresses WHERE first_name LIKE %" + "?" + "%";
    //OR last_name LIKE %?% OR street_address LIKE %?% OR city LIKE %?% OR state=? OR zip=?

    private JdbcTemplate template;

    public AddressDaoDBImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address add(Address address) {

        template.update(SQL_INSERT_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getAddress(),
                address.getCity(),
                address.getState(),
                address.getZip());

        Integer newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        address.setId(newId);
        
        
        return address;
    }

    @Override
    public Address get(Integer id) {
        Address address = template.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), id);

        return address;

    }

    @Override
    public void remove(Integer id) {

        template.update(SQL_REMOVE_ADDRESS, id);

    }

    @Override
    public void update(Address address) {
        template.update(SQL_UPDATE_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getAddress(),
                address.getCity(),
                address.getState(),
                address.getZip());
    }

    @Override
    public List<Address> list() {
        List<Address> addressList = template.query(SQL_LIST_ADDRESSES, new AddressMapper());

        return addressList;
    }

    @Override
    public List<Address> searchAll(String search) {
        List<Address> addressList = template.query(SQL_SEARCH_ADDRESSES, new Object[]{search, search, search, search, search, search}, new AddressMapper());

        return addressList;

    }
    

    private static class AddressMapper implements RowMapper<Address> {

        public AddressMapper() {
        }//default constructor for innner class

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {

            Address addressNew = new Address();

            addressNew.setId(rs.getInt("id"));
            addressNew.setFirstName(rs.getString("first_name"));
            addressNew.setLastName(rs.getString("last_name"));
            addressNew.setAddress(rs.getString("street_address"));
            addressNew.setCity(rs.getString("city"));
            addressNew.setState(rs.getString("state"));
            addressNew.setZip(rs.getString("zip"));

            return addressNew;

        }
    }

}
