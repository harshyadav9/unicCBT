package com.exam.cbt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.QuestionMaster;

@Repository
public interface QuestionMasterRepository extends JpaRepository<QuestionMaster, Integer>{
	

}
