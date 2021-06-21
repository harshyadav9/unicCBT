package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.CandidateMaster;

public interface CandidateService {
	
	String getStudent(Integer id,String password);
    void deleteStudent(CandidateMaster student);
    void deleteStudent(Integer id);
    List<CandidateMaster> getAllStudents(int pageNumber, int pageSize);
    List<CandidateMaster> getAllStudents();
    long countStudents();
}
