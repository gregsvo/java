
package com.mycompany.classmodeling;


public class City {
    private String name;
    private String location;
    private int population;
    private int zombiesPresent;
    private int populationAfterNukeFallout;
    private boolean giantSpiders;
    
   public City (String name, String location){
       this.name = name;
       this.location = location;
   }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * @return the zombiesPresent
     */
    public int getZombiesPresent() {
        return zombiesPresent;
    }

    /**
     * @param zombiesPresent the zombiesPresent to set
     */
    public void setZombiesPresent(int zombiesPresent) {
        this.zombiesPresent = zombiesPresent;
    }

    /**
     * @return the populationAfterNukeFallout
     */
    public int getPopulationAfterNukeFallout() {
        return populationAfterNukeFallout;
    }

    /**
     * @param populationAfterNukeFallout the populationAfterNukeFallout to set
     */
    public void setPopulationAfterNukeFallout(int populationAfterNukeFallout) {
        this.populationAfterNukeFallout = populationAfterNukeFallout;
    }

    /**
     * @return the giantSpiders
     */
    public boolean isGiantSpiders() {
        return giantSpiders;
    }

    /**
     * @param giantSpiders the giantSpiders to set
     */
    public void setGiantSpiders(boolean giantSpiders) {
        this.giantSpiders = giantSpiders;
    }
   
         /* POSSIBLE BEHAVIORS and METHODS:
-If giant spiders are present, notify if nukes are necessary.
-If nukes used, update population change after fallout.
-If zombies present, and giant spiders, automatically notify of nuke launch.
*/
    
    
}


