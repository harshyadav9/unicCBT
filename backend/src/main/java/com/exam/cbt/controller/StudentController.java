package com.exam.cbt.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.cbt.entity.Student;
import com.exam.cbt.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
	
	Logger log = LoggerFactory.getLogger(StudentController.class); 

	@Autowired
	StudentServiceImpl stuService;

	/*
	 * @PostMapping(path="/create") public ResponseEntity<Student>
	 * createStudent(@RequestBody Student student) {
	 * 
	 * if (student !=null && student.getPassword() != null &&
	 * student.getStudentName()!= null) { return new
	 * ResponseEntity<>(stuService.createStudent(student), HttpStatus.CREATED);
	 * 
	 * } else { return new ResponseEntity<>(new Student(), HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	@GetMapping(path = "/checkStatus")
	public String checkAppStatus() { 	

		String str = "App is running";

		return str;

	}

	//@PostMapping(path = "/login")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String,String> login(@RequestBody Student student) {
		
		Map<String,String> mp = new HashMap<String,String>();
		

		if (student.getPassword() != null) {
			
			String str = stuService.getStudent(student.getRegistrationNo(), student.getPassword());
			if(str != null && str.equalsIgnoreCase("AUTHENTICATED")) {
				mp.put("Message", str);
				mp.put("CODE", HttpStatus.FOUND.getReasonPhrase());
			}else {
				mp.put("Message", str);
				mp.put("CODE", HttpStatus.NOT_FOUND.getReasonPhrase());
			}
			return mp;
			
			
		} else {
			mp.put("Message", "Password is empty");
			mp.put("CODE", HttpStatus.BAD_REQUEST.getReasonPhrase());
			return mp;

		}		

	}

	// @GetMapping("/authenticate/{id}")
//	public ResponseEntity<Student> authenticateStudentId(@PathVariable(required = true) Integer id) {
//		
//		return new ResponseEntity<>(stuService.getStudent(id), HttpStatus.OK);
//		
//	}

}
