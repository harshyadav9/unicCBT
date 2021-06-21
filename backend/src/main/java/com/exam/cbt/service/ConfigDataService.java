package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.Config;

public interface ConfigDataService {
	
	int uploadConfigData(List<Config> configList);
	
}
