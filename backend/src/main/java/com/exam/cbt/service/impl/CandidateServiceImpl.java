package com.exam.cbt.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.CandidateRepository;
import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService{
	
	Logger log = LoggerFactory.getLogger(CandidateServiceImpl.class); 

	 @Autowired
	 private CandidateRepository candidateRepository;
	 
	@Override
	public String getStudent(Integer registrationNo, String password) {
		
		Optional<CandidateMaster> candidate =  candidateRepository.findById(registrationNo);
		
		if (candidate.isPresent()) {
			if (candidate.get().getPassword().equals(password)) {
				return "Authenticated";
			}
			else {
				
				return "Not Authenticated";
			}
			
		}
		else {
			return "Not Authenticated";
		}
		
	}
	
	public Optional<CandidateMaster> findCandidateWithId(Integer id) {
		return candidateRepository.findById(id);
	}

	@Override
	public void deleteStudent(CandidateMaster student) {
		candidateRepository.delete(student);
		
	}

	@Override
	public void deleteStudent(Integer id) {
		candidateRepository.deleteById(id);
		
	}

	@Override
	public List<CandidateMaster> getAllStudents() {
		 return (List<CandidateMaster>) candidateRepository.findAll();
	}

	@Override
	public long countStudents() {
		return candidateRepository.count();
	}

	@Override
	public List<CandidateMaster> getAllStudents(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
