package com.exam.cbt.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.ConfigRepository;
import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ConfigId;
import com.exam.cbt.service.ConfigDataService;

@Service
public class ConfigDataServiceImpl implements ConfigDataService {

	Logger log = LoggerFactory.getLogger(ConfigDataServiceImpl.class);

	@Autowired
	private ConfigRepository configRepository;

	@Override
	public int uploadConfigData(List<Config> configList) {
		
//		configList.forEach(config -> {
//			//Config configObject = null;
//
//			//if(configRepository.existsById(config.getId())) {
//				configRepository.save(config);
//			//}
//			//else {
//				//configObject = config;
//				//configRepository.save(configObject);
//			//}
//		   
//		});
		
		configRepository.saveAll(configList);
		return configList.size();
	}

	@Override
	public Optional<Config> getConfig(ConfigId id) {
		return configRepository.findById(id);
	}

}
