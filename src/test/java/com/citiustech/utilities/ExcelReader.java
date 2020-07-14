package com.citiustech.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelReader(String path) {
		this.path = path;

		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		workbook = new XSSFWorkbook();
		sheet = workbook.getSheetAt(0);
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, String colName, int rowNum) {

		int col_Num;
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {

			if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
				col_Num = i;
			}
		}

		if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
			return String.valueOf(cell.getNumericCellValue());

		} else if (cell.getCellType() == CellType.BLANK) {
			return "";
		} else {
			return String.valueOf(cell.getBooleanCellValue());
		}

	}

	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) throws IOException {
		int colNum = 0;
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook();

		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);

		if (row == null)
			row = sheet.createRow(rowNum);

		row = sheet.getRow(0);

		for (int i = 0; i < row.getLastCellNum(); i++) {

			if (row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum = i;
		}
		cell = row.getCell(colNum);
		if (cell == null)
			cell = row.createCell(colNum);

		cell.setCellValue(data);

		fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

		fileOut.close();

		return true;

	}

	// returns true if sheet is created successfully else false
	public boolean addSheet(String sheetname) throws IOException {

		FileOutputStream fileOut;
		workbook.createSheet(sheetname);
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();

		return true;
	}

	// returns true if sheet is removed successfully else false
	public boolean removeSheet(String sheetname) throws IOException {

		int index = workbook.getSheetIndex(sheetname);
		FileOutputStream fileOut;
		workbook.removeSheetAt(index);
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();

		return true;
	}

	// returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName) {

		try {
			fis = new FileInputStream(path);

			workbook = new XSSFWorkbook(fis);

			int index = workbook.getSheetIndex(sheetName);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(colName);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
