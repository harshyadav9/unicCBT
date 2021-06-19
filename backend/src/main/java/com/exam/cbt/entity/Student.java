package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
//@NamedNativeQuery(name = "Student.findById", query = "select * from student where id = ?1", resultClass = Student.class)
public class Student {
	
	@Id
	private int registrationNo;

	public Student() {
		super();
	}

	/*
	 * @Column(name= "studentName") private String studentName;
	 */

	@Column(name = "password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	/*
	 * public String getStudentName() { return studentName; }
	 * 
	 * public void setStudentName(String studentName) { this.studentName =
	 * studentName; }
	 */
	
	 @Override public String toString() { return "Student [registrationNo=" + registrationNo +
	 ", password=" + password + "]"; }
	

}
