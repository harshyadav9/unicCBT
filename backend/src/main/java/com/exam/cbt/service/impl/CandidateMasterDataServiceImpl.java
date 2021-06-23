package com.exam.cbt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.CandidateMasterRepository;
import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.service.CandidateMasterDataService;

@Service
public class CandidateMasterDataServiceImpl implements CandidateMasterDataService {

	Logger log = LoggerFactory.getLogger(CandidateMasterDataServiceImpl.class);

	@Autowired
	private CandidateMasterRepository candidateMasterRepository;

	@Value("${azure.storage.blob-endpoint}")
	String photoPrefix;
	
	@Value("${azure.token}")
	String photoAccessToken;

	@Override
	public int uploadCandidateMasterData(List<CandidateMaster> candidateMasterList) {

		candidateMasterList.forEach(candidateMstr -> {

			CandidateMaster candidateMaster = null;

			if (candidateMasterRepository.existsById(candidateMstr.getRegistrationNo())) {

				candidateMasterRepository.save(candidateMstr);

			} else {
				candidateMaster = candidateMstr;
				candidateMasterRepository.save(candidateMaster);
			}

		});

		candidateMasterRepository.saveAll(candidateMasterList);
		return candidateMasterList.size();
	}

	@Override
	public Optional<CandidateMaster> getCandidate(int regNo) {
		return candidateMasterRepository.findById(regNo);
	}

	@Override
	public void updatePhotoUrl(List<String> photoUrls) {
		List<CandidateMaster> candidateMasterList = new ArrayList<>();

		photoUrls.forEach(photoUrl -> {
			Optional<CandidateMaster> candidateMaster = candidateMasterRepository
					.findById(Integer.parseInt(photoUrl.substring(0, photoUrl.length() - 4)));

			if (candidateMaster.isPresent()) {
				candidateMaster.get().setPhoto(photoPrefix.concat(photoUrl).concat("?").concat(photoAccessToken));
				candidateMasterList.add(candidateMaster.get());
			}
		});

		if (candidateMasterList.size() > 0) {
			candidateMasterRepository.saveAll(candidateMasterList);
		}

	}

}
