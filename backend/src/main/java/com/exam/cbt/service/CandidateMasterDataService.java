package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.CandidateMaster;

public interface CandidateMasterDataService {
	
	int uploadCandidateMasterData(List<CandidateMaster> candidateMasterList);
	
}
