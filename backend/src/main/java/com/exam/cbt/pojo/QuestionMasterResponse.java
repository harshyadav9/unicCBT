package com.exam.cbt.pojo;

import java.util.HashMap;
import java.util.List;

import com.exam.cbt.entity.QuestionMaster;

public class QuestionMasterResponse {
	
	private HashMap<String,String> responseCode;
	
	private HashMap<String,List<QuestionMaster>> questionList;

	public HashMap<String, String> getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(HashMap<String, String> responseCode) {
		this.responseCode = responseCode;
	}

	public HashMap<String, List<QuestionMaster>> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(HashMap<String, List<QuestionMaster>> questionList) {
		this.questionList = questionList;
	}

}
