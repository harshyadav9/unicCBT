package com.exam.cbt.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exam.cbt.constants.CBTConstant;
import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.entity.ExamYearMaster;
import com.exam.cbt.entity.ExamYearMasterId;
import com.exam.cbt.mail.SimpleTextMail;
import com.exam.cbt.pojo.CandidateResponseUI;
import com.exam.cbt.service.impl.CandidateMasterDataServiceImpl;
import com.exam.cbt.service.impl.ExamYearMasterDataServiceImpl;

@Service
public class SubmitExamService {

	Logger log = LoggerFactory.getLogger(SubmitExamService.class);

	@Value("${trigger.email}")
	private String sendEmailFlag;

	@Autowired
	CandidateResponseService candidateRespServ;

	@Autowired
	CandidateMasterDataServiceImpl candidateMasterDataServiceImpl;

	@Autowired
	ExamYearMasterDataServiceImpl examYearMasterDataServiceImpl;

	@Autowired
	SimpleTextMail simpleTextMail;

	public void submitCandidateExam(CandidateResponseUI candidateResp,String messageId) {
		log.info("Exiting submitExam{} ");
		
		candidateResp.getResp().forEach(resp->{
			resp.setMessageId(messageId);
		});
		candidateRespServ.saveCandidateExam(candidateResp.getResp());
		Optional<CandidateMaster> candidate = candidateMasterDataServiceImpl
				.getCandidate(candidateResp.getResp().get(0).getId().getRegistrationNo());
		ExamYearMasterId id = new ExamYearMasterId();
		id.setExamCd(candidate.get().getExamCd());
		id.setInstCd(candidate.get().getInstCd());
		id.setYear(candidate.get().getYear());
		Optional<ExamYearMaster> examYearMaster = examYearMasterDataServiceImpl.getExamYearMasterDetail(id);

		if (sendEmailFlag.equalsIgnoreCase(CBTConstant.ENABLED.name())) {
			if (candidate.isPresent() && examYearMaster.isPresent()) {
				simpleTextMail.sendEmail(candidate.get().getEmailId(), examYearMaster.get().getExamName());
			}
		}

	}

}
