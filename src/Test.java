import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Test {
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;databaseName=LearnDB;integratedSecurity=false;";

        try (Connection con = DriverManager.getConnection(connectionUrl, "sa", "CyanCloud");
             Statement stmt = con.createStatement()) {
            String SQL = "SELECT TOP 10 * FROM uDept;";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("did"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
