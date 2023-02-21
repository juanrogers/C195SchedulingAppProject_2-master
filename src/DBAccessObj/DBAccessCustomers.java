package DBAccessObj;

import Model.Division;
import Utility.DBConnect;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;



/** This class handles the logic for customers within the database.
 *
 * @author Ajuane Rogers*/
public class DBAccessCustomers {

    /** This method will return all customers in the database.
     * @return  a returns list of Customers
     * @throws SQLException SQLException
     *
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {

        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String queryToDBStatement = "SELECT * FROM customers AS custs INNER JOIN first_level_divisions AS fld ON custs.Division_ID = fld.Division_ID INNER JOIN countries AS cout ON cout.Country_ID=d.COUNTRY_ID;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Customer newCust = new Customer(
                        resultSet.getInt("Customer_ID"),
                        resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Postal_Code"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Division"),
                        resultSet.getString("Country"),
                        resultSet.getInt("Division_ID")
                );

                customers.add(newCust);

            }

            return customers;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return null;

        }

    }



    /** This method will add a new customer to the database.
     * @param name name
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param division division
     * @return will return true: if the customer was added successfully, false: if not
     * @throws SQLException
     */
    public static boolean addCustomer(String name, String address, String postalCode, String phone, String division) throws SQLException {

        Division newDiv = DBAccessDivisions.getADivisionId(division);

        String queryToDBStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?)";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, newDiv.getDivision_Id());

        try {

            preparedStatement.execute();

            if (preparedStatement.getUpdateCount() > 0) {

                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());

            }

            else {

                System.out.println("No changes were detected.");

            }

            return true;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return false;

        }

    }



    /** This method will update customers in the database.
     * @param customerId customerId
     * @param name  name
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param division division
     * @return will return true: if the customer was updated successfully, false:  if not
     * @throws SQLException SQLException
     */
    public static boolean updateCustomer(int customerId, String name, String address, String postalCode, String phone, String division) throws SQLException {

        Division newDiv = DBAccessDivisions.getADivisionId(division);

        String queryToDBStatement = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, newDiv.getDivision_Id());
        preparedStatement.setInt(6, customerId);

        try {

            preparedStatement.execute();

            if (preparedStatement.getUpdateCount() > 0) {

                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());

            }

            else {

                System.out.println("No changes were detected.");

            }

            return true;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return false;

        }

    }



    /** This method will delete a customer from the database.
     * @param customerId customerId
     * @return will return true: if the customer was deleted successfully, false: if not
     * @throws SQLException SQLException
     */
    public static boolean deleteCustomer(int customerId) throws SQLException {

        String queryToDBStatement = "DELETE FROM customers WHERE Customer_Id=?";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setInt(1, customerId);

        try {

            preparedStatement.execute();

            if (preparedStatement.getUpdateCount() > 0) {

                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());

            }

            else {

                System.out.println("No changes were detected.");

            }

            return true;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return false;

        }

    }


    /**
     * This method returns all customers in the database.
     *
     * @return all customers in the database
     *
    public static ObservableList<Customer> getAllCustomers() {

        ObservableList<Customer> listOfCustomers = FXCollections.observableArrayList();

        try {

            String sqlGetCusts = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, " +
                    "first_level_divisions.COUNTRY_ID, first_level_divisions.Division FROM customers, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID ORDER BY Customer_ID";

            PreparedStatement preState = DBConnect.connection().prepareStatement(sqlGetCusts);
            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {

                int customer_Id = resultSet.getInt("Customer_ID");
                String customerName = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phone = resultSet.getString("Phone");
                int division_Id = resultSet.getInt("Division_ID");
                int country_Id = resultSet.getInt("COUNTRY_ID");
                String divisionName = resultSet.getString("Division");

                Customer custs = new Customer(customer_Id, customerName, address, postalCode, phone, country_Id, division_Id);
                listOfCustomers.add(custs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return listOfCustomers;

    }


    /**
     * This method will add a customer to the database.
     *
     * @param customerName name of customer
     * @param address      address of customer
     * @param postalCode   postal code of customer
     * @param phone        phone number of customer
     * //@param divisionId   division Id of customer
     *
    public static void addCustomer(String customerName, String address, String postalCode, String phone, int divisionId) {

        try {

            String sqladdCustomer = "INSERT INTO customers VALUES (NULL, ?, ?, ?, ?, NOW(), 'RZ', NOW(), 'RZ', ?)";

            PreparedStatement preState = DBConnect.connection().prepareStatement(sqladdCustomer);

            preState.setString(1, customerName);
            preState.setString(2, address);
            preState.setString(3, postalCode);
            preState.setString(4, phone);
            preState.setInt(5, divisionId);

            preState.execute();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }


    /**
     * This method updates a customer in the database.
     *
     * @param customer_Id  customer_Id
     * @param customerName customerName
     * @param address      address
     * @param postalCode   postalCode
     * @param phone        phone
     * @param division     division
     *
     *
    public static boolean updateCustomer(int customer_Id, String customerName, String address, String postalCode, String phone, String division) throws SQLException {

        Division newDivision = DBAccessDivisions.getDivisionId(division);

       /* try {

            String sqlupdateCust = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";


            PreparedStatement preState = DBConnect.connection().prepareStatement(sqlupdateCust);

            preState.setString(1, customerName);
            preState.setString(2, address);
            preState.setString(3, postalCode);
            preState.setString(4, phone);
            preState.setInt(5, divisionId);
            preState.setInt(6, customerId);

            preState.execute();

        } catch (SQLException e) {

            e.printStackTrace();

        } */




    /**
     * This method deletes a customer from the database.
     *
     * @param customer_Id customer_Id
     * @return will returns true: if customer was deleted, false if not
     * @throws SQLException exception
     *
    public static boolean deleteCustomer(int customer_Id) throws SQLException {

        String insertStatement = "DELETE from customers WHERE Customer_Id=?";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), insertStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setInt(1, customer_Id);

        try {

            preparedStatement.execute();

            if (preparedStatement.getUpdateCount() > 0) {

                System.out.println("Rows affected: " + preparedStatement.getUpdateCount());
            } else {

                System.out.println("No change found.");

            }

            return true;

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            return false;

        }


        /* try {

            String sqldeleteAppt = "DELETE FROM appointments WHERE Customer_ID = ?";

            PreparedStatement preStateDelAppt = DBConnect.connection().prepareStatement(sqldeleteAppt);

            preStateDelAppt.setInt(1, customerId);

            preStateDelAppt.execute();


            String sqldeleteCust = "DELETE FROM customers WHERE Customer_ID = ?";

            PreparedStatement preStateDelCust = DBConnect.connection().prepareStatement(sqldeleteCust);

            preStateDelCust.setInt(1, customerId);

            preStateDelCust.execute();

        }

        catch (SQLException e) {

            e.printStackTrace();

        } */


    }














