package DBAccessObj;

import Model.Country;
import Model.Division;
import Utility.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;



/** This class handles the logic for divisions within the database.
 *
 * @author Ajuane Rogers*/
public class DBAccessDivisions {

    /** This method will get a list of Divisions.
     * @return ObservableList List w/ Divisions
     * @throws SQLException SQLException
     */
    public static ObservableList<Division> getAllDivisions() throws SQLException {

        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String queryToDBStatement = "SELECT * FROM first_level_divisions;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Division newDiv = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("Country_ID")
                );

                divisions.add(newDiv);

            }

            return divisions;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return null;

        }

    }



    /** This method will get a division base on division name.
     * @param division division
     * @return a division
     * @throws SQLException SQLException
     */
    public static Division getADivisionId(String division) throws SQLException {

        String queryToDBStatement = "SELECT * FROM first_level_divisions WHERE Division=?";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setString(1, division);

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {

                Division newDiv = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("Country_ID")
                );

                return newDiv;

            }

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());

        }

        return null;

    }



    /** This method will get a list of divisions base on country.
     * @param country country
     * @return a list containing divisions
     * @throws SQLException SQLException
     */
    public static ObservableList<Division> getDivisionsByCountry(String country) throws SQLException {

        Country newCout = DBAccessCountries.getACountryId(country);

        ObservableList<Division> divisions = FXCollections.observableArrayList();

        String queryToDBStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID=?;";

        DBConnectQueryforPS.setPreparedStatement(DBConnect.connection(), queryToDBStatement);
        PreparedStatement preparedStatement = DBConnectQueryforPS.getPreparedStatement();

        preparedStatement.setInt(1, newCout.getCountry_Id());

        try {

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();


            while (resultSet.next()) {

                Division newDiv = new Division(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getInt("Country_ID")
                );

                divisions.add(newDiv);

            }

            return divisions;

        }

        catch (Exception expt) {

            System.out.println("Error: " + expt.getMessage());
            return null;

        }

    }


    /**
     * This method returns all divisions located in the US.
     *
     * @return divisions that have a country Id as "1"
     *
    public static ObservableList<Division> getUSDivisions() {

        ObservableList<Division> listOfUsDivisions = FXCollections.observableArrayList();

        try {

            String sqlUSDivisions = "SELECT* FROM first_level_divisions WHERE COUNTRY_ID = 1";

            PreparedStatement preState = DBConnect.connection().prepareStatement(sqlUSDivisions);

            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {

                int divisionId = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryId = resultSet.getInt("COUNTRY_ID");
                Division div = new Division(divisionId, divisionName, countryId);
                listOfUsDivisions.add(div);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return listOfUsDivisions;

    }



    /**
     * This method returns all divisions located in the UK.
     *
     * @return divisions that have a country Id as "2"
     *
    public static ObservableList<Division> getUKDivisions() {

        ObservableList<Division> listOfUKDivisions = FXCollections.observableArrayList();

        try {

            String sqlUKDivisions = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 2";

            PreparedStatement preState = DBConnect.connection().prepareStatement(sqlUKDivisions);

            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {

                int divisionId = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryId = resultSet.getInt("COUNTRY_ID");
                Division div = new Division(divisionId, divisionName, countryId);
                listOfUKDivisions.add(div);

            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

        return listOfUKDivisions;

    }



    /**
     * This method returns all divisions located in the Canada.
     *
     * @return divisions that have a country Id as "3"
     *
    public static ObservableList<Division> getCANDivisions() {

        ObservableList<Division> listOfCANDivisions = FXCollections.observableArrayList();

        try {

            String sqlCANDivisions = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 3";

            PreparedStatement preState = DBConnect.connection().prepareStatement(sqlCANDivisions);

            ResultSet resultSet = preState.executeQuery();

            while (resultSet.next()) {

                int divisionId = resultSet.getInt("Division_ID");
                String divisionName = resultSet.getString("Division");
                int countryId = resultSet.getInt("COUNTRY_ID");
                Division div = new Division(divisionId, divisionName, countryId);
                listOfCANDivisions.add(div);

            }

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

        return listOfCANDivisions;

    } */

}


