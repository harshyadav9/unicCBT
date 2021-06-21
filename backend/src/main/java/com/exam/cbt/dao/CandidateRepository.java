package com.exam.cbt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.CandidateMaster;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateMaster, Integer>{
	
	Optional<CandidateMaster> findByRegistrationNo(Integer registrationNo);

}
