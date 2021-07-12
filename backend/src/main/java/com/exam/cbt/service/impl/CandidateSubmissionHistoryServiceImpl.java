package com.exam.cbt.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.CandidateSubmissionHistoryRepository;
import com.exam.cbt.entity.CandidateResponse;
import com.exam.cbt.entity.CandidateSubmissionHistory;
import com.exam.cbt.service.CandidateSubmissionHistoryService;


@Service
public class CandidateSubmissionHistoryServiceImpl implements CandidateSubmissionHistoryService {
	Logger log = LoggerFactory.getLogger(CandidateSubmissionHistoryServiceImpl.class); 

	@Autowired
	private CandidateSubmissionHistoryRepository candidateSubmissionHistoryRepository;
	
	@Override
	public void saveCandidateAnswerFromQueue(List<CandidateResponse> candidateResponses, String messageId) {
		
		List<CandidateSubmissionHistory> l = new LinkedList<>();
		
		candidateResponses.forEach(c->{
			CandidateSubmissionHistory candidateSubmissionHistory = new CandidateSubmissionHistory();
			candidateSubmissionHistory.setAnswer(c.getAnswer());
			candidateSubmissionHistory.setExamCd(c.getExamCd());
			candidateSubmissionHistory.setInstCd(c.getInstCd());
			candidateSubmissionHistory.setMessageId(messageId);
			candidateSubmissionHistory.setQuestionNo(c.getId().getQuestionNo());
			candidateSubmissionHistory.setRegistrationNo(c.getId().getRegistrationNo());
			candidateSubmissionHistory.setYear(c.getId().getYear());
			
			l.add(candidateSubmissionHistory);
			
		});
		
		if (l != null && l.size() > 0) {
			candidateSubmissionHistoryRepository.saveAll(l);
		}
		
		
	}

}
