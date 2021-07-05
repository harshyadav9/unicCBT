package com.exam.cbt.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CandidateMaster")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateMaster {
	
	@Id
	@Column(name= "RegistrationNo") 
	private int registrationNo;
	
    @Column(name= "Name") 
    private String name;
	 
	@Column(name = "Password")
	private String password;
	
	@Column(name = "ExamCd")
	private String examCd;
	
	@Column(name = "InstCd")
	private String instCd;

	@Column(name = "Year")
	private int year;
	
	@Column(name = "Dob")
	private Date dob;
	
	@Column(name = "SetNo")
	private int setNo;
	
	public int getSetNo() {
		return setNo;
	}

	public void setSetNo(int setNo) {
		this.setNo = setNo;
	}

	@Column(name = "Photo")
	private String photo;
	
	@Column(name = "MobileNum")
	private int mobileNum;
	
	@Column(name = "EmailId")
	private String emailId;
	
	public int getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(int mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CandidateMaster [registrationNo=" + registrationNo + ", name=" + name + ", password=" + password
				+ ", examCd=" + examCd + ", instCd=" + instCd + ", year=" + year + ", dob=" + dob + ", setNo=" + setNo
				+ ", photo=" + photo + ", mobileNum=" + mobileNum + ", emailId=" + emailId + "]";
	}

}
