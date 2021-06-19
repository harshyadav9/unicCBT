package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
	
	public String getExamCd() {
		return examCd;
	}

	public void setExamCd(String examCd) {
		this.examCd = examCd;
	}

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
	
	@Column(name = "ExamCd")
	private String examCd;

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
	
	@Override
	public String toString() {
		return "Student [registrationNo=" + registrationNo + ", password=" + password + ", examCd=" + examCd + "]";
	}
}
