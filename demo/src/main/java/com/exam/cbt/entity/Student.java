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
	private int id;

	public Student() {
		super();
	}

	@Column(name= "studentName")
	private String studentName;

	@Column(name = "password")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	
	 @Override public String toString() { return "Student [id=" + id +
	 ", studentName=" + studentName + ", password=" + password + "]"; }
	

}
