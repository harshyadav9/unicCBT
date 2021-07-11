package com.exam.cbt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.ImageUploadStatusRepository;
import com.exam.cbt.entity.ImageUploadStatus;
import com.exam.cbt.service.ImageUploadStatusService;

@Service
public class ImageUploadStatusServiceImpl implements ImageUploadStatusService {

	Logger log = LoggerFactory.getLogger(ImageUploadStatusServiceImpl.class);

	@Autowired
	private ImageUploadStatusRepository imageUploadStatusRepository;

	@Value("${azure.storage.blob-endpoint}")
	String photoPrefix;
	
	@Value("${azure.token}")
	String photoAccessToken;

	@Override
	public void saveImageDetail(ImageUploadStatus img) {
		
		imageUploadStatusRepository.save(img);
		
	}

	@Override
	public void saveAllImagesDetails(List<ImageUploadStatus> imges) {
		
		imageUploadStatusRepository.saveAll(imges);
		// TODO Auto-generated method stub
		
	}

}
