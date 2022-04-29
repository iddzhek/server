package sql;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDB {

    private static String url = "jdbc:postgresql://localhost:5433/postgres";
    private static String username = "postgres";
    private static String password = "admin";

    public static ArrayList<Customer> select() {

        ArrayList<Customer> products = new ArrayList<Customer>();
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String firstName  = resultSet.getString(2);
                    String lastName  = resultSet.getString(3);
                    int contactNumber = resultSet.getInt(4);
                    String email  = resultSet.getString(5);
                    String gender  = resultSet.getString(6);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return products;
    }
    public static Customer selectOne(int id) {

        Customer customer = null;
        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "SELECT * FROM products WHERE id=?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){

                        int custId = resultSet.getInt(1);
                        String firstName  = resultSet.getString(2);
                        String lastName  = resultSet.getString(3);
                        int contactNumber = resultSet.getInt(4);
                        String email  = resultSet.getString(5);
                        String gender  = resultSet.getString(6);
                        customer = new Customer(custId, firstName, lastName, contactNumber, email, gender);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return customer;
    }
    public static int insert(Customer customer) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO products (firstName, lastName, contactNumber, email, data, gender) Values (?, ?, ?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, customer.getFirstName());
                    preparedStatement.setString(2, customer.getLastName());
                    preparedStatement.setInt(3, customer.getContactNumber());
                    preparedStatement.setString(4, customer.getEmail());
                    preparedStatement.setString(5, customer.getGender());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(Customer customer) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "UPDATE products SET firstName = ?, lastName = ?, contactNumber = ?, email = ?, data = ?, gender = ?, WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, customer.getFirstName());
                    preparedStatement.setString(2, customer.getLastName());
                    preparedStatement.setInt(3, customer.getContactNumber());
                    preparedStatement.setString(4, customer.getEmail());
                    preparedStatement.setString(5, customer.getGender());

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static int delete(int id) {

        try{
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "DELETE FROM products WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, id);

                    return  preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return 0;
    }

}
