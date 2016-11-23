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
public class Order {

    private Integer order_id;//CHECK
    @NotEmpty(message = "You must supply a value for date.")
    private String Date;
    @NotEmpty(message = "You must supply a value for customer name.")
    private String customer_name;
    @NotEmpty(message = "You must supply a state of purchase.")
    private State state;
    @NotEmpty(message = "You must supply a material.")
    private Material material;
    @NotEmpty(message = "You must supply an area.")
    private double area;
    private double total_material_cost;
    private double total_labor_cost;
    private double total_tax_cost;
    private double grand_total;//CHECK

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getTotal_material_cost() {
        return total_material_cost;
    }

    public void setTotal_material_cost(double total_material_cost) {
        this.total_material_cost = total_material_cost;
    }

    public double getTotal_labor_cost() {
        return total_labor_cost;
    }

    public void setTotal_labor_cost(double total_labor_cost) {
        this.total_labor_cost = total_labor_cost;
    }

    public double getTotal_tax_cost() {
        return total_tax_cost;
    }

    public void setTotal_tax_cost(double total_tax_cost) {
        this.total_tax_cost = total_tax_cost;
    }

    public double getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(double grand_total) {
        this.grand_total = grand_total;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
}
