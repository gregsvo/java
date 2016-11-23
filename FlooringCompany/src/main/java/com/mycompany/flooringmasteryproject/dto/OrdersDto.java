package com.mycompany.flooringmasteryproject.dto;


public class OrdersDto {
    
    private Integer orderNumber;
    private String name;
    private String customerState;
    private String productType;
    private Double area;
    private Double materialsCostPerSqFoot;
    private Double LaborCostPerSqFoot;
    private Double materialsCost;
    private Double laborCost;
    private Double totalTax;
    private Double grandTotal;
    private Double taxRate;

    /**
     * @return the orderNumber
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }



    /**
     * @return the customerState
     */
    public String getCustomerState() {
        return customerState;
    }

    /**
     * @param customerState the customerState to set
     */
    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the area
     */
    public Double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Double area) {
        this.area = area;
    }

    /**
     * @return the materialsCostPerSqFoot
     */
    public Double getMaterialsCostPerSqFoot() {
        return materialsCostPerSqFoot;
    }

    /**
     * @param materialsCostPerSqFoot the materialsCostPerSqFoot to set
     */
    public void setMaterialsCostPerSqFoot(Double materialsCostPerSqFoot) {
        this.materialsCostPerSqFoot = materialsCostPerSqFoot;
    }

    /**
     * @return the LaborCostPerSqFoot
     */
    public Double getLaborCostPerSqFoot() {
        return LaborCostPerSqFoot;
    }

    /**
     * @param LaborCostPerSqFoot the LaborCostPerSqFoot to set
     */
    public void setLaborCostPerSqFoot(Double LaborCostPerSqFoot) {
        this.LaborCostPerSqFoot = LaborCostPerSqFoot;
    }

    /**
     * @return the materialsCost
     */
    public Double getMaterialsCost() {
        return materialsCost;
    }

    /**
     * @param materialsCost the materialsCost to set
     */
    public void setMaterialsCost(Double materialsCost) {
        this.materialsCost = materialsCost;
    }

    /**
     * @return the laborCost
     */
    public Double getLaborCost() {
        return laborCost;
    }

    /**
     * @param laborCost the laborCost to set
     */
    public void setLaborCost(Double laborCost) {
        this.laborCost = laborCost;
    }

    /**
     * @return the totalTax
     */
    public Double getTotalTax() {
        return totalTax;
    }

    /**
     * @param totalTax the totalTax to set
     */
    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * @return the grandTotal
     */
    public Double getGrandTotal() {
        return grandTotal;
    }

    /**
     * @param grandTotal the grandTotal to set
     */
    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the taxRate
     */
    public Double getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
    
    
    
    
}
