package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QuestionMaster")
public class QuestionMaster {

	@Id
	private int questionID;

	public QuestionMaster() {
		super();
	}

	@Column(name = "InstCd")
	private String instCd;
	
	@Column(name = "examCd")
	private String examCd;

	@Column(name = "questionNo")
	private Integer questionNo;
	
	@Column(name = "Question")
	private String question;
	
	@Column(name = "QuestionDescription")
	private String questionDescription;
	
	@Column(name = "QuestionHint")
	private String questionHint;
	
	@Column(name = "Single")
	private String single;
	
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
	
	@Column(name = "Option5")
	private String option5;
	
	@Column(name = "CorrectAns")
	private String correctAnswer;
	
	@Column(name = "CorrectAnsWeightage")
	private Float correctAnsWeightage;
	
	@Column(name = "WrongAnsWeightage")
	private Float wrongAnsWeightage;
	
	@Column(name = "UnattemptedAnsWeightage")
	private Float unattemptedAnsWeightage;
	
	@Column(name = "PartialAnsWeightage")
	private Float partialAnsWeightage;
	
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

	public Float getPartialAnsWeightage() {
		return partialAnsWeightage;
	}

	public void setPartialAnsWeightage(Float partialAnsWeightage) {
		this.partialAnsWeightage = partialAnsWeightage;
	}
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
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

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getQuestionHint() {
		return questionHint;
	}

	public void setQuestionHint(String questionHint) {
		this.questionHint = questionHint;
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
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

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	@Override
	public String toString() {
		return "QuestionMaster [questionID=" + questionID + ", instCd=" + instCd + ", examCd=" + examCd
				+ ", questionNo=" + questionNo + ", question=" + question + ", questionDescription="
				+ questionDescription + ", questionHint=" + questionHint + ", single=" + single + ", multiple="
				+ multiple + ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4="
				+ option4 + ", option5=" + option5 + "]";
	}

}
