package com.mycompany.statecapitals2;


public class CapitalInfo {
    
    private String name;
    private double population;
    private double squareMilage;
    
    public CapitalInfo(String x, Double y, Double z){
        this.name = x;
        this.population = y;
        this.squareMilage = z;
    }
    
    public CapitalInfo() {
        
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
     * @return the population
     */
    public double getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(double population) {
        this.population = population;
    }

    /**
     * @return the squareMilage
     */
    public double getSquareMilage() {
        return squareMilage;
    }

    /**
     * @param squareMilage the squareMilage to set
     */
    public void setSquareMilage(double squareMilage) {
        this.squareMilage = squareMilage;
    }

}

