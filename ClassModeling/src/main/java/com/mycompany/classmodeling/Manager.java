
package com.mycompany.classmodeling;


public class Manager {
    
        private String firstName;
        private String lastName;
        private int empNumber;
        private double salary;
        private String title;
        private String startDate;
        private String department;
    
    public Manager (String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }
    
    public Manager (String firstName, String lastName, int empNumber, double salary, String title, String startDate, String department){
        this.firstName=firstName;
        this.lastName=lastName;
        this.empNumber=empNumber;
        this.salary=salary;
        this.startDate=startDate;
        this.department=department;
        this.title=title;
        
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

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
 /* POSSIBLE BEHAVIORS and METHODS:
-Set minium salary if assigned to certain department
-Notify of need for performance review at startDate+ + 6mo, + 1yr
- 
*/

    
    
    
    
    
}
