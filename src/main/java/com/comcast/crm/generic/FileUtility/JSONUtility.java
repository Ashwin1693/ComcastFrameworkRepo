package com.comcast.crm.generic.FileUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.google.protobuf.TextFormat.ParseException;

public class JSONUtility {
	
	public String geDataFromJSONFile(String key) throws Throwable, IOException, ParseException{
		
		FileReader fileR =new FileReader("./configAppData/appCommonData.json");/*FileReader is like opening a book
		to read it */
		JSONParser parser= new JSONParser();  /*parser object understands JSON format, like a translator for 
												JSON Language */
		Object obj = parser.parse(fileR);    //Reads the JSON file and Converts it into Java project
		JSONObject map= (JSONObject) obj;    // Now the JSON data becomes a key value map
		String data= (String) map.get(key);  //Fetches the value from JSON
		return data;	
		
	}
}

//Now we can do String UserName= jLib.geDataFromJSONFile("username");