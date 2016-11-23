
package com.mycompany.classmodeling;


public class Truck {
    private Float fuelCapacity;
    private Float currentFuelLevel;
    private int numberOfDoors;
    private String make;
    private String model;
    private int year;
    private int towingCapacity;
    private boolean towingHitch;
    private String lastScheduledMaint;
    private String warrantyExperation;
    
    public Truck(String make, String model, String year){
        this.make=make;
        this.model=model;
        
    }

    /**
     * @return the fuelCapacity
     */
    public Float getFuelCapacity() {
        return fuelCapacity;
    }

    /**
     * @param fuelCapacity the fuelCapacity to set
     */
    public void setFuelCapacity(Float fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    /**
     * @return the currentFuelLevel
     */
    public Float getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    /**
     * @param currentFuelLevel the currentFuelLevel to set
     */
    public void setCurrentFuelLevel(Float currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    /**
     * @return the numberOfDoors
     */
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    /**
     * @param numberOfDoors the numberOfDoors to set
     */
    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the towingCapacity
     */
    public int getTowingCapacity() {
        return towingCapacity;
    }

    /**
     * @param towingCapacity the towingCapacity to set
     */
    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    /**
     * @return the towingHitch
     */
    public boolean isTowingHitch() {
        return towingHitch;
    }

    /**
     * @param towingHitch the towingHitch to set
     */
    public void setTowingHitch(boolean towingHitch) {
        this.towingHitch = towingHitch;
    }

    /**
     * @return the lastScheduledMaint
     */
    public String getLastScheduledMaint() {
        return lastScheduledMaint;
    }

    /**
     * @param lastScheduledMaint the lastScheduledMaint to set
     */
    public void setLastScheduledMaint(String lastScheduledMaint) {
        this.lastScheduledMaint = lastScheduledMaint;
    }

    /**
     * @return the warrantyExperation
     */
    public String getWarrantyExperation() {
        return warrantyExperation;
    }

    /**
     * @param warrantyExperation the warrantyExperation to set
     */
    public void setWarrantyExperation(String warrantyExperation) {
        this.warrantyExperation = warrantyExperation;
    }
    
/* POSSIBLE BEHAVIORS and METHODS:
-Store information of truck
-Notify of fuel reaches certain level.
-Notify of warranty experation.
-Generate resale value based on booleans, towing capacity, year, and warranty.
*/
    
}


