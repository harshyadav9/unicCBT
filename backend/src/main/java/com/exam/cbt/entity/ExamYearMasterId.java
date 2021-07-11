package com.exam.cbt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamYearMasterId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "InstCd")
	private String instCd;
	
	@Column(name = "ExamCd")
	private String examCd;
	
	@Column(name = "Year")
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "ExamMasterId [instCd=" + instCd + ", examCd=" + examCd + ", year=" + year + "]";
	}
	
}
