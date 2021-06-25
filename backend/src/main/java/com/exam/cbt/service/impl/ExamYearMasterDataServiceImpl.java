package com.exam.cbt.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.ExamYearMasterRepository;
import com.exam.cbt.entity.ExamYearMaster;
import com.exam.cbt.entity.ExamYearMasterId;
import com.exam.cbt.service.ExamYearMasterDataService;

@Service
public class ExamYearMasterDataServiceImpl implements ExamYearMasterDataService {

	Logger log = LoggerFactory.getLogger(ExamYearMasterDataServiceImpl.class);

	@Autowired
	private ExamYearMasterRepository examYearMasterRepository;

	@Override
	public int uploadExamYearMasterData(List<ExamYearMaster> examYearMasterList) {
		
//		examYearMasterList.forEach(exmYrMaster -> {
//			ExamYearMaster examYearMaster = null;
//
//			//if(examYearMasterRepository.existsById(exmYrMaster.getId())) {
//				//examYearMasterRepository.save(exmYrMaster);
//			//}
//			//else {
//				//examYearMaster = exmYrMaster;
//				//examYearMasterRepository.save(examYearMaster);
//			//}
//		   
//		});
		
		examYearMasterRepository.saveAll(examYearMasterList);
		return (int) examYearMasterRepository.count();
	}

	@Override
	public Optional<ExamYearMaster> getExamYearMasterDetail(ExamYearMasterId id) {
		return examYearMasterRepository.findById(id);
	}

}
