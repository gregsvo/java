package DAO;

import DTO.Address;
import java.util.List;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public interface AddressDAO {

    Address addAddress(Address address);


    Set<Integer> deleteAddress(Integer id);

    /**
     * @return the nextId
     */
    Integer getNextId();

    List<Address> list();
    
    List<Address> searchByName(String lastName);
    
    List<Address> searchByCity(String city);
    
    List<Address> searchByState(String state);
    
    /**
     *
     * @param zip
     * @return
     */
    List<Address> searchByZip(String zip);
    
    
    
}
