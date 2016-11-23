
package com.mycompany.classmodeling;


public class Chair {
    
    private String manufacturer;
    private String material;
    private String style;
    private int numOfLegs;
    private float heightOfSeat;
    private boolean scuffsWoodFloors;
    
    public Chair(String manufacturer, String material, String style){
        this.manufacturer=manufacturer;
        this.material=material;
        this.style=style;
        
 
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

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * @return the numOfLegs
     */
    public int getNumOfLegs() {
        return numOfLegs;
    }

    /**
     * @param numOfLegs the numOfLegs to set
     */
    public void setNumOfLegs(int numOfLegs) {
        this.numOfLegs = numOfLegs;
    }

    /**
     * @return the heightOfSeat
     */
    public float getHeightOfSeat() {
        return heightOfSeat;
    }

    /**
     * @param heightOfSeat the heightOfSeat to set
     */
    public void setHeightOfSeat(float heightOfSeat) {
        this.heightOfSeat = heightOfSeat;
    }

    /**
     * @return the scuffsWoodFloors
     */
    public boolean isScuffsWoodFloors() {
        return scuffsWoodFloors;
    }

    /**
     * @param scuffsWoodFloors the scuffsWoodFloors to set
     */
    public void setScuffsWoodFloors(boolean scuffsWoodFloors) {
        this.scuffsWoodFloors = scuffsWoodFloors;
    }
    
    
        /* POSSIBLE BEHAVIORS and METHODS:
-Estimate price via materials and style
-Estimate if extra leg needed due to height
-Notify before sale to let customer know it scuffs wood floors.
*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
