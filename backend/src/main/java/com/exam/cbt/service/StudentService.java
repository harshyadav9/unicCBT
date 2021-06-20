package com.exam.cbt.service;

import java.util.List;

import com.exam.cbt.entity.Student;

public interface StudentService {
	
	String getStudent(Integer id,String password);
    void deleteStudent(Student student);
    void deleteStudent(Integer id);
    List<Student> getAllStudents(int pageNumber, int pageSize);
    List<Student> getAllStudents();
    long countStudents();
}
