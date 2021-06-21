package com.exam.cbt.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.entity.QuestionMasterId;

@Component
public class ReadExcelData {
	
	Logger log = LoggerFactory.getLogger(ReadExcelData.class); 
	List<QuestionMaster> questionMasterList = new ArrayList<QuestionMaster>();
	
	public List<QuestionMaster> readExcelData(MultipartFile file) throws IOException {
		
		   XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
	        XSSFSheet worksheet = workbook.getSheetAt(0);

	        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
	            if (index > 0) {
	            	
	            	log.info("Processing row :" +index);
	            
	            	QuestionMaster questionMaster = new QuestionMaster();
	                XSSFRow row = worksheet.getRow(index);
	                if (null != row.getCell(0)) {
	                	QuestionMasterId id = new QuestionMasterId();
	               
		                if (null != row.getCell(0).getStringCellValue()) {
		                	id.setInstCd(String.valueOf(row.getCell(0).getStringCellValue()));
		                	 //questionMaster.setId();(String.valueOf(row.getCell(0).getStringCellValue()));
		                }
		                if(null != row.getCell(1).getStringCellValue()) {
		                	id.setExamCd(row.getCell(1).getStringCellValue());
		                	 //questionMaster.setExamCd(row.getCell(1).getStringCellValue());
		                }
		                	 
		             //   questionMaster.setYear((int) row.getCell(2).getNumericCellValue());
		                id.setYear((int) row.getCell(2).getNumericCellValue());
		                
		                if(null != row.getCell(3).getStringCellValue()) {
		                	 questionMaster.setMultiple(row.getCell(3).getStringCellValue());
		                }
		                id.setQuestNum((int) row.getCell(4).getNumericCellValue());
		                questionMaster.setId(id);
		                
		                if(null != row.getCell(5).getStringCellValue()) {
		                	 questionMaster.setQuestion(row.getCell(5).getStringCellValue());
		                }
		                
		                if(null != row.getCell(6).getStringCellValue()) {
		                	 questionMaster.setOption1(row.getCell(6).getStringCellValue());
		                }
		                if(null != row.getCell(7).getStringCellValue()) {
		                	 questionMaster.setOption2(row.getCell(7).getStringCellValue());
		                }
		                if(null != row.getCell(8).getStringCellValue()) {
		                	 questionMaster.setOption3(row.getCell(8).getStringCellValue());
		                }
		                if(null != row.getCell(9).getStringCellValue()) {
		                	 questionMaster.setOption4(row.getCell(9).getStringCellValue());
		                }
		                if(null != row.getCell(10).getStringCellValue()) {
		                	questionMaster.setCorrectAnswer(row.getCell(10).getStringCellValue());
		                }
		                questionMaster.setCorrectAnsWeightage(new Float(row.getCell(11).getNumericCellValue()));
		                questionMaster.setWrongAnsWeightage(new Float(row.getCell(12).getNumericCellValue()));
		                questionMaster.setUnattemptedAnsWeightage(new Float(row.getCell(13).getNumericCellValue()));

		                questionMasterList.add(questionMaster);

	            	}

	            }
	        }
	        workbook.close();
	        
	        return questionMasterList;
		
	}

}
