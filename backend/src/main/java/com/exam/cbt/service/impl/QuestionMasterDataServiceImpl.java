package com.exam.cbt.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.QuestionMasterRepository;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.service.QuestionMasterDataService;

@Service
public class QuestionMasterDataServiceImpl implements QuestionMasterDataService {

	Logger log = LoggerFactory.getLogger(QuestionMasterDataServiceImpl.class);

	@Autowired
	private QuestionMasterRepository questionMasterRepository;

	@Override
	public int uploadQuestionMaster(List<QuestionMaster> questionMasterDataList) {
		
		questionMasterDataList.forEach(question -> {
			QuestionMaster questionMaster = null;

			if(questionMasterRepository.existsById(question.getId())) {
				questionMasterRepository.save(question);
			}
			else {
				questionMaster = question;
				questionMasterRepository.save(questionMaster);
			}
		   
		});
		
		questionMasterRepository.saveAll(questionMasterDataList);
		return questionMasterDataList.size();
	}
	
	@Override
	public HashMap<String, List<QuestionMaster>> getAllQuestions() {

		HashMap<String, List<QuestionMaster>> hm = new HashMap<String, List<QuestionMaster>>();
		List<QuestionMaster> questions = questionMasterRepository.findAll();
		hm.put("Questions", questions);
		return hm;
	}

}
