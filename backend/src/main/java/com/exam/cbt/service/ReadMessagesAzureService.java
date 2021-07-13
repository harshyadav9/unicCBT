package com.exam.cbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.exam.cbt.constants.CBTConstant;
import com.exam.cbt.service.impl.QueueProcessingStatusServiceImpl;
import com.microsoft.azure.storage.StorageException;

@Configuration
@EnableScheduling
public class ReadMessagesAzureService {

	@Autowired
	QueueAzureService queueAzureService;

	@Value("${cbtmdmsi0012021queue.polling.flag}")
	private String pollingFlag;

	@Autowired
	QueueProcessingStatusServiceImpl queueProcessingStatusServiceImpl;

	// @Scheduled(fixedDelay = 1000)
	@Scheduled(cron = "${cron.expression}") // runs in every 2 minutes
	public void scheduleFixedDelayTask() {

		try {
			if (pollingFlag.equalsIgnoreCase(CBTConstant.ENABLED.name())) {
				if (queueProcessingStatusServiceImpl.checkQueueProcessingStatus("MDMS", "I001", 2021)
						.equalsIgnoreCase("NOTSTARTED")
						|| queueProcessingStatusServiceImpl.checkQueueProcessingStatus("MDMS", "I001", 2021)
								.equalsIgnoreCase(CBTConstant.COMPLETED.name())) {
					queueAzureService.readMessage();
					System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
				}

			} else {
				
				System.out.println("Earlier queue is still in process..");

			}

		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
