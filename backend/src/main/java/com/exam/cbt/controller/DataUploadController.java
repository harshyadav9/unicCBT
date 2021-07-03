package com.exam.cbt.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

		Instant start = Instant.now();
		List<QuestionMaster> questionMasterList = readExcelData.readQuestionMasterData(files);

		int size = questionMasterDataServiceImpl.uploadQuestionMaster(questionMasterList);
		Instant finish = Instant.now();
		long timeElapsedMin = Duration.between(start, finish).toMinutes();
		long timeElapsedHr = Duration.between(start, finish).toHours();
		System.out.println("Time Elapsed in Minutes: " +timeElapsedMin); // Prints: Time Elapsed: 2501 
		System.out.println("Time Elapsed in Hours: " +timeElapsedHr);

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

//	@PostMapping(path = "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public Map<String, String> uploadFile(@RequestPart(value = "files", required = true) List<MultipartFile> files)  {
//        List<String> name = azureAdapter.upload(files, "prefix");
//        Map<String, String> result = new HashMap<>();
//       // result.put("key", name);
//        return result;
//    }

	@RequestMapping(value = "/imageFolder", method = RequestMethod.POST)
	public String uploadFolder(@RequestParam(name = "folderName") String folderName) {
		
		Instant start = Instant.now();
//		File[] files = fetchFiles(folderName);
//		List<String> fileNames = new ArrayList<>();
//		for(File f:files) {
//			fileNames.add(f.getName());
//			
//		}
//
		try {
			azureAdapter.upload(folderName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		//updatePhotoUrl(fileNames);
		Instant finish = Instant.now();
		long timeElapsedMin = Duration.between(start, finish).toMinutes();
		long timeElapsedHr = Duration.between(start, finish).toHours();
		
		System.out.println("Time Elapsed in Minutes: " +timeElapsedMin); // Prints: Time Elapsed: 2501 
		System.out.println("Time Elapsed in Hours: " +timeElapsedHr);
		// result.put("key", name);
		return "";
	}
	
//	@RequestMapping(value = "/deleteImages", method = RequestMethod.POST)
//	public String deleteImages(@RequestParam(name = "folderName") String folderName) {
//		
//		Instant start = Instant.now();
//		File[] files = fetchFiles(folderName);
//
//		azureAdapter.del(files);
//		
//		//updatePhotoUrl(fileNames);
//		Instant finish = Instant.now();
//		long timeElapsedMin = Duration.between(start, finish).toMinutes();
//		long timeElapsedHr = Duration.between(start, finish).toHours();
//		System.out.println("Time Elapsed in Minutes: " +timeElapsedMin); // Prints: Time Elapsed: 2501 
//		System.out.println("Time Elapsed in Hours: " +timeElapsedHr);
//		// result.put("key", name);
//		return "";
//	}

//	@GetMapping(path = "/download")
//	public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
//		byte[] data = azureAdapter.getFile(file);
//		ByteArrayResource resource = new ByteArrayResource(data);
//
//		return ResponseEntity.ok().contentLength(data.length).header("Content-type", "application/octet-stream")
//				.header("Content-disposition", "attachment; filename=\"" + file + "\"").body(resource);
//
//	}
	
//	private void updatePhotoUrl(List<String> filesNames) {
//		
//		candidateMasterDataServiceImpl.updatePhotoUrl(filesNames);;
//		
//	}

//	private File[] fetchFiles(String folderName) {
//
//		File directoryPath = new File(folderName);
//		// List of all files and directories
//		File filesList[] = directoryPath.listFiles();
//		System.out.println("List of files and directories in the specified directory:");
//		int counter = 0;
//		for (File file : filesList) {
//			counter++;
//			System.out.println("File name: " + file.getName());
//			System.out.println("File path: " + file.getAbsolutePath());
//			System.out.println("Size :" + file.getTotalSpace());
//			System.out.println(counter + " file is retrieved.");
//			System.out.println(" ");
//			
//		}
//
//		return filesList;
//
//	}
	
	
//	@RequestMapping(value = "/azCopyFolder", method = RequestMethod.POST)
//	public String azCopyFolder(@RequestParam(name = "imagesFolderLocation") String imagesFolderLocation) {
//		uploadFilesAZCopy(imagesFolderLocation);
//		
//		return "Done";
//	}
//	
//	private void uploadFilesAZCopy(String folderPath) {
//		String command="C:\\Users\\admin\\Downloads\\azcopy\\azcopy copy " +folderPath+" sp=r&st=2021-06-23T09:35:14Z&se=2021-06-23T17:35:14Z&spr=https&sv=2020-02-10&sr=c&sig=h%2BLq6PWEwCghbzYDI7N68Rg10s2A7HbBCLCS3Vx8evc%3D --recursive";
//		try {
//		    Process process = Runtime.getRuntime().exec(command);
//		 
//		    BufferedReader reader = new BufferedReader(
//		            new InputStreamReader(process.getInputStream()));
//		    String line;
//		    String numberOfFileTransfers = null;
//		    String numberOfTransfersCompleted = null;
//		    String finalStatus = null;
//		    	
//		    while ((line = reader.readLine()) != null) {
//		    	if (line.contains("Number of File Transfers:")) {
//		    		numberOfFileTransfers = line;
//		    		
//		    	}
//		    	if (line.contains("Number of Transfers Completed:")) {
//		    		numberOfTransfersCompleted = line;
//		    	}
//		    	if (line.contains("Final Job Status:")) {
//		    		finalStatus = line;
//		    	}
//		        System.out.println(line);
//		    }
//		    
//		    
//		 
//		    reader.close();
//		 
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//	}
	
}
