package com.exam.cbt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.QuestionMasterRepository;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.service.UploadQuestionDataService;

@Service
public class UploadQuestionDataServiceImpl implements UploadQuestionDataService {

	Logger log = LoggerFactory.getLogger(UploadQuestionDataServiceImpl.class);

	@Autowired
	private QuestionMasterRepository questionMasterRepository;

	@Override
	public void uploadQuestions(List<QuestionMaster> questions) {
		questionMasterRepository.saveAll(questions);
	}

}
