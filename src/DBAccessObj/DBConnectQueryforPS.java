package DBAccessObj;

import java.sql.*;

/**
 * This class will create a Prepared Statement object and return it.
 *
 */
public class DBConnectQueryforPS {

    private static PreparedStatement statement;

    /** This method will set the Prepared Statement object for database.
     * @param connection connection
     * @param sqlDbStatement sqlStatement
     * @throws SQLException throws exception
     */
    public static void setPreparedStatement(Connection connection, String sqlDbStatement) throws SQLException {

        statement = connection.prepareStatement(sqlDbStatement);

    }


    /** This method returns the Prepared Statement object
     * @return Prepared Statement
     */
    public static PreparedStatement getPreparedStatement() {

        return statement;

    }

}
