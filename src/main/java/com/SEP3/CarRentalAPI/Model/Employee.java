package com.SEP3.CarRentalAPI.Model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@SQLDelete(sql = "UPDATE employee SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Employee
{

	private long id;
	private String name;
	private String email;
	private String password;
	private int clearanceLevel;

	private boolean deleted = Boolean.FALSE;
	
	public Employee() {
		
	}
	
	public Employee(String name, String password, String email, int clearanceLevel) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.clearanceLevel = clearanceLevel;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String firstName) {
		this.name = firstName;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String emailId) {
		this.email = emailId;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "clearance_level", nullable = false)
	public int getClearanceLevel() {
		return clearanceLevel;
	}
	public void setClearanceLevel(int clearanceLevel) {
		this.clearanceLevel = clearanceLevel;
	}

	@Column(name = "deleted")
	public boolean isDeleted()
	{
		return deleted;
	}
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + name + ", password=" + password + ", emailId=" + email
				+ "]";
	}
	
}
