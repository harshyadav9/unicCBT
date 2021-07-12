package com.exam.cbt.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.cbt.entity.CandidateResponse;
import com.exam.cbt.service.impl.CandidateResponseServiceImpl;

@Service
public class DownloadExamAnswers {

	Logger log = LoggerFactory.getLogger(DownloadExamAnswers.class);
	
	@Autowired
	CandidateResponseServiceImpl candidateResponseServiceImpl;

	private XSSFWorkbook workbook = new XSSFWorkbook();;
	private XSSFSheet sheet;
	//private List<CandidateResponse> candidateResponseList;

//	public DownloadExamAnswers(List<CandidateResponse> candidateResponses) {
//		this.candidateResponseList = candidateResponses;
//		workbook = new XSSFWorkbook();
//	}

	private void writeHeaderLine(String examCd, String instCd, int year) {
		
		sheet = workbook.createSheet(examCd.concat("_").concat(instCd).concat("_")+year);
		

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "RegistrationNo", style);
		createCell(row, 1, "ExamCd", style);
		createCell(row, 2, "InstCd", style);
		createCell(row, 3, "Year", style);
		createCell(row, 4, "QuestionNo", style);
		createCell(row, 5, "Answer", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof String) {
			cell.setCellValue((String) value);
		} 
	}

	private void writeDataLines(String examCd, String instCd, int year) {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		
		List<CandidateResponse> candidateResponseList = candidateResponseServiceImpl.getCandidateAnswersForGivenExam(examCd, instCd, year);

		for (CandidateResponse candidateResponse : candidateResponseList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, candidateResponse.getId().getRegistrationNo(), style);
			createCell(row, columnCount++, candidateResponse.getExamCd(), style);
			createCell(row, columnCount++, candidateResponse.getInstCd(), style);
			createCell(row, columnCount++, candidateResponse.getId().getYear(), style);
			createCell(row, columnCount++, candidateResponse.getId().getQuestionNo(), style);
			createCell(row, columnCount++, candidateResponse.getAnswer(), style);

		}
	}

	public ByteArrayInputStream export(HttpServletResponse response, String examCd, String instCd, int year) throws IOException {
		writeHeaderLine(examCd,instCd,year);
		writeDataLines(examCd,instCd,year);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		workbook.write(out);
		return new ByteArrayInputStream(out.toByteArray());

	}
}
