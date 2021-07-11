package com.exam.cbt.service;

import java.util.List;
import java.util.Optional;

import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ConfigId;

public interface ConfigDataService {
	
	int uploadConfigData(List<Config> configList);
	
	public Optional<Config> getConfig(ConfigId id); 
	
}
