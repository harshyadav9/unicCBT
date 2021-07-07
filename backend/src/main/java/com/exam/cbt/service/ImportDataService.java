package com.exam.cbt.service;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ExamYearMaster;
import com.exam.cbt.entity.InstituteNameMaster;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.helper.ReadExcelData;
import com.exam.cbt.service.impl.CandidateMasterDataServiceImpl;
import com.exam.cbt.service.impl.ConfigDataServiceImpl;
import com.exam.cbt.service.impl.ExamYearMasterDataServiceImpl;
import com.exam.cbt.service.impl.InstituteNameMasterDataServiceImpl;
import com.exam.cbt.service.impl.QuestionMasterDataServiceImpl;

@Service
public class ImportDataService {
	
	Logger log = LoggerFactory.getLogger(ImportDataService.class);
	
	@Autowired
	ReadExcelData readExcelData;
	
	@Autowired
	ExamYearMasterDataServiceImpl examYearMasterDataServiceImpl;
	
	@Autowired
	ConfigDataServiceImpl configDataServiceImpl;
	
	@Autowired
	CandidateMasterDataServiceImpl candidateMasterDataServiceImpl;
	@Autowired
	InstituteNameMasterDataServiceImpl instituteNameMasterDataServiceImpl;
	
	@Autowired
	QuestionMasterDataServiceImpl questionMasterDataServiceImpl;
	
	public int importQuestionMaster(MultipartFile files) throws IOException {
		Instant start = Instant.now();
		List<QuestionMaster> questionMasterList = readExcelData.readQuestionMasterData(files);

		int size = questionMasterDataServiceImpl.uploadQuestionMaster(questionMasterList);
		Instant finish = Instant.now();
		long timeElapsedMin = Duration.between(start, finish).toMinutes();
		long timeElapsedHr = Duration.between(start, finish).toHours();
		System.out.println("Time Elapsed in Minutes: " +timeElapsedMin); // Prints: Time Elapsed: 2501 
		System.out.println("Time Elapsed in Hours: " +timeElapsedHr);
		
		return size;
	}
	
	public int importInstMaster(MultipartFile files) throws IOException {
		List<InstituteNameMaster> instituteNameMasterList = readExcelData.readInstituteMasterData(files);

		int size = instituteNameMasterDataServiceImpl.uploadInstituteNameMasterData(instituteNameMasterList);
		
		return size;
	}
	
	public int importExamMaster(MultipartFile files) throws IOException {
		List<ExamYearMaster> examYearMasterList = readExcelData.readExamYearMasterData(files);

		int size = examYearMasterDataServiceImpl.uploadExamYearMasterData(examYearMasterList);
		
		return size;
	}
	
	public int importConfigData(MultipartFile files) throws IOException {
		List<Config> configList = readExcelData.readConfigData(files);

		int size = configDataServiceImpl.uploadConfigData(configList);
		
		return size;
	}
	
	public int importCandidateMasterData(MultipartFile files) throws IOException {
		List<CandidateMaster> candidateMasterList = readExcelData.readCandidateMasterData(files);

		int size = candidateMasterDataServiceImpl.uploadCandidateMasterData(candidateMasterList);
		
		return size;
	}
	
}
