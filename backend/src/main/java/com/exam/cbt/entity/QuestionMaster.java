package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionMaster")
public class QuestionMaster {

	@EmbeddedId
	private QuestionMasterId id;
	
	@Column(name = "Question")
	private String question;
	
	@Column(name = "Multiple")
	private String multiple;
	
	@Column(name = "Option1")
	private String option1;
	
	@Column(name = "Option2")
	private String option2;
	
	@Column(name = "Option3")
	private String option3;
	
	@Column(name = "Option4")
	private String option4;
	
	@Column(name = "CorrectAns")
	private String correctAnswer;
	
	@Column(name = "CorrectAnsWeightage")
	private Float correctAnsWeightage;
	
	@Column(name = "WrongAnsWeightage")
	private Float wrongAnsWeightage;
	
	@Column(name = "UnattemptedAnsWeightage")
	private Float unattemptedAnsWeightage;
	
	public QuestionMasterId getId() {
		return id;
	}

	public void setId(QuestionMasterId id) {
		this.id = id;
	}
	
	public Float getCorrectAnsWeightage() {
		return correctAnsWeightage;
	}

	public void setCorrectAnsWeightage(Float correctAnsWeightage) {
		this.correctAnsWeightage = correctAnsWeightage;
	}

	public Float getWrongAnsWeightage() {
		return wrongAnsWeightage;
	}

	public void setWrongAnsWeightage(Float wrongAnsWeightage) {
		this.wrongAnsWeightage = wrongAnsWeightage;
	}

	public Float getUnattemptedAnsWeightage() {
		return unattemptedAnsWeightage;
	}

	public void setUnattemptedAnsWeightage(Float unattemptedAnsWeightage) {
		this.unattemptedAnsWeightage = unattemptedAnsWeightage;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	@Override
	public String toString() {
		return "QuestionMaster [id=" + id + ", question=" + question + ", multiple=" + multiple + ", option1=" + option1 + ", option2="
				+ option2 + ", option3=" + option3 + ", option4=" + option4 + ", correctAnswer=" + correctAnswer
				+ ", correctAnsWeightage=" + correctAnsWeightage + ", wrongAnsWeightage=" + wrongAnsWeightage
				+ ", unattemptedAnsWeightage=" + unattemptedAnsWeightage + "]";
	}

}
