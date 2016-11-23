
package com.mycompany.classmodeling;


public class House {
    
    private String address;
    private String color;
    private int numOfbedrooms;
    private Float numOfbathrooms;
    private int squareFootage;
    private boolean yard;
    private boolean pool;
    private boolean culdisac;
    
public House(String address){
     this.address = address;
}    

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the numOfbedrooms
     */
    public int getNumOfbedrooms() {
        return numOfbedrooms;
    }

    /**
     * @param numOfbedrooms the numOfbedrooms to set
     */
    public void setNumOfbedrooms(int numOfbedrooms) {
        this.numOfbedrooms = numOfbedrooms;
    }

    /**
     * @return the numOfbathrooms
     */
    public Float getNumOfbathrooms() {
        return numOfbathrooms;
    }

    /**
     * @param numOfbathrooms the numOfbathrooms to set
     */
    public void setNumOfbathrooms(Float numOfbathrooms) {
        this.numOfbathrooms = numOfbathrooms;
    }

    /**
     * @return the squareFootage
     */
    public int getSquareFootage() {
        return squareFootage;
    }

    /**
     * @param squareFootage the squareFootage to set
     */
    public void setSquareFootage(int squareFootage) {
        this.squareFootage = squareFootage;
    }

    /**
     * @return the yard
     */
    public boolean isYard() {
        return yard;
    }

    /**
     * @param yard the yard to set
     */
    public void setYard(boolean yard) {
        this.yard = yard;
    }

    /**
     * @return the pool
     */
    public boolean isPool() {
        return pool;
    }

    /**
     * @param pool the pool to set
     */
    public void setPool(boolean pool) {
        this.pool = pool;
    }

    /**
     * @return the culdisac
     */
    public boolean isCuldisac() {
        return culdisac;
    }

    /**
     * @param culdisac the culdisac to set
     */
    public void setCuldisac(boolean culdisac) {
        this.culdisac = culdisac;
    }

}

/* POSSIBLE BEHAVIORS and METHODS:
-counting bedrooms
-proximity to mall, school, etc.
-approximate house value via booleans and square footage
*/
