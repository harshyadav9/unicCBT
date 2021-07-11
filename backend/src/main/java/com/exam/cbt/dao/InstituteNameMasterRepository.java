package com.exam.cbt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.InstituteNameMaster;
import com.exam.cbt.entity.InstituteNameMasterId;

@Repository
public interface InstituteNameMasterRepository extends JpaRepository<InstituteNameMaster, InstituteNameMasterId> {
	
	//Optional<InstituteNameMaster> findByRegistrationNo(Integer registrationNo);

}
