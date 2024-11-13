package JDBC_Postgress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {

        String sql = "select * from employee;";
        String url = "jdbc:postgresql://localhost:5432/firstdb";
        String username = "postgres";
        String password = "Manojpostgres@123";
        
        Connection con =  DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        // Stores Table datatype
        ResultSet rs = st.executeQuery(sql);
        // Column pointing at  the first row(index row) therefore next
        rs.next();
        for(int i=1; i<4; i++){
            String result = rs.getString(i);
            System.out.println(result);
        }

        // close the connection
        con.close();
    }
}
