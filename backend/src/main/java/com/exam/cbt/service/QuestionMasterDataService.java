package com.exam.cbt.service;

import java.util.HashMap;
import java.util.List;

import com.exam.cbt.entity.QuestionMaster;

public interface QuestionMasterDataService {
	
	int uploadQuestionMaster(List<QuestionMaster> questions);
	
	HashMap<String,List<QuestionMaster>> getAllQuestions(int setNo, int year, String instCd, String examCd);
	
}
