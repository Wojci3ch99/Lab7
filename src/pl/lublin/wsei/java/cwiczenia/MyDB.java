package pl.lublin.wsei.java.cwiczenia;

import java.sql.*;
import java.util.Properties;

public class MyDB {

    String host;
    String dbName;
    Number port;
    private Connection conn = null;

    private String user;
    private String password;


    private Statement statement = null;

    public MyDB(String host, Number port, String dbName ){
        this.host = host;
        this.dbName = dbName;
        this.port = port;

    }
    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void connect(){
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connectionProps.put("serwerTimezone", "Europe/Warsaw");

        String jdbcString = "jdbc:mysql://" + host + ":" + port + "/" +dbName;

        try {
            conn = DriverManager.getConnection(jdbcString, connectionProps);
            statement = conn.createStatement();
        }
        catch (SQLException e){
            System.out.println("Błąd połączenia do bazy: "+jdbcString);
            System.out.println("Komunikat błędu: "+e.getMessage());
            conn = null;
        }
        System.out.println("Connected to database "+dbName);
    }
    public Connection getConnection() {
        if (conn == null)
            connect();
        return conn;
    }
    public void closeConnection() {
        if (conn != null)
            try {
                conn.close();
            }
        catch(SQLException e) {
            System.out.println("Błąd przy zamykaniu połączenia bazodanowego: "+ e.getMessage());
        }
        conn = null;
    }
    public ResultSet selectData(String selectStatement) {
        if ((conn != null ) && (statement != null));
        try {
            return statement.executeQuery(selectStatement);
        }
        catch (SQLException e){
            System.out.println("Błąd realizacji zapytania: "+ selectStatement+", "+e.getMessage());
        }
        return null;
    }
}
