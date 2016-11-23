/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.baseballleague.dto;

import java.util.List;

/**
 *
 * @author apprentice
 */
public class Team {
    
    private Integer id;
    private String name;
    private List<Player> roster;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the roster
     */
    public List<Player> getRoster() {
        return roster;
    }

    /**
     * @param roster the roster to set
     */
    public void setRoster(List<Player> roster) {
        this.roster = roster;
    }
    
    
    
    
}
