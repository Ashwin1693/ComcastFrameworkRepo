package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropertiesFile(String key) throws IOException
	{
		FileInputStream fis= new FileInputStream("./configAppData/commondata.properties");
		Properties pObj= new Properties(); /*Properties is a Class present in the java class with the help of which 
		we read the data from the property file, works like a map and stores data as key-value */
		
		pObj.load(fis);
		String data = pObj.getProperty(key);
		return data;
	}

}
  // Usage - FileUtility fLib= new FileUtility();
  // String Browser  = fLib.GetDataFromPropertiesFile("browser");
