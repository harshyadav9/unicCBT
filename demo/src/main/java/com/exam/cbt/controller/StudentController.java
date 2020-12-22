package com.exam.cbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.cbt.entity.Student;
import com.exam.cbt.service.impl.StudentServiceImpl;  

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:8080")
public class StudentController {
	
	@Autowired
	StudentServiceImpl stuService;
	
	@PostMapping(path="/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		
		if (student !=null && student.getPassword() != null && student.getStudentName()!= null) {
			return new ResponseEntity<>(stuService.createStudent(student), HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>(new Student(), HttpStatus.BAD_REQUEST);
			
		}
		
		
	}
	
	@PostMapping(path="/authenticate")
	public ResponseEntity<String> authenticateStudentById(@RequestBody Student student) {
		
		if (student !=null) {
			return new ResponseEntity<>(stuService.getStudent(student.getId(),student.getPassword()), HttpStatus.FOUND);
			
		}
		else {
			return new ResponseEntity<>("Password is empty", HttpStatus.NOT_FOUND);
			
		}
		
		
	}
	

	//	@GetMapping("/authenticate/{id}")
//	public ResponseEntity<Student> authenticateStudentId(@PathVariable(required = true) Integer id) {
//		
//		return new ResponseEntity<>(stuService.getStudent(id), HttpStatus.OK);
//		
//	}


}
	