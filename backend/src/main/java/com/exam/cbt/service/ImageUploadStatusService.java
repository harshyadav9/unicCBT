package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.ImageUploadStatus;

public interface ImageUploadStatusService {
	
	public void saveImageDetail(ImageUploadStatus img) ;
	
	public void saveAllImagesDetails(List<ImageUploadStatus> imges) ;
}
