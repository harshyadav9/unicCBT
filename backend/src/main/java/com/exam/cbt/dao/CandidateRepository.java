package com.exam.cbt.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.CandidateMaster;

@Repository
public interface CandidateRepository extends CrudRepository<CandidateMaster, Integer>{
	
	Optional<CandidateMaster> findByRegistrationNo(Integer registrationNo);

}
