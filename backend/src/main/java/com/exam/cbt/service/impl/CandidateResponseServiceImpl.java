package com.exam.cbt.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.CandidateResponseRepository;
import com.exam.cbt.dao.QuestionMasterRepository;
import com.exam.cbt.entity.CandidateResponse;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.entity.QuestionMasterId;
import com.exam.cbt.service.CandidateResponseService;


@Service
public class CandidateResponseServiceImpl implements CandidateResponseService {
	Logger log = LoggerFactory.getLogger(CandidateResponseServiceImpl.class); 

	@Autowired
	private CandidateResponseRepository candidateResponseRepository;
	
	@Autowired
	private QuestionMasterRepository questionMasterRepository;

	@Override
	public void saveCandidateExam(List<CandidateResponse> candidateResponses) {
		
		candidateResponses.forEach(resp -> {
			CandidateResponse candidateResponse = null;
			QuestionMasterId questionMasterId = new QuestionMasterId();
			questionMasterId.setExamCd(resp.getExamCd());
			questionMasterId.setInstCd(resp.getInstCd());
			questionMasterId.setQuestionNo(resp.getId().getQuestionNo());
			questionMasterId.setYear(resp.getId().getYear());
			
			resp = determineAnswerSelection(questionMasterId,resp);
			if(candidateResponseRepository.existsById(resp.getId())) {
				
				candidateResponseRepository.save(resp);
			}
			else {
				candidateResponse = resp;
				candidateResponseRepository.save(candidateResponse);
			}
		   
		});
		
		//List<StudentResponse> li = studentResponseRepository.saveAll(studResponse);
		//System.out.println(li);
		
	}
	
	private CandidateResponse determineAnswerSelection(QuestionMasterId questionMasterId,CandidateResponse resp) {
		
		Optional<QuestionMaster> master = questionMasterRepository.findById(questionMasterId);
		
		if (master.isPresent()) {
			if (master.get().getMultiple().equals("N")) {
				
				if (resp.getAnswer().equals("Option1")) {
					resp.setAnswer(master.get().getOption1().split("-")[0]);
					
				}
				if (resp.getAnswer().equals("Option2")) {
					resp.setAnswer(master.get().getOption2().split("-")[0]);
					
				}
				if (resp.getAnswer().equals("Option3")) {
					resp.setAnswer(master.get().getOption3().split("-")[0]);
					
				}
				if (resp.getAnswer().equals("Option4")) {
					resp.setAnswer(master.get().getOption4().split("-")[0]);
					
				}
				
			}else {
				
				String[] answers = resp.getAnswer().split(",");
				String string = new String();
				
				for (String answer : answers) {
					
					if (answer.equals("Option1")) {
						//resp.setAnswer(master.get().getOption1().split("-")[0]);
						string = string.concat(master.get().getOption1().split("-")[0]).concat(",");
						
					}
					if (answer.equals("Option2")) {
						string = string.concat(master.get().getOption2().split("-")[0]).concat(",");
						
					}
					if (answer.equals("Option3")) {
						string = string.concat(master.get().getOption3().split("-")[0]).concat(",");
						
					}
					if (answer.equals("Option4")) {
						string = string.concat(master.get().getOption4().split("-")[0]).concat(",");
						
					}
					
				}
				if(string.endsWith(","))
				{
					string = string.substring(0,string.length() - 1);
				}
				resp.setAnswer(string);
			
			}
			
		}
		
		return resp;
	}

}
