package JDBC_MySql;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseQueryExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/collage";
        String username = "root";
        String password = "ManojMysql@123";
        String folderPath = "C:\\Users\\HP\\Downloads\\"; 
        // String imagePath = "C:\\Users\\HP\\OneDrive\\Pictures\\Catagory\\Anime\\Itachi.png";

        // String select = "SELECT * FROM bank WHERE stu_name = ? and city = ?";
        // String insert = "INSERT INTO bank (stu_id, stu_name, mode_pyt, city) VALUES (?, ?, ?, ?)";
        // String imageInsert = "INSERT INTO imagetable (imagedata) VALUES (?)";
        String folderInsert = "select imagedata from imagetable where imageid = (?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver Not Found: " + e.getMessage());
        }

        try (Connection con = DriverManager.getConnection(url, username, password)) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Database Connected Successfully.");

            // Upload Image in Database
            // FileInputStream fileInputStream = new FileInputStream(imagePath);
            // byte[] imageData = new byte[fileInputStream.available()];
            // // Read the image data into the byte array
            // fileInputStream.read(imageData);

            // // Prepare statement for insertion
            // PreparedStatement insertStatement = con.prepareStatement(imageInsert);
            // insertStatement.setBytes(1, imageData);
            

            // Download Image from Database
            PreparedStatement selectStatement = con.prepareStatement(folderInsert);
            selectStatement.setInt(1, 1);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                byte[] imagedata = resultSet.getBytes("imagedata");
                String imageName = folderPath+"Itachi.jpg";
                FileOutputStream fileoutputStream = new FileOutputStream(imageName);
                fileoutputStream.write(imagedata);
                System.out.println("Image downloaded successfully.");

            } else {
                System.out.println("Image not found!");
            }

            // System.out.println("Enter student id: ");
            // int id = sc.nextInt();
            // sc.nextLine(); // Consume the newline left-over from nextInt()
            // insertStatement.setInt(1, id);

            // System.out.println("Enter student name: ");
            // String name = sc.nextLine();
            // insertStatement.setString(2, name);

            // System.out.println("Enter mode of payment: ");
            // String mode = sc.nextLine();
            // insertStatement.setString(3, mode);

            // System.out.println("Enter city: ");
            // String city = sc.nextLine();
            // insertStatement.setString(4, city);

            // Execute the insert operation
            // int rowsAffected = insertStatement.executeUpdate();
            // if (rowsAffected > 0) {
            //     System.out.println("Insert Successful.");
            //     System.out.println("No. of Rows affected: " + rowsAffected);
            // } else {
            //     System.out.println("Record not inserted.");
            // }

            // Optional: Uncomment this section if you want to retrieve data based on
            // conditions
            /*
             * PreparedStatement selectStatement = con.prepareStatement(select);
             * selectStatement.setString(1, name);
             * selectStatement.setString(2, city);
             * 
             * ResultSet resultSet = selectStatement.executeQuery();
             * while (resultSet.next()) {
             * int stu_id = resultSet.getInt("stu_id");
             * String stu_name = resultSet.getString("stu_name");
             * String mode_pyt = resultSet.getString("mode_pyt");
             * String city_res = resultSet.getString("city");
             * System.out.println("Student ID: " + stu_id +
             * "\nStudent Name: " + stu_name +
             * "\nMode of Payment: " + mode_pyt +
             * "\nCity: " + city_res);
             * System.out.println("--------------------------------------------------------"
             * );
             * }
             * resultSet.close();
             * selectStatement.close();
             */

            // fileInputStream.close();
            sc.close();
            // insertStatement.close();
            con.close();
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
