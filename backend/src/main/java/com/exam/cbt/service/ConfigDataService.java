package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ConfigId;

public interface ConfigDataService {
	
	int uploadConfigData(List<Config> configList);
	
	public Config getConfig(ConfigId id); 
	
}
