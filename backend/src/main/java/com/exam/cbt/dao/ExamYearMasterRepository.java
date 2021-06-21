package com.exam.cbt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.ExamYearMaster;
import com.exam.cbt.entity.ExamYearMasterId;

@Repository
public interface ExamYearMasterRepository extends JpaRepository<ExamYearMaster, ExamYearMasterId> {

}
