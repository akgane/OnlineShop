package kz.aitu.onlineshopspring.data;

import kz.aitu.onlineshopspring.data.interfaces.DBInterface;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;


@Component
public class PostgresDB implements DBInterface {

    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/OnlineShop";
        try {
            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "0000");

            return con;
        } catch (Exception e) {
            System.out.println("failed to connect to postgres: " + e.getMessage());

            return null;
        }
    }
}
