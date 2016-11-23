package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.State;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface StateDao {

    State add(State state);
    State get(Integer id);
    List<State> list();
    void remove(Integer id);
    void update(State state);
   public List<State> searchAll(String search);
    
}
