package com.exam.cbt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.QuestionMasterRepository;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.entity.QuestionMasterId;
import com.exam.cbt.service.QuestionMasterDataService;

@Service
public class QuestionMasterDataServiceImpl implements QuestionMasterDataService {

	Logger log = LoggerFactory.getLogger(QuestionMasterDataServiceImpl.class);

	@Autowired
	private QuestionMasterRepository questionMasterRepository;

	@Override
	public int uploadQuestionMaster(List<QuestionMaster> questionMasterDataList) {
		for (QuestionMaster q:questionMasterDataList) {
			if (q.getSetNo()!= 101 || q.getSetNo()!= 102 || q.getSetNo()!= 103 || q.getSetNo()!= 104){
				System.out.println("Issue");
			}
		}
		questionMasterRepository.saveAll(questionMasterDataList);
		QuestionMasterId id = new QuestionMasterId();
		id.setExamCd("");
		id.setInstCd("");
		id.setQuestionNo(0);
		id.setYear(0);
		Optional<QuestionMaster> q = questionMasterRepository.findById(id);
		if(q.isPresent()) {
			questionMasterRepository.deleteById(id);
			
		}
		return questionMasterDataList.size();
	}
	
	@Override
	public HashMap<String, List<QuestionMaster>> getAllQuestions(int setNo, int year, String instCd, String examCd) {

		HashMap<String, List<QuestionMaster>> hm = new HashMap<String, List<QuestionMaster>>();
		Iterable<QuestionMaster> questions = questionMasterRepository.findAllBySetNoAndIdYearAndIdInstCdAndIdExamCdOrderByIdQuestionNoAsc(setNo,year,instCd,examCd);
		hm.put("Questions", (List<QuestionMaster>) questions);
		return hm;
	}

}
