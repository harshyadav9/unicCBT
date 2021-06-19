package com.exam.cbt.service;

import java.util.HashMap;
import java.util.List;

import com.exam.cbt.entity.QuestionMaster;

public interface QuestionMasterService {
	
    HashMap<String,List<QuestionMaster>> getAllQuestions();
}
