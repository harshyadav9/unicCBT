package com.exam.cbt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.CandidateMasterRepository;
import com.exam.cbt.dao.ConfigRepository;
import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ConfigId;
import com.exam.cbt.service.CandidateMasterDataService;

@Service
public class CandidateMasterDataServiceImpl implements CandidateMasterDataService {

	Logger log = LoggerFactory.getLogger(CandidateMasterDataServiceImpl.class);

	@Autowired
	private CandidateMasterRepository candidateMasterRepository;

	@Autowired
	private ConfigRepository configRepository;

	@Value("${azure.storage.blob-endpoint}")
	String photoPrefix;

	@Value("${azure.token}")
	String photoAccessToken;
	
	@PersistenceContext
	EntityManager em;

	@Override
	public int uploadCandidateMasterData(List<CandidateMaster> candidateMasterList) {

		candidateMasterList.forEach(candidateMstr -> {

			CandidateMaster candidateMaster = null;

//			if (candidateMasterRepository.existsById(candidateMstr.getRegistrationNo())) {
//
//				candidateMasterRepository.save(candidateMstr);
//
//			} else {
//				candidateMaster = candidateMstr;
//				candidateMasterRepository.save(candidateMaster);
//			}

		});

		candidateMasterRepository.saveAll(candidateMasterList);
		return candidateMasterList.size();
	}

	@Override
	public Optional<CandidateMaster> getCandidate(int regNo) {
		return candidateMasterRepository.findById(regNo);
	}

	@Override
	public void updatePhotoUrl(Map<String, String> photoUrlsMap) {
		List<CandidateMaster> candidateMasterList = new ArrayList<>();

		photoUrlsMap.entrySet().stream().forEach(map -> {
			Optional<CandidateMaster> candidateMaster = candidateMasterRepository
					.findById(Integer.parseInt(map.getKey().substring(0, map.getKey().length() - 4)));

			if (candidateMaster.isPresent()) {
				candidateMaster.get().setPhoto(map.getValue());
				candidateMasterList.add(candidateMaster.get());
			}
		});

		if (candidateMasterList.size() > 0) {
			candidateMasterRepository.saveAll(candidateMasterList);
		
		}

	}

	@Transactional
	public void updateSetToCandidates(ConfigId id) {
		Pageable pageable = PageRequest.of(0, 10000);
		Optional<Config> config = configRepository.findById(id);

		if (config.isPresent()) {
			List<CandidateMaster> cn = new ArrayList<>();
			int seriesLimitLower = config.get().getSetStart();
			int noOfSet = config.get().getNoOfSet();
			int seriesLimitUpper = seriesLimitLower + noOfSet - 1;
			
			Page<CandidateMaster> page = candidateMasterRepository.findByExamCdAndInstCdAndYearSortByRegistrationAsc(id.getExamCd(), id.getInstCd(),
					id.getYear(),pageable);
			int size = 0;//page.getTotalPages();
			while(size <= page.getTotalPages()) {
				Page<CandidateMaster> page1 = abc(size,10000,id);
				//while (page1.hasNext()) {
					List<CandidateMaster> candidates = page1.getContent();
					
					for (CandidateMaster c : candidates) {
						if (seriesLimitLower > seriesLimitUpper) {
							seriesLimitLower = config.get().getSetStart();
						}
						System.out.println("RegistrationNo"+c.getRegistrationNo());
						c.setSetNo(seriesLimitLower);
						seriesLimitLower++;
						
						cn.add(c);
					}
					candidateMasterRepository.saveAll(cn);
					
				//}
				size++;
			}
			
		}
		System.out.println("Set Nos are updated successfully in Candidate Master");
	}

	private Page<CandidateMaster> abc(int pageNo,int size,ConfigId id) {
		Pageable pageable = PageRequest.of(pageNo, size);
		Page<CandidateMaster> page = candidateMasterRepository.findByExamCdAndInstCdAndYearSortByRegistrationAsc(id.getExamCd(), id.getInstCd(),
				id.getYear(),pageable);
		
		return page;
	}
}
