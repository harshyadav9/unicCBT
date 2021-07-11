package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.CandidateMaster;

public interface UploadCandidateDataService {
	
	void uploadCandidates(List<CandidateMaster> candidates);
	
}
