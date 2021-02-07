package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "StudentResponse")
public class StudentResponse {

	@EmbeddedId
	private StudentResponseId id;
	
	@Column(name = "selectedAnswer")
	private String selectedAnswer;
	
	public StudentResponse() {
		super();
	}

	public StudentResponseId getId() {
		return id;
	}

	public void setId(StudentResponseId id) {
		this.id = id;
	}
	
	public String getSelectedAnswer() {
		return selectedAnswer;
	}

	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	
	@Override
	public String toString() {
		return "StudentResponse [id=" + id + ", selectedAnswer=" + selectedAnswer + "]";
	}

}
