package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.CandidateResponse;

public interface CandidateSubmissionHistoryService {
	
    public void saveCandidateAnswerFromQueue(List<CandidateResponse> candidateResponses, String messageId);
    
}
