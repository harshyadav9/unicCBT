package com.exam.cbt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exam.cbt.dao.StudentRepository;
import com.exam.cbt.entity.Student;
import com.exam.cbt.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	 @Autowired
	 private StudentRepository studentRepository;
	 
	@Override
	public Student createStudent(Student student) {
		if (student != null) {
			/*
			 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			 * encoder.matches(password, user.getPassword());
			 */
			
			//student.setPassword(BCrypt.gensalt(password));
			
		}
		 return studentRepository.save(student);
	}

	@Override
	public String getStudent(Integer registrationNo, String password) {
		
		Optional<Student> student =  studentRepository.findById(registrationNo);
		
		if (student.isPresent()) {
			if (student.get().getPassword().equals(password)) {
				return "Authenticated";
			}
			else {
				
				return "Not Authenticated";
			}
			
		}
		else {
			return "Not Authenticated";
		}
		
	}

	@Override
	public void deleteStudent(Student student) {
		studentRepository.delete(student);
		
	}

	@Override
	public void deleteStudent(Integer id) {
		studentRepository.deleteById(id);
		
	}

	@Override
	public List<Student> getAllStudents(int pageNumber, int pageSize) {
		Pageable paging = PageRequest.of(pageNumber, pageSize);
		 
        return studentRepository.findAll(paging).getContent();
	}

	@Override
	public List<Student> getAllStudents() {
		 return studentRepository.findAll();
	}

	@Override
	public long countStudents() {
		return studentRepository.count();
	}
	
	
	
	

}
