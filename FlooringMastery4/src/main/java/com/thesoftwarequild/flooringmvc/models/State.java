/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.models;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author apprentice
 */
public class State {
    private Integer state_id;
    @NotEmpty(message = "You must enter a state name.")
    private String state_name;
    @NotEmpty(message = "You must enter a tax rate.")
    private double tax_rate;
    


    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public double getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(double tax_rate) {
        this.tax_rate = tax_rate;
    }
    
    
    public int getState_id(){
        return state_id;
    } 
    
    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }
}    
