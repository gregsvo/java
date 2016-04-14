package Email_DTO;

import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class Email {

    private String address;
    private int status;//1=SUBSCRIBED 2=RESUBSCRIBED 3=
    private LocalDate lastUpdated;
    private int id;

    public static final int SUBSCRIBED = 1;
    public static final int RESUBSCRIBED = 2;
    public static final int UNSUBSCRIBED = 3;

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the subscribed
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the subscribed to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the lastUpdated
     */
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
