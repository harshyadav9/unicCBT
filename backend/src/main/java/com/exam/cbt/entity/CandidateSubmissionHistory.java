package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CandidateSubmissionHistory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateSubmissionHistory {
	
	@Id
	@Column(name= "MessageId") 
	private String messageId;
	
    @Column(name= "ExamCd") 
    private String examCd;
	 
	@Column(name = "InstCd")
	private String instCd;
	
	@Column(name = "Year")
	private int year;
	
	@Column(name = "QuestionNo")
	private int questionNo;

	@Column(name = "Answer")
	private String answer;
	
	@Column(name = "RegistrationNo")
	private int registrationNo;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getExamCd() {
		return examCd;
	}

	public void setExamCd(String examCd) {
		this.examCd = examCd;
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

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	@Override
	public String toString() {
		return "CandidateSubmissionHistory [messageId=" + messageId + ", examCd=" + examCd + ", instCd=" + instCd
				+ ", year=" + year + ", questionNo=" + questionNo + ", answer=" + answer + ", registrationNo="
				+ registrationNo + "]";
	}
	
}
