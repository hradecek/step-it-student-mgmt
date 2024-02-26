package cz.stepit.student.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class responsible for creating and managing database {@link Connection}.
 */
public class DBConnectionManager {

    protected final String connectionUrl;

    public DBConnectionManager(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            throw new DBConnectionException(ex);
        }
    }
}
