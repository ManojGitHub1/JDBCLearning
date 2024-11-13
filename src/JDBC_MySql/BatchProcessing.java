package JDBC_MySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchProcessing {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/collage";
        String username = "root";
        String password = "ManojMysql@123";

        String insertQuery = "INSERT INTO bank (stu_id, stu_name, mode_pyt, city) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver Not Found: " + e.getMessage());
        }

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database Connected Successfully.");

            // Disable auto-commit mode
            con.setAutoCommit(false);

            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {

                // Adding data in batch
                preparedStatement.setInt(1, 101);
                preparedStatement.setString(2, "Alice");
                preparedStatement.setString(3, "Credit");
                preparedStatement.setString(4, "New York");
                preparedStatement.addBatch();

                preparedStatement.setInt(1, 102);
                preparedStatement.setString(2, "Bob");
                preparedStatement.setString(3, "Debit");
                preparedStatement.setString(4, "Los Angeles");
                preparedStatement.addBatch();

                preparedStatement.setInt(1, 103);
                preparedStatement.setString(2, "Charlie");
                preparedStatement.setString(3, "Credit");
                preparedStatement.setString(4, "Chicago");
                preparedStatement.addBatch();

                preparedStatement.setInt(1, 104);
                preparedStatement.setString(2, "Daisy");
                preparedStatement.setString(3, "Debit");
                preparedStatement.setString(4, "Houston");
                preparedStatement.addBatch();

                // Execute batch
                int[] rowsAffected = preparedStatement.executeBatch();

                // Commit transaction
                con.commit();
                System.out.println("Batch Insert Successful. Rows affected: " + rowsAffected.length);

            } catch (SQLException e) {
                // Rollback transaction if there is any error
                con.rollback();
                System.err.println("Batch Processing Failed, Transaction Rolled Back: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
        }
    }
}
