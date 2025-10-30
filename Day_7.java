import java.sql.*;

public class Day_7 {
    private Connection conn;


    public Day_7() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "password";

        try (Connection tempConn = DriverManager.getConnection(url, user, password);
             Statement stmt = tempConn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE mydb");
        } catch (SQLException e) {

        }


        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/mydb", user, password);


        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "email VARCHAR(50) NOT NULL UNIQUE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
        }
    }


    public void addUser(String name, String email) throws SQLException {
        String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
        }
    }


    public void viewUser(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Email: " + rs.getString("email"));
                } else {
                    System.out.println("User not found.");
                }
            }
        }
    }


    public void updateUser(int id, String name, String email) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("User updated successfully!");
            else System.out.println("User not found.");
        }
    }


    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) System.out.println("User deleted successfully!");
            else System.out.println("User not found.");
        }
    }


    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}
