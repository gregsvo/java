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
public class Phone extends Product {

    
    private String carrier;
    private float screenSize;
    private boolean is4G;

    public Phone(String... args) {
        this.productName = args[0]; // = currentPhone.getProductName();
        this.price = Double.parseDouble(args[1]); // = String.valueOf(currentPhone.getPrice());
        this.maxStock = Integer.parseInt(args[2]); // = String.valueOf(currentPhone.getMaxStock());
        this.endangeredStockNumber = Integer.parseInt(args[3]); // = String.valueOf(currentPhone.getEndangeredStockNumber());
        this.currentStock = Integer.parseInt(args[4]);
        
        this.screenSize = Float.parseFloat(args[5]); // = String.valueOf(currentPhone.getScreenSize());
        this.is4G = Boolean.parseBoolean(args[6]); // = String.valueOf(currentPhone.isIs4G());
        this.carrier = args[7]; // = currentPhone.getCarrier();
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return this.productName;
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
    public int getMaxStock() {
        return maxStock;
    }

    /**
     * @param maxStock the maxStock to set
     */
    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    /**
     * @return the endangeredStockNumber
     */
    public int getEndangeredStockNumber() {
        return endangeredStockNumber;
    }

    /**
     * @param endangeredStockNumber the endangeredStockNumber to set
     */
    public void setEndangeredStockNumber(int endangeredStockNumber) {
        this.endangeredStockNumber = endangeredStockNumber;
    }

    /**
     * @return the carrier
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * @param carrier the carrier to set
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
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
     * @return the is4G
     */
    public boolean isIs4G() {
        return is4G;
    }

    /**
     * @param is4G the is4G to set
     */
    public void setIs4G(boolean is4G) {
        this.is4G = is4G;
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
