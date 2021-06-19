package com.exam.cbt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.StudentResponse;
import com.exam.cbt.entity.StudentResponseId;

@Repository
public interface StudentResponseRepository extends JpaRepository<StudentResponse, StudentResponseId>{
	

}
