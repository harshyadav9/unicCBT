package com.exam.cbt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.CandidateMaster;

@Repository
public interface CandidateMasterRepository extends CrudRepository<CandidateMaster, Integer> {

}
