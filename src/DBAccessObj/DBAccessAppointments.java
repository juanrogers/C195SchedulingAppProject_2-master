package DBAccessObj;


import Model.Contact;
import Utility.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.Appointment;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


/** This class handles the logic for appointments within the database.
 *
 * @author Ajuane Rogers*/
public class DBAccessAppointments {
    /**
     * This method will return all appointments listed in the database.
     *
     * @return appointments in the database
     * @throws SQLException SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {

        ObservableList<Appointment> listOfAppointments = FXCollections.observableArrayList();

        String queryStatement = "SELECT * FROM appointments AS appt INNER JOIN contacts AS cont ON appt.Contact_ID=cont.Contact_ID;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointment newAppt = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                listOfAppointments.add(newAppt);

            }

            return listOfAppointments;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return null;

        }

    }


    /**
     * This method will return all appointments in the database that are scheduled in the current week.
     *
     * @return appointments in the database from the current week
     * @throws SQLException SQLException
     */
    public static ObservableList<Appointment> getWeekAppointments() throws SQLException {

        ObservableList<Appointment> weekAppointmentsList = FXCollections.observableArrayList();

        LocalDateTime theDateForToday = LocalDateTime.now();
        LocalDateTime theLastWeek = theDateForToday.minusDays(7);

        String queryStatement = "SELECT * FROM appointments AS appt INNER JOIN contacts AS cont ON appt.Contact_ID=cont.Contact_ID WHERE Start < ? AND Start > ?;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setDate(1, java.sql.Date.valueOf(theDateForToday.toLocalDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(theLastWeek.toLocalDate()));

        try {
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                weekAppointmentsList.add(newAppointment);

            }

            return weekAppointmentsList;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return null;

        }

    }



    /**
     * This method will return all appointments in the database that are scheduled in the current month.
     *
     * @return appointments in the database from the current month
     * @throws SQLException SQLException
     */
    public static ObservableList<Appointment> getMonthAppointments() throws SQLException{

        ObservableList<Appointment> monthAppointmentsList = FXCollections.observableArrayList();

        LocalDateTime theDateForToday = LocalDateTime.now();
        LocalDateTime theLastMonth = theDateForToday.minusDays(30);

        String queryStatement = "SELECT * FROM appointments AS appt INNER JOIN contacts AS cont ON appt.Contact_ID=cont.Contact_ID WHERE Start < ? AND Start > ?;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setDate(1, java.sql.Date.valueOf(theDateForToday.toLocalDate()));
        preparedStatement.setDate(2, java.sql.Date.valueOf(theLastMonth.toLocalDate()));

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Appointment newAppt = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                monthAppointmentsList.add(newAppt);

            }

            return monthAppointmentsList;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return null;

        }

    }



    /**
     * This method will check to see if there are overlapping appointments.
     *
     * //@param appointment appointment to be checked.
     * ZonedDateTime startTime, ZonedDateTime endTime, Integer appt_Id to be checked
     * If there are overlapping appointments: true, if there are not overlapping appointments: false.
     *
    public static Boolean checkOverlappingAppointments(Appointment appointment) {

        try {

            String sqlChkOverlapAppts = "SELECT * FROM appointments WHERE ((? <= Start AND ? > Start) OR (? >= Start AND ? < End)) AND Customer_ID = ? AND Appointment_ID <> ?";

            PreparedStatement chkOverlapAppts = DBConnect.connection().prepareStatement(sqlChkOverlapAppts);

            chkOverlapAppts.setTimestamp(1, appointment.getStart());
            chkOverlapAppts.setTimestamp(2, appointment.getEnd());
            chkOverlapAppts.setTimestamp(3, appointment.getStart());
            chkOverlapAppts.setTimestamp(4, appointment.getStart());
            chkOverlapAppts.setInt(5, appointment.getCustomer_Id());
            chkOverlapAppts.setInt(6, appointment.getAppointment_Id());

            ResultSet resultSet = chkOverlapAppts.executeQuery();

            while (resultSet.next()) {
                return true;
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }  */





    /** This method will add an apppointment to the database.
     * @param contactName contactName
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param customer_Id customer_Id
     * @param user_Id user_Id
     * @return will return true: if appointment was added successfully, false: if not
     * @throws SQLException SQLException
     */
    public static boolean addAppointment(String contactName, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customer_Id, Integer user_Id) throws SQLException {

        Contact cont = DBAccessContacts.getContact_Id(contactName);

        String queryToDBStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, Contact_ID, User_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customer_Id);
        if (cont != null) {
            preparedStatement.setInt(8, cont.getContact_Id());
        }
        preparedStatement.setInt(9, user_Id);

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



    /** This method updates an Appointment by Appointment ID
     * @param contactName contactName
     * @param title title
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param customer_Id customer_Id
     * @param user_Id user_ID
     * @param appointment_Id appointment_ID
     * @return will return true: if the appointment updated successfully, false: if not
     * @throws SQLException SQLException
     */
    public static boolean updateAppointment(String contactName, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Integer customer_Id, Integer user_Id, Integer appointment_Id) throws SQLException {

        Contact contact = DBAccessContacts.getContact_Id(contactName);

        String queryToDBStatement = "UPDATE appointments SET Title=?, Description=?, Location=?, Type=?, Start=?, End=?, Customer_ID=?, Contact_ID=?, User_ID=? WHERE Appointment_ID = ?;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customer_Id);
        preparedStatement.setInt(8, contact.getContact_Id());
        preparedStatement.setInt(9, user_Id);
        preparedStatement.setInt(10, appointment_Id);

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

        catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            return false;

        }

    }



    /**
     * This method will delete an appointment from the database.
     * @param appointment_Id appointment_Id
     * @return will return true: if appointment was deleted successfully, false: if not
     * @throws SQLException SQLException
     */
    public static boolean deleteAppointment(int appointment_Id) throws SQLException {

        String queryToDBStatement = "DELETE from appointments WHERE Appointment_Id=?";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setInt(1, appointment_Id);

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



       /* try {

            String sqldeleteAppt = "DELETE FROM appointments WHERE Appointment_ID = ?";

            PreparedStatement deleteAppt = DBConnect.connection().prepareStatement(sqldeleteAppt);

            deleteAppt.setInt(1, appointment_Id);

            deleteAppt.execute();

        }

        catch (SQLException e) {

            e.printStackTrace();

        } */



    }



    /**
     * This method will return all appointment types in the database.
     *
     * @return appointment types in the database
     */
    public static ObservableList<String> getAllTypesOfAppts() {

        ObservableList<String> typesOfApptsList = FXCollections.observableArrayList();

        try {

            String sqlGetAllTypes = "SELECT DISTINCT type FROM appointments";

            PreparedStatement getAllTypes = DBConnect.connection().prepareStatement(sqlGetAllTypes);


            ResultSet resultSet = getAllTypes.executeQuery();

            while (resultSet.next()) {

                typesOfApptsList.add(resultSet.getString(1));

            }

        }

        catch (SQLException expt) {

            expt.printStackTrace();

        }

        return typesOfApptsList;

    }



    /**
     *
     * This method will return a specific type and number or appointments and that are in a specific month.
     *
     * @param month a specific month
     * @param type a specific type
     * @return total number of appointments
     */
    public static int getTypeAndMonthCount(String month, String type) {

        int total = 0;

        try {

            String sqlTypeAndMonth = "SELECT count(*) FROM appointments WHERE type = ? AND monthname(start) = ?";


            PreparedStatement typeAndMonth = DBConnect.connection().prepareStatement(sqlTypeAndMonth);


            typeAndMonth.setString(1, type);
            typeAndMonth.setString(2, month);

            ResultSet resultSet = typeAndMonth.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1);

            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

        return total;

    }



    /** This method will get an appointment by searching for the customer Id
     * @param customerID customerID
     * @return list of Appointments
     * @throws SQLException exception
     */
    public static ObservableList<Appointment> getApptsByCustomerId(int customerID) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        String queryToDBStatement = "SELECT * FROM appointments AS appt INNER JOIN contacts AS cnt ON appt.Contact_ID=cnt.Contact_ID WHERE Customer_ID=?;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setInt(1, customerID);

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Appointment newAppointment = new Appointment(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getDate("Start").toLocalDate(),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getDate("End").toLocalDate(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name")
                );

                appointments.add(newAppointment);
            }

            return appointments;

        }

        catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            return null;

        }

    }

}

