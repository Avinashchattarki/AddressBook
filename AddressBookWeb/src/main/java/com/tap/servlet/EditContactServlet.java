package com.tap.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.tap.dao.ContactDAO;
import com.tap.model.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditContactServlet")
public class EditContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO = new ContactDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
            // Create Contact object from form data
            Contact contact = new Contact();
            contact.setId(Integer.parseInt(request.getParameter("id")));
            contact.setFirstName(request.getParameter("firstName"));
            contact.setLastName(request.getParameter("lastName"));
            contact.setEmail(request.getParameter("email"));
            contact.setPhone(request.getParameter("phone"));
            contact.setAddress(request.getParameter("address"));
            contact.setNotes(request.getParameter("notes"));
            
            boolean updated = contactDAO.update(contact);
            
            if (updated) {
                // Send success response with redirect URL
                out.print("{\"success\": true, \"redirectUrl\": \"editsuccess.html\"}");
            } else {
                // Send error response
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"success\": false, \"error\": \"Failed to update contact\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"success\": false, \"error\": \"" + e.getMessage() + "\"}");
        }
    }
}