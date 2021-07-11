package com.exam.cbt.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuestionMasterId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name= "InstCd") 
	private String instCd;
	
	@Column(name= "ExamCd") 
	private String examCd;
	
    @Column(name= "QuestionNo") 
	private int questionNo;
    
    @Column(name= "Year") 
	private int year;
	
	public String getInstCd() {
		return instCd;
	}
	public void setInstCd(String instCd) {
		this.instCd = instCd;
	}
	public String getExamCd() {
		return examCd;
	}
	public void setExamCd(String examCd) {
		this.examCd = examCd;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(examCd, instCd, questionNo, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionMasterId other = (QuestionMasterId) obj;
		return Objects.equals(examCd, other.examCd) && Objects.equals(instCd, other.instCd)
				&& questionNo == other.questionNo && year == other.year;
	}
	@Override
	public String toString() {
		return "QuestionMasterId [instCd=" + instCd + ", examCd=" + examCd + ", questionNo=" + questionNo + ", year=" + year
				+ "]";
	}

}
