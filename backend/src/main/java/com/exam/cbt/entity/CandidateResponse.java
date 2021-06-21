package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CandidateResponse")
public class CandidateResponse {

	@EmbeddedId
	private CandidateResponseId id;
	
	@Column(name = "Answer")
	private String answer;
	
	@Column(name = "InstCd")
	private String instCd;
	
	@Column(name = "ExamCd")
	private String examCd;
	
	public CandidateResponseId getId() {
		return id;
	}

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

	public void setId(CandidateResponseId id) {
		this.id = id;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "CandidateResponse [id=" + id + ", answer=" + answer + ", instCd=" + instCd + ", examCd=" + examCd + "]";
	}

}
