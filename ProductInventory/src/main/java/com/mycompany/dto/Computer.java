package com.mycompany.dto;

/**
 *
 * @author apprentice
 */
public class Computer extends Product {
//    private Double price;
//    private String productName;
//    private Integer maxStock;
//    private Integer endangeredStockNumber;
    //protected Integer currentStock

    private Integer numProcessors;
    private boolean isGaming;
    private boolean isLiquidCooled;

    public Computer(String... args) {
        this.productName = args[0]; // = currentComputer.getProductName();
        this.price = Double.parseDouble(args[1]); // = String.valueOf(currentComputer.getPrice());
        this.maxStock = Integer.parseInt(args[2]); // = String.valueOf(currentComputer.getMaxStock());
        this.endangeredStockNumber = Integer.parseInt(args[3]); // = String.valueOf(currentComputer.getEndangeredStockNumber());
        this.currentStock = Integer.parseInt(args[4]);

        this.numProcessors = Integer.parseInt(args[5]); // = String.valueOf(currentComputer.getScreenSize());
        this.isGaming = Boolean.parseBoolean(args[6]); // = String.valueOf(currentComputer.isIsGaming());
        this.isLiquidCooled = Boolean.parseBoolean(args[7]); // = currentComputer.getCarrier();
    }

    /**
     * @return the price
     */
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
     * @return the numProcessors
     */
    public Integer getNumProcessors() {
        return numProcessors;
    }

    /**
     * @param numProcessors the numProcessors to set
     */
    public void setNumProcessors(Integer numProcessors) {
        this.numProcessors = numProcessors;
    }

    /**
     * @return the isGaming
     */
    public boolean isIsGaming() {
        return isGaming;
    }

    /**
     * @param isGaming the isGaming to set
     */
    public void setIsGaming(boolean isGaming) {
        this.isGaming = isGaming;
    }

    /**
     * @return the isLiquidCooled
     */
    public boolean isIsLiquidCooled() {
        return isLiquidCooled;
    }

    /**
     * @param isLiquidCooled the isLiquidCooled to set
     */
    public void setIsLiquidCooled(boolean isLiquidCooled) {
        this.isLiquidCooled = isLiquidCooled;
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
