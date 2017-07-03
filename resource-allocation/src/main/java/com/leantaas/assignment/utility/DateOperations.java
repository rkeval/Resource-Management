package com.leantaas.assignment.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateOperations {

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new DateOperations().convertStringToDate("2017-06-29T13%3A30");
	}*/
	public static Date convertStringToDate(String strDate){
		strDate = strDate.replaceAll("%3A", ":");
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			System.out.println(dateFormat.parse(strDate) );
			return dateFormat.parse(strDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
