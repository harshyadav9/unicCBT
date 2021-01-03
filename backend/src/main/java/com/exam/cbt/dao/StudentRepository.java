package com.exam.cbt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.cbt.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	Optional<Student> findById(Integer id);

}
