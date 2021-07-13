package com.exam.cbt.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.pojo.QuestionMasterResponse;
import com.exam.cbt.service.ConfigDataService;
import com.exam.cbt.service.DownloadExamAnswers;
import com.exam.cbt.service.QuestionService;
import com.exam.cbt.service.QueueAzureService;
import com.exam.cbt.service.SubmitExamService;
import com.exam.cbt.service.impl.CandidateMasterDataServiceImpl;
import com.exam.cbt.service.impl.CandidateServiceImpl;
import com.exam.cbt.service.impl.ExamYearMasterDataServiceImpl;
import com.exam.cbt.service.impl.InstituteNameMasterDataServiceImpl;
import com.exam.cbt.service.impl.QuestionMasterDataServiceImpl;
import com.microsoft.azure.storage.StorageException;

@RestController
@RequestMapping("/cbt/student")
@CrossOrigin(origins = "*")
public class StudentController {
	
	Logger log = LoggerFactory.getLogger(StudentController.class); 

	@Autowired
	CandidateServiceImpl candidateService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	DownloadExamAnswers excelExporter;
	
	@Autowired
	QuestionMasterDataServiceImpl questionMasterServ;
	
	@Autowired
	InstituteNameMasterDataServiceImpl instituteNameMasterDataServiceImpl;
	
	@Autowired
	QueueAzureService queueAzureService;
	
	@Autowired
	ConfigDataService configDataService;
	
	@Autowired
	CandidateMasterDataServiceImpl candidateMasterDataServiceImpl;
	
	@Autowired
	ExamYearMasterDataServiceImpl examYearMasterDataServiceImpl;
	
	@Autowired
	SubmitExamService submitExamService;

	@GetMapping(path = "/checkStatus")
	public ResponseEntity<String> checkAppStatus() { 	

		log.info("Exiting checkAppStatus{}");
		return new ResponseEntity<>("App is Up and Running", HttpStatus.OK);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody CandidateMaster student) {
		
		log.info("Inside login{} with inout student : "+student);
		
		Map<String,String> mp = new HashMap<String,String>();

		if (student.getPassword() != null) {
			log.info("Calling stuService.getStudent{} ");
			String str = candidateService.getStudent(student.getRegistrationNo(), student.getPassword());
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
	
//	@RequestMapping(value = "/submitExam", method = RequestMethod.PUT)
//	public ResponseEntity<String> submitExam(@RequestBody CandidateResponseUI candidateResp) {
//		
//		if (candidateResp != null) {
//			submitExamService.submitCandidateExam(candidateResp);
//			
//			return new ResponseEntity<>("Exam Submitted Successfully", HttpStatus.OK);
//			
//		} else {
//			
//			log.info("Exiting submitExam{} ");
//			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
//		}		
//	}

	@RequestMapping(value = "/getQuestions/{registrationNumber}", method = RequestMethod.GET)
	public ResponseEntity<QuestionMasterResponse> getQuestions(@PathVariable Integer registrationNumber) {
		
		log.info("Inside getQuestions{} with registrationNo: " +registrationNumber);
		
		QuestionMasterResponse res = new QuestionMasterResponse();
		
		if (registrationNumber != null) {
			res = questionService.populatequestionMasterResponse(registrationNumber);
			
			if (res==null){
				return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
			}
			log.info("Exiting getQuestions{}");
			return new ResponseEntity<>(res, HttpStatus.OK);
			
		} else {
			
			log.info("Exiting getQuestions{}");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}		
	}
	
//	@GetMapping(path = "/download")
//	public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
//		byte[] data = azureAdapter.getFile(file);
//		ByteArrayResource resource = new ByteArrayResource(data);
//
//		return ResponseEntity.ok().contentLength(data.length).header("Content-type", "application/octet-stream")
//				.header("Content-disposition", "attachment; filename=\"" + file + "\"").body(resource);
//
//	}
	
	@RequestMapping(value = "/candidatesAnswers/export/excel/{examCd}/{instCd}/{year}", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadCandidatesAnswers(@PathVariable String examCd,@PathVariable String instCd,
			@PathVariable Integer year,HttpServletResponse httpServletResponse) throws IOException {
		
		log.info("Inside downloadCandidatesAnswers{} : ");
		
		httpServletResponse.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=candidates_" + currentDateTime + ".xlsx";
		httpServletResponse.setHeader(headerKey, headerValue);
		
		//DownloadExamAnswers excelExporter = new DownloadExamAnswers((List<CandidateResponse>) listUsers);
		
		String workbook = "tutorials.xlsx";
	    InputStreamResource file = new InputStreamResource(excelExporter.export(httpServletResponse, examCd, instCd, year));
        
        return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + workbook)
		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		        .body(file); 		
	}
	
	@RequestMapping(value = "/sendToQueue", method = RequestMethod.POST)
	public void sendMessageToQueue(@RequestBody String str) {
		
			try {
				queueAzureService.sendMessage(str);
			} catch (StorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
//	@RequestMapping(value = "/readMessage", method = RequestMethod.GET)
//	public void readMessage() {
//		
//		try {
//			queueAzureService.readMessage();
//			//readMessagesAzureService.scheduleFixedDelayTask();
//		} catch (StorageException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
}
