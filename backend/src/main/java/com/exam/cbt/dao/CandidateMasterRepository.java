package com.exam.cbt.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.CandidateMaster;

@Repository
public interface CandidateMasterRepository extends CrudRepository<CandidateMaster, Integer> {
	
	 @Query(value="select * from CandidateMaster where examCd = ?1 and InstCd = ?2 and Year = ?3", nativeQuery=true)
	 Page<CandidateMaster> findByExamCdAndInstCdAndYearSortByRegistrationAsc(String examCode,String instCode, int year,Pageable pageable);
	 
}
