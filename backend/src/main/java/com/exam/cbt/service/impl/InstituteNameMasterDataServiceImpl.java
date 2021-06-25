package com.exam.cbt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.InstituteNameMasterRepository;
import com.exam.cbt.entity.InstituteNameMaster;
import com.exam.cbt.service.InstituteNameMasterDataService;

@Service
public class InstituteNameMasterDataServiceImpl implements InstituteNameMasterDataService {

	Logger log = LoggerFactory.getLogger(InstituteNameMasterDataServiceImpl.class);

	@Autowired
	private InstituteNameMasterRepository instituteNameMasterRepository;

	@Override
	public int uploadInstituteNameMasterData(List<InstituteNameMaster> instituteNameMasterList) {
		
//		instituteNameMasterList.forEach(instMaster -> {
//			InstituteNameMaster instituteNameMaster = null;
//
//			if(instituteNameMasterRepository.existsById(instMaster.getId())) {
//				instituteNameMasterRepository.save(instMaster);
//			}
//			else {
//				instituteNameMaster = instMaster;
//				instituteNameMasterRepository.save(instituteNameMaster);
//			}
//		   
//		});
//		
		instituteNameMasterRepository.saveAll(instituteNameMasterList);
		return instituteNameMasterList.size();
	}

}
