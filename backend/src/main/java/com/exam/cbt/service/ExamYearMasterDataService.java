package com.exam.cbt.service;

import java.util.List;
import java.util.Optional;

import com.exam.cbt.entity.ExamYearMaster;
import com.exam.cbt.entity.ExamYearMasterId;

public interface ExamYearMasterDataService {
	
	int uploadExamYearMasterData(List<ExamYearMaster> ExamYearMasterList);
	
	public Optional<ExamYearMaster> getExamYearMasterDetail(ExamYearMasterId id);
	
}
