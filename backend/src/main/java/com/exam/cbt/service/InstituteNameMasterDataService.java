package com.exam.cbt.service;

import java.util.List;
import java.util.Optional;

import com.exam.cbt.entity.InstituteNameMaster;
import com.exam.cbt.entity.InstituteNameMasterId;

public interface InstituteNameMasterDataService {
	
	int uploadInstituteNameMasterData(List<InstituteNameMaster> instituteNameMasterList);
	
	public Optional<InstituteNameMaster> getInstituteDetail(InstituteNameMasterId id);
	
}
