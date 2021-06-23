package com.exam.cbt.service;

import java.util.List;
import java.util.Optional;

import com.exam.cbt.entity.CandidateMaster;

public interface CandidateMasterDataService {
	
	int uploadCandidateMasterData(List<CandidateMaster> candidateMasterList);
	
	public Optional<CandidateMaster> getCandidate(int regNo);
	
}
