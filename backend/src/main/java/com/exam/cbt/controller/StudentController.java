package com.exam.cbt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.entity.Student;
import com.exam.cbt.pojo.QuestionMasterResponse;
import com.exam.cbt.service.StudentResponseService;
import com.exam.cbt.service.impl.QuestionMasterDataServiceImpl;
import com.exam.cbt.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
	
	Logger log = LoggerFactory.getLogger(StudentController.class); 

	@Autowired
	StudentServiceImpl stuService;

	@Autowired
	QuestionMasterDataServiceImpl questionMasterServ;
	
	@Autowired
	StudentResponseService stuRespServ;

	@GetMapping(path = "/checkStatus")
	public String checkAppStatus() { 	

		String str = "App is running";
		log.info(str);
		log.info("Exiting checkAppStatus{}");
		return str;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody Student student) {
		
		log.info("Inside login{} with inout student : "+student);
		
		Map<String,String> mp = new HashMap<String,String>();

		if (student.getPassword() != null) {
			log.info("Calling stuService.getStudent{} ");
			String str = stuService.getStudent(student.getRegistrationNo(), student.getPassword());
			if(str != null && str.equalsIgnoreCase("AUTHENTICATED")) {
				log.info("Authenticated:  " +student.getRegistrationNo());
				mp.put("Message", str);
				mp.put("CODE", HttpStatus.OK.getReasonPhrase());
				return new ResponseEntity<>("Authenticated", HttpStatus.OK);
			}else {
				log.info("Not Found:  " +student.getRegistrationNo());
				return new ResponseEntity<>("Not Authenticated", HttpStatus.NOT_FOUND);
				
			}
			
		} else {
			log.info("Password is emptyfor registrationNumber:  " +student.getRegistrationNo());
			log.info("Exiting login{}");
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
		}		

	}
	
	@RequestMapping(value = "/submitExam", method = RequestMethod.PUT)
	public HashMap<String,String> submitExam(@RequestBody com.exam.cbt.pojo.StudentResponse studentResp) {
		
		
		HashMap<String, String> retCode = new HashMap<>(); 
		
		if (studentResp != null) {
			log.info("Inside submitExam{} for registrationNo : " + studentResp.getRegistrationNo());
			stuRespServ.saveStudentExam(studentResp.getResp());
			retCode.put("CODE", HttpStatus.CREATED.getReasonPhrase());
			log.info("Exiting submitExam{} for registrationNo : " +studentResp.getRegistrationNo() );
			return retCode;
			
		} else {
			
			retCode.put("CODE", HttpStatus.BAD_REQUEST.getReasonPhrase());
			log.info(retCode.get("CODE"));
			log.info("Exiting submitExam{} ");
			return retCode;

		}		

	}
	
	@RequestMapping(value = "/getQuestions/{registrationNumber}", method = RequestMethod.GET)
	public QuestionMasterResponse getQuestions(@PathVariable Integer registrationNumber) {
		
		log.info("Inside getQuestions{} with registrationNo: " +registrationNumber);
		
		QuestionMasterResponse res = new QuestionMasterResponse();
		HashMap<String, String> retCode = new HashMap<>(); 
		
		if (registrationNumber != null) {
			log.info("Calling questionMasterServ.getAllQuestions{}");
			HashMap<String, List<QuestionMaster>> hm = questionMasterServ.getAllQuestions();
			Student student = stuService.findStudentWithId(registrationNumber);
			
			retCode.put("CODE", HttpStatus.ACCEPTED.getReasonPhrase());
			res.setQuestionList(hm);
			res.setExamCd(student.getExamCd());
			res.setResponseCode(retCode);
			log.info(retCode.get("CODE"));
			log.info("Exiting getQuestions{}");
			return res;
			
		} else {
			
			retCode.put("CODE", HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setResponseCode(retCode);
			log.info(retCode.get("CODE"));
			log.info("Exiting getQuestions{}");
			return res;

		}		

	}

}
