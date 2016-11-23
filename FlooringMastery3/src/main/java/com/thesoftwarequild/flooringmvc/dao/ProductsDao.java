/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.dao;

/**
 *
 * @author Ollie
 */
public interface ProductsDao {

    double calculateLaborCost(String type, double area);

    double calculateMaterialCost(String type, double area);

    double calculateSubtotal(String type, double area);

    double retrieveCostPerSquareFoot(String type);

    double retrieveLaborCostPerSquareFoot(String type);
    
}
