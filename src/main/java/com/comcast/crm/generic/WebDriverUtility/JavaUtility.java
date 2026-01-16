package com.comcast.crm.generic.WebDriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random random= new Random();
		int randomCount= random.nextInt(1000);
		return randomCount;
		 
	}

		public String getSystemDateMMDDYYYY() {
			Date d= new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			String date= sdf.format(d);
			return date;
		}
		 
		public String getRequiredDateMMDDYYYY(int days) {
			Date d= new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			sdf.format(d);
			Calendar cal= sdf.getCalendar();
			cal.add(Calendar.DAY_OF_MONTH, days);
			String ReqDate= sdf.format(cal.getTime());
			return ReqDate;
		}
		
		
		}
		
		
	