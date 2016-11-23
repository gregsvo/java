
package com.mycompany.classmodeling;


public class Employee {
    
    private String firstName;
    private String lastName;
    private int empNumber;
    private double salary;
    private String title;
    
    public Employee (String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the empNumber
     */
    public int getEmpNumber() {
        return empNumber;
    }


    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
/* POSSIBLE BEHAVIORS and METHODS:
-If job title changed to certain parameters, set minimum salary to:__________
-If job title set at certain date, remind management of review for job at 6mo, 1yr.
*/
