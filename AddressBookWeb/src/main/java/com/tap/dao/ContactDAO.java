package com.tap.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import com.tap.model.Contact;

public class ContactDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/addressbook";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Avi@143563";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Create a new contact
    public void create(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (firstName, lastName, email, phone, address, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());
            stmt.setString(5, contact.getAddress());
            stmt.setString(6, contact.getNotes());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        contact.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }


    // Read all contacts
    public List<Contact> readAll() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts ORDER BY firstName, lastName";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(mapResultSetToContact(rs));
            }
        }
        return contacts;
    }

    // Delete a contact
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    
    public Contact read(int id) throws Exception {
        String query = "SELECT * FROM contacts WHERE id = ?";
        try (Connection conn = ContactDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Contact(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("notes")
                    );
                }
            }
        }
        return null;
    }

    public boolean update(Contact contact) throws Exception {
        String query = "UPDATE contacts SET firstName = ?, lastName = ?, phone = ?, email = ?, address = ?, notes = ? WHERE id = ?";
        try (Connection conn = ContactDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getPhone());
            stmt.setString(4, contact.getEmail());
            stmt.setString(5, contact.getAddress());
            stmt.setString(6, contact.getNotes());
            stmt.setInt(7, contact.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    
    

    // Helper method to map ResultSet to Contact object
    private Contact mapResultSetToContact(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("id"));
        contact.setFirstName(rs.getString("firstName"));
        contact.setLastName(rs.getString("lastName"));
        contact.setEmail(rs.getString("email"));
        contact.setPhone(rs.getString("phone"));
        contact.setAddress(rs.getString("address"));
        contact.setNotes(rs.getString("notes"));
        return contact;
    }

    // Test connection
    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Database connection successful!");
            return true;
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return false;
        }
    }

    // Test method
    public static void main(String[] args) {
        ContactDAO dao = new ContactDAO();

        // Test connection
        if (dao.testConnection()) {
            try {
                // Test readAll
                List<Contact> contacts = dao.readAll();
                System.out.println("Found " + contacts.size() + " contacts:");
                for (Contact contact : contacts) {
                    System.out.println(contact);
                }
            } catch (SQLException e) {
                System.out.println("Error reading contacts:");
                e.printStackTrace();
            }
        }
    }
}
