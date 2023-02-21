package Model;


import DBAccessObj.DBAccessCustomers;

/** This class will be used to handle customers.
 *
 * @author Ajuane Rogers*/

public class Customer {

    private int customer_Id;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    public String divisionName;
    private String countryName;
    public int division_Id;

    /**
     * This is the constructor used for building a customer.
     *
     * @param customer_Id  The id of the customer.
     * @param customerName The name of the customer.
     * @param address      The address of the customer.
     * @param postalCode   The postal code of the customer.
     * @param phone        The phone number of the customer.
     * @param divisionName The divisionName for customer.
     * @param countryName  The countryName for customer.
     * @param division_Id  The division id for the customer.
     */
    public Customer(int customer_Id, String customerName, String address, String postalCode, String phone, String divisionName, String countryName, int division_Id) {

        this.customer_Id = customer_Id;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionName = divisionName;
        this.countryName = countryName;
        this.division_Id = division_Id;

    }


    /**
     * Getters listed below
     */


    /**
     * @return will return the customer_Id
     */
    public int getCustomer_Id() {

        return customer_Id;

    }

    /**
     * @return will return the customerName
     */
    public String getCustomerName() {

        return customerName;

    }

    /**
     * @return will return the address
     */
    public String getAddress() {

        return address;

    }

    /**
     * @return will return the postalCode
     */
    public String getPostalCode() {

        return postalCode;

    }

    /**
     * @return will return the phone
     */
    public String getPhone() {

        return phone;

    }

    /**
     * @return will return the divisionName
     */
    public String getDivisionName() {

        return divisionName;

    }

    /**
     * @return will return the countryName
     */
    public String getCountryName() {

        return countryName;

    }

    /**
     * @return will return the division_Id
     */
    public int getDivision_Id() {

        return division_Id;

    }



    /**
     * Setters listed below
     */



    /**
     * @param customer_Id Setter for the customer_Id
     */
    public void setCustomer_Id(int customer_Id) {

        this.customer_Id = customer_Id;

    }

    /**
     * @param customerName Setter for the customerName
     */
    public void setCustomerName(String customerName) {

        this.customerName = customerName;

    }


    /**
     * @param address Setter for the address
     */
    public void setAddress(String address) {

        this.address = address;

    }


    /**
     * @param postalCode Setter for the postalCode
     */
    public void setPostalCode(String postalCode) {

        this.postalCode = postalCode;

    }


    /**
     * @param phone Setter for the phone
     */
    public void setPhone(String phone) {

        this.phone = phone;

    }


    /**
     * @param divisionName Setter for the divisionName
     */
    public void setDivisionName(String divisionName) {

        this.divisionName = divisionName;

    }


    /**
     * @param countryName Setter for the countryName
     */
    public void setCountryName(String countryName) {

        this.countryName = countryName;

    }


    /**
     * @param division_Id Setter for the division_Id
     */
    public void setDivision_Id(int division_Id) {

        this.division_Id = division_Id;

    }

}



