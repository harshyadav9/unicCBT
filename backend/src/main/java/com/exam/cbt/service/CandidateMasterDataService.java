package com.exam.cbt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.exam.cbt.entity.CandidateMaster;

public interface CandidateMasterDataService {
	
	int uploadCandidateMasterData(List<CandidateMaster> candidateMasterList);
	
	public Optional<CandidateMaster> getCandidate(int regNo);
	
	public void updatePhotoUrl(Map<String,String> photoUrls);
	
}
