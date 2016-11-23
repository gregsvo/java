
package com.mycompany.classmodeling;


public class School {
    
    private int studentCapacity;
    private boolean MonthylHealthInspection;
    private int studentAttendance;
    private Float graduationRate;
    private String schoolName;
    private String address;
    private String principal;

    public School (String schoolName, String address, String principal){
        this.schoolName = schoolName;
        this.address = address;
        this.principal = principal;
    }

    /**
     * @return the studentCapacity
     */
    public int getStudentCapacity() {
        return studentCapacity;
    }

    /**
     * @param studentCapacity the studentCapacity to set
     */
    public void setStudentCapacity(int studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    /**
     * @return the MonthylHealthInspection
     */
    public boolean isMonthylHealthInspection() {
        return MonthylHealthInspection;
    }

    /**
     * @param MonthylHealthInspection the MonthylHealthInspection to set
     */
    public void setMonthylHealthInspection(boolean MonthylHealthInspection) {
        this.MonthylHealthInspection = MonthylHealthInspection;
    }

    /**
     * @return the studentAttendance
     */
    public int getStudentAttendance() {
        return studentAttendance;
    }

    /**
     * @param studentAttendance the studentAttendance to set
     */
    public void setStudentAttendance(int studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    /**
     * @return the graduationRate
     */
    public Float getGraduationRate() {
        return graduationRate;
    }

    /**
     * @param graduationRate the graduationRate to set
     */
    public void setGraduationRate(Float graduationRate) {
        this.graduationRate = graduationRate;
    }

    /**
     * @return the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the principal
     */
    public String getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    
     /* POSSIBLE BEHAVIORS and METHODS:
-Notify if graduate rate dips below certain point
-Notify if monthly review has not been completed
-Notify if student attendance drops below certain percentage of student capacity.
- 
*/
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
