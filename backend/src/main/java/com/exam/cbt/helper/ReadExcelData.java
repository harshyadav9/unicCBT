package com.exam.cbt.helper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.exam.cbt.entity.CandidateMaster;
import com.exam.cbt.entity.Config;
import com.exam.cbt.entity.ConfigId;
import com.exam.cbt.entity.ExamYearMaster;
import com.exam.cbt.entity.ExamYearMasterId;
import com.exam.cbt.entity.InstituteNameMaster;
import com.exam.cbt.entity.InstituteNameMasterId;
import com.exam.cbt.entity.QuestionMaster;
import com.exam.cbt.entity.QuestionMasterId;

@Component
public class ReadExcelData {

	Logger log = LoggerFactory.getLogger(ReadExcelData.class);
	List<QuestionMaster> questionMasterList = new ArrayList<QuestionMaster>();
	List<InstituteNameMaster> instituteNameMasterList = new ArrayList<InstituteNameMaster>();
	List<ExamYearMaster> examYearMasterList = new ArrayList<ExamYearMaster>();
	List<Config> configList = new ArrayList<Config>();
	List<CandidateMaster> candidateMasterList = new ArrayList<CandidateMaster>();

	@Value("${candidate.master.excel.sheet.inputName}")
	private String candidateMasterSheetName;

	@Value("${config.excel.sheet.inputName}")
	private String configSheetName;

	@Value("${exam.master.excel.sheet.inputName}")
	private String examMasterSheetName;

	@Value("${inst.master.excel.sheet.inputName}")
	private String instituteMasterSheetName;

	@Value("${question.master.excel.sheet.inputName}")
	private String questionMasterSheetName;

	public List<QuestionMaster> readQuestionMasterData(MultipartFile file) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet sheet = workbook.getSheet(questionMasterSheetName);
		Iterator<Row> rows = sheet.rowIterator();

		// for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
		while (rows.hasNext()) {
			Row row = rows.next();
			if (row.getRowNum() == 0) {
				continue;
			}
			// if (index > 0) {

			log.info("Processing row :" + row.getRowNum());

			QuestionMaster questionMaster = new QuestionMaster();
			// XSSFRow row = worksheet.getRow(index);
			if (null != row && null != row.getCell(0)) {
				QuestionMasterId id = new QuestionMasterId();

				if (null != row.getCell(0).getStringCellValue()) {
					id.setInstCd(String.valueOf(row.getCell(0).getStringCellValue()));
				}
				if (null != row.getCell(1).getStringCellValue()) {
					id.setExamCd(row.getCell(1).getStringCellValue());
				}
				// if ((int) row.getCell(2).getNumericCellValue() != 0) {
				id.setYear((int) row.getCell(2).getNumericCellValue());
				// }

				if (null != row.getCell(3).getStringCellValue()) {
					questionMaster.setMultiple(row.getCell(3).getStringCellValue());
				}

				id.setQuestionNo((int) row.getCell(4).getNumericCellValue());

				questionMaster.setId(id);

				//if (id != null) {
					if (null != row.getCell(5).getStringCellValue()) {
						questionMaster.setQuestion(row.getCell(5).getStringCellValue());
					}

					if (null != row.getCell(6).getStringCellValue()) {
						questionMaster.setOption1(row.getCell(6).getStringCellValue());
					}
					if (null != row.getCell(7).getStringCellValue()) {
						questionMaster.setOption2(row.getCell(7).getStringCellValue());
					}
					if (null != row.getCell(8).getStringCellValue()) {
						questionMaster.setOption3(row.getCell(8).getStringCellValue());
					}
					if (null != row.getCell(9).getStringCellValue()) {
						questionMaster.setOption4(row.getCell(9).getStringCellValue());
					}
					if (null != row.getCell(10).getStringCellValue()) {
						questionMaster.setCorrectAnswer(row.getCell(10).getStringCellValue());
					}
					questionMaster.setCorrectAnsWeightage(new Float(row.getCell(11).getNumericCellValue()));
					questionMaster.setWrongAnsWeightage(new Float(row.getCell(12).getNumericCellValue()));
					questionMaster.setUnattemptedAnsWeightage(new Float(row.getCell(13).getNumericCellValue()));
					questionMaster.setSetNo((int) row.getCell(14).getNumericCellValue());

					questionMasterList.add(questionMaster);

				}

			//}

		}
		// }
		workbook.close();

		// }

		// }
		return questionMasterList;

	}

	public List<InstituteNameMaster> readInstituteMasterData(MultipartFile file) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheet(instituteMasterSheetName);

		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {

				log.info("Processing row :" + index);

				InstituteNameMaster instituteNameMaster = new InstituteNameMaster();
				XSSFRow row = worksheet.getRow(index);
				if (null != row && null != row.getCell(0)) {
					InstituteNameMasterId id = new InstituteNameMasterId();

					if (null != row.getCell(0).getStringCellValue() && row.getCell(0).getStringCellValue() != "") {
						id.setInstCd(String.valueOf(row.getCell(0).getStringCellValue()));
					}
					if (null != row.getCell(1).getStringCellValue() && row.getCell(1).getStringCellValue() != "") {
						id.setExamCd(row.getCell(1).getStringCellValue());
					}

					if ((int) row.getCell(2).getNumericCellValue() != 0) {
						id.setYear((int) row.getCell(2).getNumericCellValue());
					}

					if (id != null && id.getExamCd() != null & id.getExamCd() != ""
							&& id.getInstCd() != null & id.getInstCd() != "" && id.getYear() != 0) {

						instituteNameMaster.setId(id);
					}

					if (null != row.getCell(3).getStringCellValue()) {
						instituteNameMaster.setInstName(row.getCell(3).getStringCellValue());
					}

					instituteNameMasterList.add(instituteNameMaster);
				}
			}
		}
		workbook.close();

		return instituteNameMasterList;

	}

	public List<ExamYearMaster> readExamYearMasterData(MultipartFile file) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheet(examMasterSheetName);

		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {

				log.info("Processing row :" + index);

				ExamYearMaster examYearMaster = new ExamYearMaster();
				XSSFRow row = worksheet.getRow(index);
				if (null != row && null != row.getCell(0)) {
					ExamYearMasterId id = new ExamYearMasterId();

					if (null != row.getCell(0).getStringCellValue() && row.getCell(0).getStringCellValue() != "") {
						id.setInstCd(String.valueOf(row.getCell(0).getStringCellValue()));
					}
					if (null != row.getCell(1).getStringCellValue() && row.getCell(1).getStringCellValue() != "") {
						id.setExamCd(row.getCell(1).getStringCellValue());
					}

					if ((int) row.getCell(2).getNumericCellValue() != 0) {
						id.setYear((int) row.getCell(2).getNumericCellValue());
					}

					if (id != null && id.getExamCd() != null & id.getExamCd() != ""
							&& id.getInstCd() != null & id.getInstCd() != "" && id.getYear() != 0) {

						examYearMaster.setId(id);
					}

					if (null != row.getCell(3).getStringCellValue()) {
						examYearMaster.setExamName(row.getCell(3).getStringCellValue());
					}

					examYearMasterList.add(examYearMaster);
				}
			}
		}
		workbook.close();

		return examYearMasterList;

	}

	public List<Config> readConfigData(MultipartFile file) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheet(configSheetName);

		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {

				log.info("Processing row :" + index);

				Config config = new Config();
				XSSFRow row = worksheet.getRow(index);
				if (null != row && null != row.getCell(0)) {
					ConfigId id = new ConfigId();

					if (null != row.getCell(0).getStringCellValue() && row.getCell(0).getStringCellValue() != "") {
						id.setInstCd(String.valueOf(row.getCell(0).getStringCellValue()));
					}
					if (null != row.getCell(1).getStringCellValue() && row.getCell(1).getStringCellValue() != "") {
						id.setExamCd(row.getCell(1).getStringCellValue());
					}

					//if ((int) row.getCell(2).getNumericCellValue() != 0) {
						id.setYear((int) row.getCell(2).getNumericCellValue());
					//}

					if (id != null && id.getExamCd() != null & id.getExamCd() != ""
							&& id.getInstCd() != null & id.getInstCd() != "") {

						config.setId(id);
					}

					if (id != null) {
						if (null != row.getCell(3).getDateCellValue()) {
							Date d = row.getCell(3).getDateCellValue();
							java.sql.Date sd = new java.sql.Date(d.getTime());
							config.setDateOfExam(sd);
						}

						if (null != row.getCell(4).getLocalDateTimeCellValue()) {

							config.setDurationHr(fetchHour(row.getCell(4).getLocalDateTimeCellValue()));
							config.setDurationMin(fetchMin(row.getCell(4).getLocalDateTimeCellValue()));
						}
						
						config.setSetStart((int) row.getCell(6).getNumericCellValue());
						config.setNoOfSet((int) row.getCell(5).getNumericCellValue());
						configList.add(config);

					}

				}

			}
		}
		workbook.close();

		return configList;

	}

	public List<CandidateMaster> readCandidateMasterData(MultipartFile file) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
		XSSFSheet worksheet = workbook.getSheet(candidateMasterSheetName);

		for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
			if (index > 0) {

				log.info("Processing row :" + index);

				CandidateMaster candidateMaster = new CandidateMaster();
				XSSFRow row = worksheet.getRow(index);
				if (null != row && null != row.getCell(0)) {

					if (null != row.getCell(0).getStringCellValue()) {
						candidateMaster.setInstCd(String.valueOf(row.getCell(0).getStringCellValue()));
					}
					if (null != row.getCell(1).getStringCellValue()) {
						candidateMaster.setExamCd(row.getCell(1).getStringCellValue());
					}

					candidateMaster.setYear((int) row.getCell(2).getNumericCellValue());

					candidateMaster.setRegistrationNo((int) row.getCell(3).getNumericCellValue());

					if (null != row.getCell(4).getStringCellValue()) {
						candidateMaster.setName(row.getCell(4).getStringCellValue());
					}

					if (null != row.getCell(5)) {
						candidateMaster.setPassword(row.getCell(5).getStringCellValue());
					}

					if (null != row.getCell(6).getDateCellValue()) {
						Date d = row.getCell(6).getDateCellValue();
						java.sql.Date sd = new java.sql.Date(d.getTime());
						candidateMaster.setDob(sd);
					}

					if (null != row.getCell(7).getStringCellValue()) {
						candidateMaster.setPhoto(row.getCell(7).getStringCellValue());
					}

					candidateMaster.setMobileNum((int) row.getCell(8).getNumericCellValue());

					if (null != row.getCell(9).getStringCellValue()) {
						candidateMaster.setEmailId(row.getCell(9).getStringCellValue());
					}

					candidateMasterList.add(candidateMaster);
				}
			}
		}
		workbook.close();

		return candidateMasterList;
	}

	private int fetchHour(LocalDateTime data) {
		return data.getHour();
	}

	private int fetchMin(LocalDateTime data) {
		return data.getMinute();
	}
}
