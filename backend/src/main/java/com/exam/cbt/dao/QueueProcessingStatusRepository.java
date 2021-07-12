package com.exam.cbt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.QueueProcessingStatus;
import com.exam.cbt.entity.QueueProcessingStatusId;

@Repository
public interface QueueProcessingStatusRepository extends CrudRepository<QueueProcessingStatus, QueueProcessingStatusId>{
	
	public QueueProcessingStatus findByIdExamCdAndIdInstCdAndIdYear(String examCd, String instCd, int year);
	

}
