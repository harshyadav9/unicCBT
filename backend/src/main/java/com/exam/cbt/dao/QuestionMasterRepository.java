package com.exam.cbt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.entity.QuestionMasterId;

@Repository
public interface QuestionMasterRepository extends JpaRepository<QuestionMaster, QuestionMasterId>{
	
	//public List<QuestionMaster> findByinstCdAndExamCdAndYearAndQuestionAndQuestionNo(String instCd, String examCd, int year, String question, int quesNum);
	

}
