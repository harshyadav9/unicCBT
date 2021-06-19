package com.exam.cbt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.StudentRepository;
import com.exam.cbt.entity.Student;
import com.exam.cbt.service.UploadStudentDataService;

@Service
public class UploadStudentDataServiceImpl implements UploadStudentDataService{
	
	Logger log = LoggerFactory.getLogger(UploadStudentDataServiceImpl.class); 
	
	 @Autowired
	 private StudentRepository studentRepository;

	@Override
	public void uploadStudents(List<Student> students) {
		studentRepository.saveAll(students);
		
	}


}
