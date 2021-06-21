package com.exam.cbt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.CandidateMaster;

@Repository
public interface CandidateMasterRepository extends JpaRepository<CandidateMaster, Integer> {

}
