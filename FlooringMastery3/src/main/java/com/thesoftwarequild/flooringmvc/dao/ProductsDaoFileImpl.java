/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.dao;

import com.thesoftwarequild.flooringmvc.models.Products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ProductsDaoFileImpl implements ProductsDao {
    private final String FILENAME = "productsFile.csv";
    private final String DELIMETER = ",";
    private Products products = new Products();
    private Map<String, Products> productsMap = new HashMap<>();

    public ProductsDaoFileImpl() {
        List<Products> productsList = decode();

        for (Products product : productsList) {
            productsMap.put(product.getProductType(), product);
        }
    }

    @Override
    public double calculateSubtotal(String type, double area) {
        return (calculateLaborCost(type, area) + calculateMaterialCost(type, area));

    }

    @Override
    public double calculateLaborCost(String type, double area) {
        return (retrieveLaborCostPerSquareFoot(type) * area);

    }

    @Override
    public double calculateMaterialCost(String type, double area) {
        return (retrieveCostPerSquareFoot(type) * area);

    }
    
    @Override
    public double retrieveCostPerSquareFoot(String type) {
        return productsMap.get(type).getCostPerSquareFoot();
    }
    
    @Override
    public double retrieveLaborCostPerSquareFoot(String type) {
        return productsMap.get(type).getLaborCostPerSquareFoot();       
    }

    private List<Products> decode() {
        List<Products> products = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            
            String currentLine = sc.nextLine();
            while (sc.hasNextLine()) {

                String currentLine2 = sc.nextLine();
                String[] values = currentLine2.split(DELIMETER);

                Products product = new Products();

                product.setProductType(values[0]);
                product.setCostPerSquareFoot(Double.parseDouble(values[1]));
                product.setLaborCostPerSquareFoot(Double.parseDouble(values[2]));

                products.add(product);
            }
        } catch (FileNotFoundException ex) {

        }

        return products;

    }
}
