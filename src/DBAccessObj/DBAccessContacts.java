package DBAccessObj;

import Utility.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** This class handles the logic for contacts within the database.
 *
 * @author Ajuane Rogers */
public class DBAccessContacts {

    /**
     * This method returns all contacts in the database.
     *
     * @return all contacts in the database
     */
    public static ObservableList<Contact> getAllContacts() {

        ObservableList<Contact> listOfContacts = FXCollections.observableArrayList();

        try {

            String sqlGetContacts = "SELECT * FROM contacts";

            PreparedStatement preState = DBConnect.connection().prepareStatement(sqlGetContacts);

            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {

                int contactId = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");
                String contactEmail = resultSet.getString("Email");
                Contact cont = new Contact(contactId, contactName, contactEmail);
                listOfContacts.add(cont);

            }

        }

        catch (SQLException expt) {

            expt.printStackTrace();

        }

        return listOfContacts;

    }


    /** This will method gets contacts from the database, based on the contact name.
     * @param contactName contactName
     * @return will return a contact
     * @throws SQLException SQLException
     */
    public static Contact getContact_Id(String contactName) throws SQLException {

        String queryToDBStatement = "SELECT * FROM contacts WHERE Contact_Name=?";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setString(1, contactName);

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();;

            while (resultSet.next()) {
                Contact newCont = new Contact(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")
                );

                return newCont;

            }

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());

        }

        return null;

    }

}






