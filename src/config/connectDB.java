package config;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class connectDB {
    private Connection connect;

    // Constructor to connect to the database
    public connectDB() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/basketball", "root", "");
            System.out.println("Connected to the database successfully!");
        } catch (SQLException ex) {
            System.out.println("Can't connect to database: " + ex.getMessage());
        }
    }

    // Get the connection object
    public Connection getConnection() {
        return connect;
    }

    // Function to insert data securely
    public int insertData(String sql, Object... params) {
        int result = 0;
        try (PreparedStatement pst = connect.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            result = pst.executeUpdate();
            System.out.println("Inserted Successfully!");
        } catch (SQLException ex) {
            System.out.println("Connection Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    // Function to retrieve data
    public ResultSet getData(String sql) throws SQLException {
        Statement stmt = connect.createStatement();
        return stmt.executeQuery(sql);
    }

    // ✅ Function to retrieve a user by ID (including images)
    public ResultSet getUserById(String userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, userId);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Function to check if a field exists (for duplicates)
    public boolean fieldExists(String tableName, String columnName, String value) {
        String sql = "SELECT 1 FROM " + tableName + " WHERE " + columnName + " = ? LIMIT 1";
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, value);
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Function to validate login
    public String validateLogin(String username, String password) throws SQLException {
        String query = "SELECT usertype FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("usertype");
            }
        }
        return null;
    }

    // Function to close the database connection
    public void closeConnection() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException ex) {
            System.out.println("Error closing connection: " + ex.getMessage());
        }
    }

    // Function to display records in a JTable
    public void displayData(JTable studentTable) {
        try (ResultSet rs = getData("SELECT * FROM users")) {
            studentTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Function to update data securely
    public int updateData(String sql, Object... params) {
        int rowsUpdated = 0;
        try (PreparedStatement pst = connect.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }
            rowsUpdated = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return rowsUpdated;
    }

    // Function to check for duplicates excluding the current user
    public boolean duplicateCheckExcludingCurrent(String tableName, String columnName, String value, String idColumn, String currentId) {
        String sql = "SELECT 1 FROM " + tableName + " WHERE " + columnName + " = ? AND " + idColumn + " != ? LIMIT 1";
        try (PreparedStatement pstmt = connect.prepareStatement(sql)) {
            pstmt.setString(1, value);
            pstmt.setString(2, currentId);
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
