package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.CandidateResponse;

public interface CandidateResponseService {
	
    public void saveCandidateExam(List<CandidateResponse> candidateResponses);
    
    public List<CandidateResponse> getCandidateAnswersForGivenExam(String examCd, String instCd, int year);
}
