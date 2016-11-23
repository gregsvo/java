package com.mycompany.flooringmasteryproject.dto;


public class ProductsDto {
    private String productType;
    private Double materialCostPerSqFoot;
    private Double laborCostPerSqFoot;

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
     * @return the materialCostPerSqFoot
     */
    public Double getMaterialCostPerSqFoot() {
        return materialCostPerSqFoot;
    }

    /**
     * @param materialCostPerSqFoot the materialCostPerSqFoot to set
     */
    public void setMaterialCostPerSqFoot(Double materialCostPerSqFoot) {
        this.materialCostPerSqFoot = materialCostPerSqFoot;
    }

    /**
     * @return the laborCostPerSqFoot
     */
    public Double getLaborCostPerSqFoot() {
        return laborCostPerSqFoot;
    }

    /**
     * @param laborCostPerSqFoot the laborCostPerSqFoot to set
     */
    public void setLaborCostPerSqFoot(Double laborCostPerSqFoot) {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }

    
}
