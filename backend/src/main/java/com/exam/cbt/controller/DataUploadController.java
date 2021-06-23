package com.exam.cbt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exam.cbt.azure.AzureBlobAdapter;
import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ExamYearMaster;
import com.exam.cbt.entity.InstituteNameMaster;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.helper.ReadExcelData;
import com.exam.cbt.service.impl.CandidateMasterDataServiceImpl;
import com.exam.cbt.service.impl.ConfigDataServiceImpl;
import com.exam.cbt.service.impl.ExamYearMasterDataServiceImpl;
import com.exam.cbt.service.impl.InstituteNameMasterDataServiceImpl;
import com.exam.cbt.service.impl.QuestionMasterDataServiceImpl;
import com.exam.cbt.service.impl.UploadCandidateDataServiceImpl;

@RestController
@RequestMapping("/cbt/upload")
public class DataUploadController {

	Logger log = LoggerFactory.getLogger(DataUploadController.class);

	@Autowired
	UploadCandidateDataServiceImpl uploadStudentDataService;

	@Autowired
	QuestionMasterDataServiceImpl questionMasterDataServiceImpl;

	@Autowired
	InstituteNameMasterDataServiceImpl instituteNameMasterDataServiceImpl;

	@Autowired
	ExamYearMasterDataServiceImpl examYearMasterDataServiceImpl;

	@Autowired
	ConfigDataServiceImpl configDataServiceImpl;

	@Autowired
	CandidateMasterDataServiceImpl candidateMasterDataServiceImpl;

	@Autowired
	ReadExcelData readExcelData;

	@Autowired
	AzureBlobAdapter azureAdapter;

	@PostMapping(value = "/importRegisteredStudentData")
	ResponseEntity<List<CandidateMaster>> importRegisteredStudentData(@RequestParam("file") MultipartFile files)
			throws IOException {

		HttpStatus status = HttpStatus.OK;
		List<CandidateMaster> stuList = new ArrayList<>();

		XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);

		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {
				CandidateMaster student = new CandidateMaster();

				XSSFRow row = worksheet.getRow(index);
				Integer id = (int) row.getCell(0).getNumericCellValue();

				student.setRegistrationNo(id);
				// student.setStudentName(row.getCell(1).getStringCellValue());
				if (row.getCell(2).getCellType() == CellType.STRING)
					student.setPassword(row.getCell(2).getStringCellValue());
				else if (row.getCell(2).getCellType() == CellType.NUMERIC) {
					student.setPassword(String.valueOf((int) row.getCell(2).getNumericCellValue()));
				}

				stuList.add(student);
			}
		}
		workbook.close();

		// insert into db
		uploadStudentDataService.uploadCandidates(stuList);

		return new ResponseEntity<>(stuList, status);
	}

	@PostMapping(value = "/importQuestionMasterData")
	ResponseEntity<String> importQuestionMasterData(@RequestParam("file") MultipartFile files) throws IOException {

		List<QuestionMaster> questionMasterList = readExcelData.readQuestionMasterData(files);

		int size = questionMasterDataServiceImpl.uploadQuestionMaster(questionMasterList);

		return new ResponseEntity<>(size + " Questions are Uploaded Successfully.", HttpStatus.CREATED);
	}

	@PostMapping(value = "/importInstitutionMasterData")
	ResponseEntity<String> importInstitutionMasterData(@RequestParam("file") MultipartFile files) throws IOException {

		List<InstituteNameMaster> instituteNameMasterList = readExcelData.readInstituteMasterData(files);

		int size = instituteNameMasterDataServiceImpl.uploadInstituteNameMasterData(instituteNameMasterList);

		return new ResponseEntity<>(size + " Institute Master Data is Uploaded Successfully.", HttpStatus.CREATED);
	}

	@PostMapping(value = "/importExamYearMasterData")
	ResponseEntity<String> importExamYearMasterData(@RequestParam("file") MultipartFile files) throws IOException {

		List<ExamYearMaster> examYearMasterList = readExcelData.readExamYearMasterData(files);

		int size = examYearMasterDataServiceImpl.uploadExamYearMasterData(examYearMasterList);

		return new ResponseEntity<>(size + " Exam Year Master Data is Uploaded Successfully.", HttpStatus.CREATED);

	}

	@PostMapping(value = "/importConfigData")
	ResponseEntity<String> importConfigData(@RequestParam("file") MultipartFile files) throws IOException {

		List<Config> configList = readExcelData.readConfigData(files);

		int size = configDataServiceImpl.uploadConfigData(configList);

		return new ResponseEntity<>(size + " Config Data is Uploaded Successfully.", HttpStatus.CREATED);

	}

	@PostMapping(value = "/importCandidateMasterData")
	ResponseEntity<String> importCandidateMasterData(@RequestParam("file") MultipartFile files) throws IOException {

		List<CandidateMaster> candidateMasterList = readExcelData.readCandidateMasterData(files);

		int size = candidateMasterDataServiceImpl.uploadCandidateMasterData(candidateMasterList);

		return new ResponseEntity<>(size + " Candidate Master Data is Uploaded Successfully.", HttpStatus.CREATED);

	}
	
	@PostMapping(path = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFile(@RequestPart(value = "files", required = true) List<MultipartFile> files)  {
        List<String> name = azureAdapter.upload(files, "prefix");
        Map<String, String> result = new HashMap<>();
       // result.put("key", name);
        return result;
    }

    @GetMapping(path = "/download")
    public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
        byte[] data = azureAdapter.getFile(file);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
                .body(resource);

    }

}
