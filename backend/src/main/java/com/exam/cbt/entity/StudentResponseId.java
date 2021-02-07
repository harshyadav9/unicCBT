package com.exam.cbt.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class StudentResponseId implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int registrationNo;
	private int questionId;
	private int yearOfExam;
	
	public StudentResponseId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getYearOfExam() {
		return yearOfExam;
	}

	public void setYearOfExam(int yearOfExam) {
		this.yearOfExam = yearOfExam;
	}
	
	@Override
	public String toString() {
		return "StudentResponseId [registrationNo=" + registrationNo + ", questionId=" + questionId + ", yearOfExam="
				+ yearOfExam + "]";
	}

}
