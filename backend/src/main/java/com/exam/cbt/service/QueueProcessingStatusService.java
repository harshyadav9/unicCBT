package com.exam.cbt.service;

public interface QueueProcessingStatusService {
	
	public String checkQueueProcessingStatus(String examCd, String instCd, int year);
	
	public void updateQueueProcessingStatus(String examCd, String instCd, int year, String updateStatus);
}
