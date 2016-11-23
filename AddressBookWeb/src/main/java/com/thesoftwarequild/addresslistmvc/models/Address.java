/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.addresslistmvc.models;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Address {

    private Integer id;

    @NotEmpty(message = "Please enter a first name.")
    private String firstName;
    @NotEmpty(message = "Please enter a last name.")
    private String lastName;

    @NotEmpty(message = "Please enter a street name & number.")
    private String address;
    @NotEmpty(message = "Please enter a city.")
    private String city;
    @Length(min = 2, max = 2, message = "Please enter a two-letter state.")
    private String state;
    @Length(min = 5, max = 5, message = "Please enter a valid 5-dig zip code.")
    private String zip;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
