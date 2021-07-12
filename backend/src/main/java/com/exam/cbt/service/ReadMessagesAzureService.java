package com.exam.cbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.microsoft.azure.storage.StorageException;

@Configuration
@EnableScheduling
public class ReadMessagesAzureService {
	
	@Autowired
	QueueAzureService queueAzureService;
	
	//@Scheduled(fixedDelay = 1000)
	@Scheduled(cron = "${cron.expression}") //runs in every 2 minutes
	public void scheduleFixedDelayTask() {
		
				try {
					queueAzureService.readMessage();
					System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
				} catch (StorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
		
	    
	}

}
