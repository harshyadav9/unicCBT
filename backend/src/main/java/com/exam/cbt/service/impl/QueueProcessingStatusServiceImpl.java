package com.exam.cbt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.QueueProcessingStatusRepository;
import com.exam.cbt.entity.QueueProcessingStatus;
import com.exam.cbt.entity.QueueProcessingStatusId;
import com.exam.cbt.service.QueueProcessingStatusService;

@Service
public class QueueProcessingStatusServiceImpl implements QueueProcessingStatusService {

	Logger log = LoggerFactory.getLogger(QueueProcessingStatusServiceImpl.class);

	@Autowired 
	QueueProcessingStatusRepository queueProcessingStatusRepository;
	
	@Override
	public String checkQueueProcessingStatus(String examCd, String instCd, int year) {
		QueueProcessingStatus status = queueProcessingStatusRepository.findByIdExamCdAndIdInstCdAndIdYear(examCd, instCd, year);
		
		if (status == null ) {
			return "NOTSTARTED";
		}
		return status.getProcessingStatus();
		
	}

	@Override
	public void updateQueueProcessingStatus(String examCd, String instCd, int year, String updateStatus) {
		
		
		QueueProcessingStatusId id = new QueueProcessingStatusId();
		QueueProcessingStatus  status = new QueueProcessingStatus();
		
		id.setExamCd(examCd);
		id.setYear(year);
		id.setInstCd(instCd);
		status.setId(id);
		status.setProcessingStatus(updateStatus);
		queueProcessingStatusRepository.save(status);
	}

}
