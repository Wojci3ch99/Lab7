package pl.lublin.wsei.java.cwiczenia.test;

import pl.lublin.wsei.java.cwiczenia.MyDB;

import java.sql.Connection;
import java.sql.SQLException;

public class TestMyDB {
    public static void main(String[] args) {
        MyDB db = new MyDB("localhost", 3306, "mydb" );
        db.setUser("root");
        db.setPassword("Bakieras1999");
        Connection conn = db.getConnection();
        if (conn != null)
            System.out.println("Połączenie z baza nwiązane");
        db.closeConnection();
        System.out.println("Polączenie z bazą zakończone");
    }
}
