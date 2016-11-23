
package com.mycompany.classmodeling;


public class Airplane {
    private String make;
    private String model;
    private Float fuelLevel;
    private int fuelCapacity;
    private int numberOfSeats;
    private int milageRange;
    private int runwayLengthRequirement;

public Airplane (String make, String model){
        this.make = make;
        this.model = model;
}

public Airplane (String make, String model, Float fuelLevel, int fuelCapacity, int numbrOfSeats, int milageRange, int runwayLengthRequirement){
        this.make = make;
        this.model = model;
        this.fuelLevel = fuelLevel;
        this.fuelCapacity = fuelCapacity;
        this.numberOfSeats = numberOfSeats;
        this.milageRange = milageRange;
        this.runwayLengthRequirement = runwayLengthRequirement;
}

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the fuelLevel
     */
    public Float getFuelLevel() {
        return fuelLevel;
    }

    /**
     * @param fuelLevel the fuelLevel to set
     */
    public void setFuelLevel(Float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    /**
     * @return the fuelCapacity
     */
    public int getFuelCapacity() {
        return fuelCapacity;
    }

    /**
     * @param fuelCapacity the fuelCapacity to set
     */
    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    /**
     * @return the numberOfSeats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * @param numberOfSeats the numberOfSeats to set
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * @return the milageRange
     */
    public int getMilageRange() {
        return milageRange;
    }

    /**
     * @param milageRange the milageRange to set
     */
    public void setMilageRange(int milageRange) {
        this.milageRange = milageRange;
    }

    /**
     * @return the runwayLengthRequirement
     */
    public int getRunwayLengthRequirement() {
        return runwayLengthRequirement;
    }

    /**
     * @param runwayLengthRequirement the runwayLengthRequirement to set
     */
    public void setRunwayLengthRequirement(int runwayLengthRequirement) {
        this.runwayLengthRequirement = runwayLengthRequirement;
    }

 /* POSSIBLE BEHAVIORS and METHODS:
-Notify at certain fuel level
-Notify if inputed runway is below runway length requirement.
-Notify if extra weight from full seating will require more fuel added to current fuel level.
- 
*/







}


