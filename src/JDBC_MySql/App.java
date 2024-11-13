package JDBC_MySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

// 1. Import necessary classes from java.sql package
// import java.sql.*;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {

        // Database URL
        String url = "jdbc:mysql://localhost:3306/collage";
        // Database credentials
        String username = "root";
        String password = "ManojMysql@123";

        // Query
        String select = "select * from bank;";
        // String insert = "INSERT INTO bank (stu_id, stu_name, mode_pyt, city) VALUES (3, 'Manthan', 'UPI', 'COEP');";
        // String delete = "DELETE FROM bank WHERE stu_id = 3;";
        // String update = "UPDATE bank SET mode_pyt = 'UPI/Net Banking' WHERE stu_id = 2;";

        // 2. Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver Not Found: " + e.getMessage());
        }

        // 3. Establish connection
        try {

            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully.");

            // 4. Statement Interface
            Statement st = con.createStatement();

            // 5. ResultSet Interface to hold the result of the query
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                int stu_id = rs.getInt("stu_id");
                String stu_name = rs.getString("stu_name");
                String mode_pyt = rs.getString("mode_pyt");
                String city = rs.getString("city");
                System.out.println("Student ID: " + stu_id + "\nStudent Name: " + stu_name + "\nMode of Payment: " + mode_pyt + "\nCity: " + city);
                System.out.println("--------------------------------------------------------");
            }

            // int rowsAffected = st.executeUpdate(update);
            // if (rowsAffected > 0) {
            //     System.out.println("Insert/Delete/Update Successful.");
            //     System.out.println("No. of Rows affected: " + rowsAffected);
            // } else {
            //     System.out.println("Record not inserted.");
            // }

            // close the connection
            rs.close();
            st.close();
            con.close();
            System.out.println("Connection Closed Successfully");
        } catch (SQLException e) {
            System.err.println("Database Connection/Query Excecution Failed: " + e.getMessage());
        }
    }
}
