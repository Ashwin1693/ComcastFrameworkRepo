package practice.test;

import com.comcast.crm.generic.FileUtility.ExcelUtility;

public class countrows {
public static void main(String[] args) throws Throwable {
	ExcelUtility eLib = new ExcelUtility();
	int rowcount= eLib.getRowCount("Product");
	System.out.println(rowcount);
	
}
}
