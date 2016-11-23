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
public class Material {
    
    private Integer material_id;
    @NotEmpty(message = "You must supply a material name.")
    private String material_name;
    @NotEmpty(message = "You must enter the material cost per square foot.")
    private double material_cpsf;
    @NotEmpty(message = "You must enter the labor cost per square foot.")
    private double labor_cpsf;
    

    public Integer getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Integer material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public double getMaterial_cpsf() {
        return material_cpsf;
    }

    public void setMaterial_cpsf(double material_cpsf) {
        this.material_cpsf = material_cpsf;
    }

    public double getLabor_cpsf() {
        return labor_cpsf;
    }

    public void setLabor_cpsf(double labor_cpsf) {
        this.labor_cpsf = labor_cpsf;
    }

}
