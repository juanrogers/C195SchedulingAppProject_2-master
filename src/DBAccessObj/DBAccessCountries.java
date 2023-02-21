package DBAccessObj;

import Model.Country;
import Utility.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;


/** This class handles the logic for countries within the database.
 *
 * @author Ajuane Rogers*/
public class DBAccessCountries {

    /** This method will get a list of Countries.
     * @return ObservableList List w/ Countries
     * @throws SQLException SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> countries = FXCollections.observableArrayList();

        String queryToDBStatement = "SELECT * FROM countries;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();


            while (resultSet.next()) {

                Country newCout = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );

                countries.add(newCout);

            }

            return countries;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return null;

        }

    }



    /** This method will get a list of Countries.
     * @return ObservableList List w/ Countries
     * @throws SQLException SQLException
     */
    public static Country getACountryId(String country) throws SQLException {

        String queryToDBStatement = "SELECT * FROM countries WHERE Country=?";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setString(1, country);

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Country newCout = new Country(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country")
                );

                return newCout;

            }

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());

        }

        return null;

    }


    /**
     * This method returns all countries in the database.
     *
     * @return countries in the database
     *
    public static ObservableList<Country> getAllCountries() {

        ObservableList<Country> listOfCountries = FXCollections.observableArrayList();

        try {

            String sqlCountrylist = "SELECT * FROM countries";

            PreparedStatement preState = DBConnect.connection().prepareStatement(sqlCountrylist);

            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {

                int countryId = resultSet.getInt("Country_ID");
                String countryName = resultSet.getString("Country");
                Country contr = new Country(countryId, countryName);
                listOfCountries.add(contr);

            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

        return listOfCountries;
    } */

}


