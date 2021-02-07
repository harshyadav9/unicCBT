package com.exam.cbt.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exam.cbt.entity.Student;
import com.exam.cbt.service.impl.UploadDataServiceImpl;  

@RestController
@RequestMapping("/upload")
//@CrossOrigin(origins = "http://localhost:8080")
public class StudentDataUploadController {
	Logger log = LoggerFactory.getLogger(StudentDataUploadController.class); 
	
	@Autowired
	UploadDataServiceImpl uploadDataService;
	
	@PostMapping(value = "/import-excel")
	ResponseEntity<List<Student>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<Student> stuList = new ArrayList<>();
        
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Student student = new Student();

                XSSFRow row = worksheet.getRow(index);
                Integer id = (int) row.getCell(0).getNumericCellValue();

                student.setRegistrationNo(id);
               // student.setStudentName(row.getCell(1).getStringCellValue());
                if(row.getCell(2).getCellType()==CellType.STRING) 
                	 student.setPassword(row.getCell(2).getStringCellValue());
                else if(row.getCell(2).getCellType()==CellType.NUMERIC) {
                student.setPassword(String.valueOf((int) row.getCell(2).getNumericCellValue()));
                }

                stuList.add(student);
            }
        }

        //insert into db
        uploadDataService.uploadStudents(stuList);
		
        return new ResponseEntity<>(stuList, status);
    }

	
	
}
	