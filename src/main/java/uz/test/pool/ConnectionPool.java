package uz.test.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();
    private final BlockingQueue<Connection> free = new LinkedBlockingQueue<>(10);
    private static final String URL = "jdbc:postgresql://localhost:5432/furortest";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root123";

    private ConnectionPool() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                free.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            return free.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean releaseConnection(Connection connection) {

        try {
            free.put(connection);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void destroyPool() {
        for (int i = 0; i < 20; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
