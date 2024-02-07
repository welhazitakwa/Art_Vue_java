package utils;

import java.sql.*;

public class MyDaBase {

    final String URL = "jdbc:mysql://localhost:3306/artVue" ;
    final String USER= "root";
    final String PASSWORD="";
    Connection connection ;
    public MyDaBase (){
        try {
            connection = DriverManager.getConnection(URL,USER,PASSWORD) ;
            System.out.println("Connected to artVue dataBase !! ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
}
