package com.exam.cbt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.CandidateRepository;
import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.service.UploadCandidateDataService;

@Service
public class UploadCandidateDataServiceImpl implements UploadCandidateDataService {

	Logger log = LoggerFactory.getLogger(UploadCandidateDataServiceImpl.class);

	@Autowired
	private CandidateRepository candidateRepository;

	@Override
	public void uploadCandidates(List<CandidateMaster> candidates) {
		candidateRepository.saveAll(candidates);

	}

}
