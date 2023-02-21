package Model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


/** This class will be used to handle appointments.
 *
 * @author Ajuane Rogers */
public class Appointment {

    private int appointment_Id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDate startDate;
    private LocalDateTime startTime;
    private LocalDate endDate;
    private LocalDateTime endTime;
    private int customer_Id;
    private int user_Id;
    private int contact_Id;
    private String contactName;

    /** This is the constructor used for building an appointment.
     *
     * @param appointment_Id This holds the id of the appointment.
     * @param title This holds the title of the appointment.
     * @param description This holds the description of the appointment.
     * @param location This holds the location of the appointment.
     * @param type This holds the type of appointment.
     * @param startDate This holds the start date of the appointment.
     * @param startTime This holds the start time of the appointment.
     * @param endDate This holds the end date of the appointment.
     * @param endTime This holds the end time of the appointment.
     * @param customer_Id This holds the customer Id for the appointment.
     * @param user_Id This holds the user Id for with the appointment.
     * @param contact_Id This holds the contact Id for the appointment.
     * @param contactName This holds the contact name for the appointment.
     */
    public Appointment (int appointment_Id, String title, String description, String location, String type, LocalDate startDate, LocalDateTime startTime, LocalDate endDate, LocalDateTime endTime, int customer_Id, int user_Id, int contact_Id, String contactName) {

        this.appointment_Id = appointment_Id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customer_Id = customer_Id;
        this.user_Id = user_Id;
        this.contact_Id = contact_Id;
        this.contactName = contactName;

    }

   // public Appointment(int appointment_id, String title, String description, String location, int contact_id, String contactName, String type, Timestamp startTime, Timestamp endTime, int custId, int user_id) {
   // }



    /**
     * Getters listed below
     */



    /**
     * @return will return the appointment_Id
     */
    public int getAppointment_Id() {

        return appointment_Id;

    }

    /**
     * @return will return the title
     */
    public String getTitle() {

        return title;

    }

    /**
     * @return will return the description
     */
    public String getDescription() {

        return description;

    }

    /**
     * @return will return the location
     */
    public String getLocation() {

        return location;

    }

    /**
     * @return will return the type
     */
    public String getType() {

        return type;

    }

    /**
     * @return will return the start
     */
    public LocalDate getStartDate() {

        return startDate;

    }

    /**
     * @return will return the end
     */
    public LocalDateTime getStartTime() {

        return startTime;

    }

    /**
     * @return will return the start
     */
    public LocalDate getEndDate() {

        return getEndDate();

    }

    /**
     * @return will return the end
     */
    public LocalDateTime getEndTime() {

        return endTime;

    }


    /**
     * @return Getter for the customer_Id
     */
    public int getCustomer_Id() {

        return customer_Id;

    }

    /**
     * @return Getter for the user_Id
     */
    public int getUser_Id() {

        return user_Id;

    }

    /**
     * @return Getter for the contact_Id
     */
    public int getContact_Id() {

        return contact_Id;

    }

    /**
     * @return Getter for the contactName
     */
    public String getContactName() {

        return contactName;

     }



    /**
     * Setters listed below
     */



    /**
     * @param appointment_Id Setter for the appointment_Id
     */
    public void setAppointment_Id(int appointment_Id) {

        this.appointment_Id = appointment_Id;

    }

    /**
     * @param title Setter for the title
     */
    public void setTitle(String title) {

        this.title = title;

    }

    /**
     * @param description Setter for the description
     */
    public void setDescription(String description) {

        this.description = description;

    }

    /**
     * @param location Setter for the location
     */
    public void setLocation(String location) {

        this.location = location;

    }

    /**
     * @param type Setter for the type
     */
    public void setType(String type) {

        this.type = type;

    }

    /**
     * @param startDate Setter for the start date
     */
    public void setStartDate(LocalDate startDate) {

        this.startDate = startDate;

    }

    /**
     * @param startTime Setter for the start time
     */
    public void setStartTime(LocalDateTime startTime) {

        this.startTime = startTime;

    }

    /**
     * @param endDate Setter for the end date
     */
    public void setEndDate(LocalDate endDate) {

        this.endDate = endDate;

    }

    /**
     * @param endTime Setter for the end time
     */
    public void setEndTime(LocalDateTime endTime) {

        this.endTime = endTime;

    }

    /**
     * @param customer_Id Setter for the customer_Id
     */
    public void setCustomer_Id(int customer_Id) {

        this.customer_Id = customer_Id;

    }

    /**
     * @param user_Id Setter for the user_Id
     */
    public void setUser_Id(int user_Id) {

        this.user_Id = user_Id;

    }

    /**
     * @param contact_Id Setter for the contact_Id
     */
    public void setContact_Id(int contact_Id) {

        this.contact_Id = contact_Id;

    }

    /**
     * @param contactName Setter for the contactName
     */
    public void setContactName(String contactName) {

        this.contactName = contactName;

    }

}