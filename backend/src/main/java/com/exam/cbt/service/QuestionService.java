package com.exam.cbt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ConfigId;
import com.exam.cbt.entity.InstituteNameMaster;
import com.exam.cbt.entity.InstituteNameMasterId;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.mail.SimpleTextMail;
import com.exam.cbt.pojo.QuestionMasterResponse;
import com.exam.cbt.service.impl.CandidateMasterDataServiceImpl;
import com.exam.cbt.service.impl.CandidateServiceImpl;
import com.exam.cbt.service.impl.ExamYearMasterDataServiceImpl;
import com.exam.cbt.service.impl.InstituteNameMasterDataServiceImpl;
import com.exam.cbt.service.impl.QuestionMasterDataServiceImpl;

@Service
public class QuestionService {

	Logger log = LoggerFactory.getLogger(QuestionService.class);

	@Autowired
	CandidateServiceImpl candidateService;

	@Autowired
	QuestionMasterDataServiceImpl questionMasterServ;
	
	@Autowired
	InstituteNameMasterDataServiceImpl instituteNameMasterDataServiceImpl;

	@Autowired
	SimpleTextMail simpleTextMail;
	
	@Autowired
	ConfigDataService configDataService;

	public QuestionMasterResponse populatequestionMasterResponse(int registrationNumber) {

		QuestionMasterResponse res = null;

		Optional<CandidateMaster> candidateMaster = candidateService.findCandidateWithId(registrationNumber);

		if (candidateMaster.isPresent()) {
			res = new QuestionMasterResponse();
			ConfigId id = new ConfigId();
			id.setExamCd(candidateMaster.get().getExamCd());
			id.setInstCd(candidateMaster.get().getInstCd());
			id.setYear(candidateMaster.get().getYear());
			int setNo = candidateMaster.get().getSetNo();
			Optional<Config> config = configDataService.getConfig(id);
			InstituteNameMasterId instituteNameMasterId = new InstituteNameMasterId();
			instituteNameMasterId.setExamCd(candidateMaster.get().getExamCd());
			instituteNameMasterId.setInstCd(candidateMaster.get().getInstCd());
			instituteNameMasterId.setYear(candidateMaster.get().getYear());
			Optional<InstituteNameMaster> instituteNameMaster = instituteNameMasterDataServiceImpl
					.getInstituteDetail(instituteNameMasterId);

			if (instituteNameMaster.isPresent()) {
				res.setInstituteName(instituteNameMaster.get().getInstName());

			}

			HashMap<String, List<QuestionMaster>> hm = questionMasterServ.getAllQuestions(setNo, id.getYear(),
					id.getInstCd(), id.getExamCd());

			res.setQuestionList(hm);
			res.setCandidateName(candidateMaster.get().getName());
			if (config.isPresent()) {
				res.setDateOfExam(config.get().getDateOfExam());
				res.setDurationHr(config.get().getDurationHr());
				res.setDurationMin(config.get().getDurationMin());
			}

			res.setPhoto(candidateMaster.get().getPhoto());
			res.setRegistrationNo(registrationNumber);
		}

		return res;
	}

}
