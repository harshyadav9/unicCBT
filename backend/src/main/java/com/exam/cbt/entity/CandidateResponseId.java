package com.exam.cbt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CandidateResponseId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "RegistrationNo")
	private int registrationNo;
	
	@Column(name = "Year")
	private int year;
	
	@Column(name = "QuestionNo")
	private int questionNo;
	
	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	
	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "CandidateResponseId [registrationNo=" + registrationNo + ", year=" + year + "]";
	}

}
