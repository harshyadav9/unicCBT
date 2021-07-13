package com.exam.cbt.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.entity.QuestionMasterId;

@Repository
public interface QuestionMasterRepository extends CrudRepository<QuestionMaster, QuestionMasterId> {
	
	 List<QuestionMaster> findAllBySetNoAndIdYearAndIdInstCdAndIdExamCdOrderByIdQuestionNoAsc(int setNo, int year, String instCode, String examCd);
}
