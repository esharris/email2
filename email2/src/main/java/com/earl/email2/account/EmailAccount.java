package com.earl.email2.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmailAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String lastName;
	private String department;
	private String password;

	@Column(unique = true)
	private String email;

	private int mailboxCapacity = 500;
	private String alternateEmail;

	protected EmailAccount() {

	}

	public EmailAccount(String firstName, String lastName, String department, String password, String email,
			int mailboxCapacity, String alternateEmail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.password = password;
		this.email = email;
		this.mailboxCapacity = mailboxCapacity;
		this.alternateEmail = alternateEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMailboxCapacity() {
		return mailboxCapacity;
	}

	public void setMailboxCapacity(int mailboxCapacity) {
		this.mailboxCapacity = mailboxCapacity;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String showInfo() {
		return "DISPLAY NAME: " + firstName + " " + lastName + "\nCOMPANY EMAIL: " + email + "\nMAILBOX CAPACITY: "
				+ mailboxCapacity + "mb";
	}

	@Override
	public String toString() {
		return "EmailAccount [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department="
				+ department + ", password=" + password + ", email=" + email + ", mailboxCapacity=" + mailboxCapacity
				+ ", alternateEmaail=" + alternateEmail + "]";
	}

}
