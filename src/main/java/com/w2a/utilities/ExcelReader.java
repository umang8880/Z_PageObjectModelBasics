package com.w2a.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelReader {
	public static String path;

	public ExcelReader(String path) {
	this.path = path;
	}

	@DataProvider(name="dp1")
	public static Object[][] getdata(Method m) throws EncryptedDocumentException, IOException{	
		
		File f = new File(path);		
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet0 = workbook.getSheet(m.getName());
		Row row0 = sheet0.getRow(0); 
		
		int totalRows = sheet0.getPhysicalNumberOfRows()-1;
		int totalCols = row0.getPhysicalNumberOfCells();
		 		
		Object[][] data = new Object[totalRows][totalCols]; //[totalRows][totalCols] Total# rows and cols
		
		for (int rownum=1; rownum<=totalRows; rownum++) {
			row0 = sheet0.getRow(rownum);
			for(int colnum=0; colnum<totalCols; colnum++) {
			data[rownum-1][colnum] = row0.getCell(colnum).getStringCellValue();
			}
		}
		fis.close();
		return data;
	}
	
	@DataProvider(name="dp2")
	public static Object[][] getDatafromHash(Method m) throws EncryptedDocumentException, IOException{	
		
		File f = new File(path);		
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet0 = workbook.getSheet(m.getName());
		Row row = sheet0.getRow(0); 
		
		int totalRows = sheet0.getPhysicalNumberOfRows()-1;
		int totalCols = row.getPhysicalNumberOfCells();
		 		
		Object[][] data = new Object[totalRows][1]; //[totalRows][totalCols] Total# rows and cols
		Hashtable<String,String> table = null;
		for (int rownum=1; rownum<=totalRows; rownum++) {
			table = new Hashtable<String,String>();
			row = sheet0.getRow(rownum);
			for(int colnum=0; colnum<totalCols; colnum++) {
				table.put(sheet0.getRow(0).getCell(colnum).getStringCellValue(), row.getCell(colnum).getStringCellValue());
				data[rownum-1][0] = table;
			}
		}
		fis.close();
		return data;
	}

	public static boolean isTestRunnable(String testName) throws EncryptedDocumentException, IOException {
		
		File f = new File(path);		
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet0 = workbook.getSheet("test_suits");
		Row row0 = sheet0.getRow(0); 
		
		int totalRows = sheet0.getPhysicalNumberOfRows()-1;

		for (int rownum=1; rownum<=totalRows; rownum++) {
			row0 = sheet0.getRow(rownum);
			if(testName.equals(row0.getCell(0).getStringCellValue())){
				String runnmode= row0.getCell(1).getStringCellValue();
				if(runnmode.equalsIgnoreCase("Y")) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;	
	}
}
