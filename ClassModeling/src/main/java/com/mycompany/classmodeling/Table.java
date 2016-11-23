
package com.mycompany.classmodeling;


public class Table {
    private int height;
    private String manufacturer;
    private int chairCapacity;
    private String material;
    
    public Table (int height, String manufacturer, String material){
        this.height=height;
        this.manufacturer=manufacturer;
        this.material=material;
    
        
        
        
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the chairCapacity
     */
    public int getChairCapacity() {
        return chairCapacity;
    }

    /**
     * @param chairCapacity the chairCapacity to set
     */
    public void setChairCapacity(int chairCapacity) {
        this.chairCapacity = chairCapacity;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }
}

       /* POSSIBLE BEHAVIORS and METHODS:
-Store table data
-Estimate price based on manufactuer, chair capacity, and material.
*/