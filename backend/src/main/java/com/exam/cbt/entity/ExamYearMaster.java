package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ExamYearMaster")
public class ExamYearMaster {
	
	@EmbeddedId
	private ExamYearMasterId id;
	
	@Column(name = "ExamName")
	private String examName;
	
	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public ExamYearMasterId getId() {
		return id;
	}

	public void setId(ExamYearMasterId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ExamYearMaster [id=" + id + ", examName=" + examName + "]";
	}


}
