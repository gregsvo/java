
package com.mycompany.classmodeling;


public class Car {
    private Float fuelCapacity;
    private Float currentFuelLevel;
    private int numberOfDoors;
    private String make;
    private String model;
    private int year;
    
    public Car(String make, String model, int year){
        this.make=make;
        this.model=model;
        this.year=year;
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
    
/* POSSIBLE BEHAVIORS and METHODS:
-Store information of car
-Notify of fuel reaches certain level.
*/
    
    
    
    
    
    
    
    
    
    
}
