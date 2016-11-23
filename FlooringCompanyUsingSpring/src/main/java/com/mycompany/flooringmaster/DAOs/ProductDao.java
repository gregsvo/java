/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.DAOs;

import com.mycompany.flooringmaster.DTOs.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public final class ProductDao {

    final static String FILENAME = "products.txt";
    final static String DELIMETER = ",";

    private List<Product> productList = new ArrayList<>();
    private boolean testMode;
    EncodeDecode ed = new EncodeDecode();

    public ProductDao(boolean testMode) {
        setup(testMode);
    }

    public void setup(boolean testMode) {
        this.testMode = testMode;
        productList = ed.decode();
    }

    public String[] getProducts() {
        String[] products = new String[productList.size()];
        int i = 0;
        for (Product x : productList) {
            products[i] = x.getProductType();
            i++;
        }
        return products;
    }

    public String[] getProductsCosts() {
        String[] products = new String[productList.size()];
        int i = 0;
        for (Product x : productList) {
            products[i] = x.getProductType() + " Material: " + x.getCostSqFt() + " Labor: " + x.getLaborCostSqFt();
            i++;
        }
        return products;
    }

    public Product getProduct(String productName) {
        for (Product x : productList) {
            if (x.getProductType().equalsIgnoreCase(productName)) {
                return x;
            }
        }
        return (new Product("null", -1, -1));
    }

    public void createProduct(String newProductName, double newMaterialCostSqFt, double newLaborCostSqFt) {
        productList.add(new Product(newProductName, newMaterialCostSqFt, newLaborCostSqFt));
        ed.encode();
    }

    public void renameProduct(String oldProductName, String newProductName) {
        for (Product thisProduct : productList) {
            if (thisProduct.getProductType().equals(oldProductName)) {
                thisProduct.setProductType(newProductName);
                break;
            }
        }
        ed.encode();
    }

    public void changeMaterialCost(String productName, double newMaterialCost) {
        for (Product thisProduct : productList) {
            if (thisProduct.getProductType().equals(productName)) {
                thisProduct.setCostSqFt(newMaterialCost);
                break;
            }
        }
        ed.encode();
    }

    public void changeLaborCost(String productName, double newLaborCost) {
        for (Product thisProduct : productList) {
            if (thisProduct.getProductType().equals(productName)) {
                thisProduct.setLaborCostSqFt(newLaborCost);
                break;
            }
        }
        ed.encode();
    }

    public void removeProduct(String productName) {
        for (Product thisProduct : productList) {
            if (thisProduct.getProductType().equals(productName)) {
                productList.remove(thisProduct);
                break;
            }
        }
        ed.encode();
    }
    
    private class EncodeDecode implements FileIO {

        @Override
        public List<Product> decode() {
            List<Product> productList = new ArrayList<>();
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
                //implemented to skip over the header line in text file
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String currentLine = sc.nextLine();
                    String[] values = currentLine.split(DELIMETER);
                    productList.add(new Product(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2])));
                }
                Collections.sort(productList, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getProductType().compareToIgnoreCase(o2.getProductType());
                    }
                });
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return productList;
        }

        @Override
        public void encode() {
            if (!testMode) {
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(FILENAME));
                    pw.println("ProductType, CostPerSquareFoot, LaborCostPerSquareFoot");
                    for (Product currentProduct : productList) {
                        String line
                                = currentProduct.getProductType() + DELIMETER
                                + currentProduct.getCostSqFt() + DELIMETER
                                + currentProduct.getLaborCostSqFt();

                        pw.println(line);
                        pw.flush();

                    }
                    pw.close();
                } catch (IOException ex) {
                    Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
