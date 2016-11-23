/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class StateDaoDbImpl implements StateDao {

    private static final String SQL_GET_STATE = "SELECT * FROM state WHERE state_id = ?;";
    private static final String SQL_INSERT_STATE = "INSERT INTO `state` (`state_name`, `tax_rate`) VALUES (?, ?);";
    private static final String SQL_REMOVE_STATE = "DELETE FROM state WHERE state_id = ?;";
    private static final String SQL_UPDATE_STATE = "UPDATE state SET title = ?, tax_rate = ? WHERE state_id = ?";
    private static final String SQL_LIST_STATES = "SELECT * FROM state";
    private static final String SQL_SEARCH_STATES = "SELECT * FROM state WHERE `state_name` LIKE CONCAT('%',?,'%')";

    JdbcTemplate template;

    public StateDaoDbImpl(JdbcTemplate template) {

        this.template = template;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public State add(State state) {
        template.update(SQL_INSERT_STATE,
                state.getState_name(),
                state.getTax_rate());


        Integer newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        state.setState_id(newId);
        

        return state;
    }


    @Override
    public State get(Integer id) {

        State state = template.queryForObject(SQL_GET_STATE, new StateMapper(), id);

        return state;

    }
    

    @Override
    public List<State> list() {

        List<State> stateList = template.query(SQL_LIST_STATES, new StateMapper());

        return stateList;

    }

    @Override
    public void remove(Integer id) {

        template.update(SQL_REMOVE_STATE, id);

    }

    @Override
    public void update(State state) {

        template.update(SQL_UPDATE_STATE,
                state.getState_id(),
                state.getState_name(),
                state.getTax_rate());

    }

    @Override
    public List<State> searchAll(String search) {
        List<State> stateList = template.query(SQL_SEARCH_STATES, new Object[]{search}, new StateMapper());
    return stateList;
    }

 private static class StateMapper implements RowMapper<State> {

        public StateMapper() {
        }

        @Override
        public State mapRow(ResultSet rs, int i) throws SQLException {

            State state = new State();

            state.setState_id(rs.getInt("state_id"));
            state.setState_name(rs.getString("state_name"));
            state.setTax_rate(rs.getDouble("tax_rate"));
           
            return state;

        }
    }
}
