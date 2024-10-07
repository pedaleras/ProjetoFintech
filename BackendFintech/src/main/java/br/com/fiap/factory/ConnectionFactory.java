package br.com.fiap.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/FREEPDB1";
    private static final String USUARIO = "sys";
    private static final String SENHA = "123";
    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USUARIO);
        props.setProperty("password", SENHA);
        props.setProperty("internal_logon", "SYSDBA");  // Define o SYSDBA

        return DriverManager.getConnection(URL, props);
    }
}