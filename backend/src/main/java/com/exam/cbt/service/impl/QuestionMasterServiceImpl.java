package com.exam.cbt.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.QuestionMasterRepository;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.service.QuestionMasterService;

@Service
public class QuestionMasterServiceImpl implements QuestionMasterService {

	@Autowired
	private QuestionMasterRepository questionMasterRepository;

	@Override
	public HashMap<String, List<QuestionMaster>> getAllQuestions() {
		
		HashMap<String, List<QuestionMaster>> hm = new HashMap<String, List<QuestionMaster>>();
		List<QuestionMaster> questions = questionMasterRepository.findAll();
		hm.put("Questions", questions);
		return hm;
	}

}
