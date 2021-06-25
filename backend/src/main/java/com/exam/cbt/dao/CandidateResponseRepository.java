package com.exam.cbt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.CandidateResponse;
import com.exam.cbt.entity.CandidateResponseId;

@Repository
public interface CandidateResponseRepository extends CrudRepository<CandidateResponse, CandidateResponseId>{
	

}
