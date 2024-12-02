package com.tap.model;

public class Contact {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String notes;
// Default constructor
public Contact() {
}
// Constructor with all fields
public Contact(int id, String firstName, String lastName, String email,
String phone, String address, String notes) {
this.id = id;
this.firstName = firstName;
this.lastName = lastName;
this.email = email;
this.phone = phone;
this.address = address;
this.notes = notes;
}
// Getters and Setters
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getFirstName() {
return firstName;
}
public void setFirstName(String firstName) {
this.firstName = firstName;
}
public String getLastName() {
return lastName;
}
public void setLastName(String lastName) {
this.lastName = lastName;
}
public String getEmail() {
return email;
}
public void setEmail(String email) {
this.email = email;
}
public String getPhone() {
return phone;
}
public void setPhone(String phone) {
this.phone = phone;
}
public String getAddress() {
return address;
}
public void setAddress(String address) {
this.address = address;
}
public String getNotes() {
return notes;
}
public void setNotes(String notes) {
this.notes = notes;
}
@Override
public String toString() {
return "Contact{" +
"id=" + id +
", firstName='" + firstName + '\'' +
", lastName='" + lastName + '\'' +
", email='" + email + '\'' +
", phone='" + phone + '\'' +
", address='" + address + '\'' +
", notes='" + notes + '\'' +
'}';
}
}