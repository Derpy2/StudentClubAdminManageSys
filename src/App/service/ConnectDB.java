package App.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    protected Connection conn = null;
    private final String DB_SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;DatabaseName=Club";
    private final String DB_USER = "sa";
    private final String DB_PASSWORD = "CyanCloud";

    public ConnectDB(){
        try{
            Class.forName(DB_SERVER);
        }
        catch (ClassNotFoundException e){
            System.out.println('0' + e.getMessage());
        }
    }

    protected Connection getConnection(){
        try{
            if(conn == null || conn.isClosed()){
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            }
        }
        catch (SQLException e){
            System.out.println("1" + e.getMessage());
        }
        return conn;
    }

    protected void closeConnection(){
        try{
            if(conn != null || !conn.isClosed()){
                conn.close();
            }

        }catch (SQLException e){
            System.out.println("2" + e.getMessage());
        }
    }
}
