package com.earl.email2.accountfactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmailAccountPartition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String lastName;
	private String department;
	private long count;

	protected EmailAccountPartition() {

	}

	public EmailAccountPartition(String firstName, String lastName, String department, long count) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.count = count;
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "EmailAccountPartition [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", department=" + department + ", count=" + count + "]";
	}

}
