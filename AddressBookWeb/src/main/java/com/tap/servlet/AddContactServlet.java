package com.tap.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.tap.dao.ContactDAO;
import com.tap.model.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddContactServlet", urlPatterns = "/add-contact")
public class AddContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO;

    public void init() {
        contactDAO = new ContactDAO();
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Get parameters
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String notes = request.getParameter("notes");

            // Validate required fields
            if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                response.setContentType("text/html");
                response.getWriter().write("Error: Required fields (First Name, Last Name, Email) cannot be empty");
                return;
            }

            // Create and save contact
            Contact contact = new Contact();
            contact.setFirstName(firstName.trim());
            contact.setLastName(lastName.trim());
            contact.setEmail(email.trim());
            contact.setPhone(phone != null ? phone.trim() : "");
            contact.setAddress(address != null ? address.trim() : "");
            contact.setNotes(notes != null ? notes.trim() : "");

            contactDAO.create(contact);
            
            // Redirect to success page
            response.sendRedirect("success.html");

        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}