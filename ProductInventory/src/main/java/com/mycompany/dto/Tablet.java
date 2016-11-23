/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

/**
 *
 * @author apprentice
 */
public class Tablet extends Product {
//    private Double price;
//    private String productName;
//    private Integer maxStock;
//    private Integer endangeredStockNumber;

    private String operatingSystem;
    private boolean hasStylus;
    private float screenSize;

    public Tablet(String... args) {
        this.productName = args[0]; // = currentComputer.getProductName();
        this.price = Double.parseDouble(args[1]); // = String.valueOf(currentComputer.getPrice());
        this.maxStock = Integer.parseInt(args[2]); // = String.valueOf(currentComputer.getMaxStock());
        this.endangeredStockNumber = Integer.parseInt(args[3]); // = String.valueOf(currentComputer.getEndangeredStockNumber());
        this.currentStock = Integer.parseInt(args[4]);

        this.operatingSystem = args[5]; // = String.valueOf(currentTablet.getOperatingSystem());
        this.hasStylus = Boolean.parseBoolean(args[6]); // = Boolean.valueOf(currentTablet.isHasStylus());
        this.screenSize = Float.parseFloat(args[7]); // = currentTablet.getScreenSize();

        /**
         * @return the price
         */
    }

    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the maxStock
     */
    public Integer getMaxStock() {
        return maxStock;
    }

    /**
     * @param maxStock the maxStock to set
     */
    public void setMaxStock(Integer maxStock) {
        this.maxStock = maxStock;
    }

    /**
     * @return the endangeredStockNumber
     */
    public Integer getEndangeredStockNumber() {
        return endangeredStockNumber;
    }

    /**
     * @param endangeredStockNumber the endangeredStockNumber to set
     */
    public void setEndangeredStockNumber(Integer endangeredStockNumber) {
        this.endangeredStockNumber = endangeredStockNumber;
    }

    /**
     * @return the operatingSystem
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * @param operatingSystem the operatingSystem to set
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * @return the hasStylus
     */
    public boolean isHasStylus() {
        return hasStylus;
    }

    /**
     * @param hasStylus the hasStylus to set
     */
    public void setHasStylus(boolean hasStylus) {
        this.hasStylus = hasStylus;
    }

    /**
     * @return the screenSize
     */
    public float getScreenSize() {
        return screenSize;
    }

    /**
     * @param screenSize the screenSize to set
     */
    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    /**
     * @return the currentStock
     */
    public int getCurrentStock() {
        return currentStock;
    }

    /**
     * @param currentStock the currentStock to set
     */
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

}
