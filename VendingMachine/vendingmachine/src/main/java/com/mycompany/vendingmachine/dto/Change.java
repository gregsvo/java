/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

/**
 *
 * @author apprentice
 */
public class Change {
    
    private int penny;
    private int nickel;
    private int dime; 
    private int quarter;

    
  
    
    /**
     * @return the penny
     */
    public int getPenny() {
        return penny;
    }

    /**
     * @return the nickel
     */
    public int getNickel() {
        return nickel;
    }

    /**
     * @return the dime
     */
    public int getDime() {
        return dime;
    }

    /**
     * @return the quarter
     */
    public int getQuarter() {
        return quarter;
    }

    /**
     * @param penny the penny to set
     */
    public void setPenny(int penny) {
        this.penny = penny;
    }

    /**
     * @param nickel the nickel to set
     */
    public void setNickel(int nickel) {
        this.nickel = nickel;
    }

    /**
     * @param dime the dime to set
     */
    public void setDime(int dime) {
        this.dime = dime;
    }

    /**
     * @param quarter the quarter to set
     */
    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }
    
    
    
}
