package br.mack.ps2.persistencia;
import java.sql.*;

public class MySQLConnection {
    String url = "jdbc:mysql://192.168.99.100:32777/projeto";
    String usuario = "root";
    String senha = "root";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection(url, usuario, senha);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
