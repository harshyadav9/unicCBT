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
	
	@Id
	@Column(name= "registrationNo") 
	private int registrationNo;
	
    @Column(name= "studentName") 
    private String studentName;
	 
	@Column(name = "password")
	private String password;
	
	@Column(name = "ExamCd")
	private String examCd;
	
	@Column(name = "InstCd")
	private String instCd;

	@Column(name = "Year")
	private int year;
	
	@Column(name = "DOB")
	private String dob;
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getInstCd() {
		return instCd;
	}

	public void setInstCd(String instCd) {
		this.instCd = instCd;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

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

	public String getExamCd() {
		return examCd;
	}

	public void setExamCd(String examCd) {
		this.examCd = examCd;
	}
	
	@Override
	public String toString() {
		return "Student [registrationNo=" + registrationNo + ", studentName=" + studentName + ", password=" + password
				+ ", examCd=" + examCd + ", instCd=" + instCd + ", year=" + year + ", dob=" + dob + "]";
	}
	
}
