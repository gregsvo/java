package DAO;

import DTO.Address;
import java.util.Comparator;

/**
 *
 * @author apprentice
 */
public class CustomComparator implements Comparator<Address> {

    @Override
    public int compare(Address a, Address b) {
        return a.getCity().compareTo(b.getCity());
    }
    
}


