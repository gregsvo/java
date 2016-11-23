
package com.mycompany.classmodeling;


public class Student {
    private String nameFirst;
    private String nameLast;
    private int gradDate;
    private float GPA;
    private float balance;
    private boolean inGoodStanding;
    
    public Student (String nameFirst, String nameLast){
        this.nameFirst=nameFirst;
        this.nameLast=nameLast;
    }

    /**
     * @return the nameFirst
     */
    public String getNameFirst() {
        return nameFirst;
    }

    /**
     * @param nameFirst the nameFirst to set
     */
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    /**
     * @return the nameLast
     */
    public String getNameLast() {
        return nameLast;
    }

    /**
     * @param nameLast the nameLast to set
     */
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    /**
     * @return the gradDate
     */
    public int getGradDate() {
        return gradDate;
    }

    /**
     * @param gradDate the gradDate to set
     */
    public void setGradDate(int gradDate) {
        this.gradDate = gradDate;
    }

    /**
     * @return the GPA
     */
    public float getGPA() {
        return GPA;
    }

    /**
     * @param GPA the GPA to set
     */
    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * @return the inGoodStanding
     */
    public boolean isInGoodStanding() {
        return inGoodStanding;
    }

    /**
     * @param inGoodStanding the inGoodStanding to set
     */
    public void setInGoodStanding(boolean inGoodStanding) {
        this.inGoodStanding = inGoodStanding;
    }

}
/* POSSIBLE BEHAVIORS and METHODS:
-Keep track of GPA, return GPA average
-if student is not in good standing, notify.
-if student not in good standing, do not allow graduation.
-if student has balance, set not in good standing to false, notify student.
*/
