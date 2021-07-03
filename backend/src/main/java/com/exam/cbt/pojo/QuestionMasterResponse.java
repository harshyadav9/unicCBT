package com.exam.cbt.pojo;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.exam.cbt.entity.QuestionMaster;

public class QuestionMasterResponse {
	
	private String ExamCd;
	
	private String instituteName;
	
	private String candidateName;
	
	private int durationHr;
	
	private int durationMin;
	
	private Date dateOfExam;
	
	private String photo;
	
	private int registrationNo;
	
	public int getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(int registrationNo) {
		this.registrationNo = registrationNo;
	}

	private HashMap<String,List<QuestionMaster>> questionList;

	public HashMap<String, List<QuestionMaster>> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(HashMap<String, List<QuestionMaster>> questionList) {
		this.questionList = questionList;
	}
	
	public String getExamCd() {
		return ExamCd;
	}

	public void setExamCd(String examCd) {
		ExamCd = examCd;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getDurationHr() {
		return durationHr;
	}

	public void setDurationHr(int durationHr) {
		this.durationHr = durationHr;
	}

	public int getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(int durationMin) {
		this.durationMin = durationMin;
	}

	public Date getDateOfExam() {
		return dateOfExam;
	}

	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	@Override
	public String toString() {
		return "QuestionMasterResponse [ExamCd=" + ExamCd + ", instituteName=" + instituteName + ", candidateName="
				+ candidateName + ", durationHr=" + durationHr + ", durationMin=" + durationMin + ", dateOfExam="
				+ dateOfExam + ", photo=" + photo + ", registrationNo=" + registrationNo + ", questionList="
				+ questionList + "]";
	}

}
