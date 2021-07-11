package com.exam.cbt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.ImageUploadStatus;

@Repository
public interface ImageUploadStatusRepository extends CrudRepository<ImageUploadStatus, Integer> {

}
