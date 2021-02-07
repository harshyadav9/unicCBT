package com.exam.cbt.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.StudentResponseRepository;
import com.exam.cbt.entity.StudentResponse;
import com.exam.cbt.service.StudentResponseService;

@Service
public class StudentResponseServiceImpl implements StudentResponseService {
	Logger log = LoggerFactory.getLogger(StudentResponseServiceImpl.class); 

	@Autowired
	private StudentResponseRepository studentResponseRepository;

	@Override
	public void saveStudentExam(List<StudentResponse> studResponse) {
		List<StudentResponse> li = studentResponseRepository.saveAll(studResponse);
		System.out.println(li);
		
	}

}
