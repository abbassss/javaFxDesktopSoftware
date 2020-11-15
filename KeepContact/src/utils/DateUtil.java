package utils;

import java.time.LocalDate;

/**
 * class for validate Date 
 * */
public class DateUtil {
	
	/**
	 * method for validate localDate input
	 * @param LocalDate
	 * @return boolean
	 * */
	public static boolean isEmpty(LocalDate date) {		
		if (date == null) {
			return true;
		}
		return false;
	}
	
}
