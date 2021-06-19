package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.StudentResponse;

public interface StudentResponseService {
	
    public void saveStudentExam(List<StudentResponse> studResponse);
}
