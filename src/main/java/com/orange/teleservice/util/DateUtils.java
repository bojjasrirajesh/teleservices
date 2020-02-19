package com.orange.teleservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private DateUtils() {
		
	}
	
	public static String getCurrentDate() {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    System.out.println(formatter.format(date));
	    
	    
	    return formatter.format(date);  	    
	}	
	
	public static void main(String[] args) {
		getCurrentDate();
	}
}
