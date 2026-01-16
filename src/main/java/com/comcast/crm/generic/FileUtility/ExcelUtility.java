package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String SheetName, int RowNum, int CellNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis= new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		  String data= wb.getSheet(SheetName).getRow(RowNum).getCell(CellNum).getStringCellValue();//reads specific cell value
		  wb.close();
		  return data;
	
	}
	/* usage- String username= eLib.getDataFromExcel("Login",1,0) */
	public int getRowCount(String SheetName) throws Throwable{
		FileInputStream fis= new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		int rowCount = wb.getSheet(SheetName).getLastRowNum();
		wb.close();
		return rowCount;
		
	}
	 public void setDataIntoExcel(String sheetName,int rowNum,int cellNum,String data) throws EncryptedDocumentException, IOException {
		 FileInputStream fis= new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		 Workbook wb = WorkbookFactory.create(fis);
		 wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data); /* Creating a cell and 
		 																				puts text back into the cell */
		 FileOutputStream fos= new FileOutputStream("./TestScriptData/TestScriptData.xlsx");
		 wb.write(fos);
		 wb.close();
	 }
	 

}
