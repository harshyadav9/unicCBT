package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.QuestionMaster;

public interface UploadQuestionDataService {
	
	void uploadQuestions(List<QuestionMaster> questions);
	
}
