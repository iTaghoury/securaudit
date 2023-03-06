package fr.m2i.securauditgroupe1.data;

import java.sql.*;

public class DataAccess implements AutoCloseable {
    private Connection connection;

    //region USER PASSWORD AND URL
    private final String USER = "root";
    private final String PASSWORD = "0628Cara*";
    private static final String URL = "jdbc:mysql://localhost:3306/securauditgroupe1?connectTimeout=3000&useSSL=false&allowPublicKeyRetrieval=true";
    //endregion
    protected DataAccess() {
        this.createConnection();
    }


    public Connection getConnection() {
        return connection;
    }
    private void createConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Ferme la connexion à la base de données
     * @throws SQLException
     */
    @Override
    public void close() throws SQLException {
        if(this.connection != null) {
            this.connection.close();
        }
    }
}
