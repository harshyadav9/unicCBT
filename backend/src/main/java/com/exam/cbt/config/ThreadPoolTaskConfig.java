package com.exam.cbt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolTaskConfig {
	
	@Bean
	public ThreadPoolTaskExecutor executorPool(){
		
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(200);
		threadPoolTaskExecutor.setMaxPoolSize(200);
		threadPoolTaskExecutor.setQueueCapacity(70000);
		threadPoolTaskExecutor.setThreadNamePrefix("ImageUploadTaskExecutor");
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
		
	}

}
