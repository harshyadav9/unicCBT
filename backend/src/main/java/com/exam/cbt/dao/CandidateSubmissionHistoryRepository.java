package com.exam.cbt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.CandidateSubmissionHistory;

@Repository
public interface CandidateSubmissionHistoryRepository extends CrudRepository<CandidateSubmissionHistory, String>{
	
	// List<CandidateResponse> findAllByExamCdAndInstCdAndIdYearOrderByIdRegistrationNoAsc(String examCd, String instCd, int year);

}
