package JDBC_MySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHandling {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/collage";
        String username = "root";
        String password = "ManojMysql@123";

        String withdraw = "UPDATE transaction SET balance = balance - ? WHERE acc_no = ?";
        String deposit = "UPDATE transaction SET balance = balance + ? WHERE acc_no = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver Not Found: " + e.getMessage());
        }

        try {

            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully.");

            con.setAutoCommit(false);

            try {
                PreparedStatement withdrawStatement = con.prepareStatement(withdraw);
                PreparedStatement depositStatement = con.prepareStatement(deposit);
                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "acc123");

                depositStatement.setDouble(1, 500.00);
                depositStatement.setString(2, "acc4564");

                int rowsAffectedwithdraw = withdrawStatement.executeUpdate();
                int rowsAffecteddeposit = depositStatement.executeUpdate();
                if (rowsAffectedwithdraw > 0 && rowsAffecteddeposit > 0) {
                    con.commit();
                    System.out.println("Transaction Successfully");
                } else {
                    // rollback depositStatement
                    con.rollback();
                    System.err.println("Transaction Failed");
                }

            } catch (SQLException e) {
                System.err.println("Transaction Failed: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Database Connection/Query Excecution Failed: " + e.getMessage());
        }
    }
}
