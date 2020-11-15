package utils;

import java.sql.Timestamp;

//class for validate timestamp
public class TimestampUtil {
	
	/**
	 * method for validate Timestamp input
	 * @param Timestamp
	 * @return boolean
	 * */
	public static boolean isEmpty(Timestamp input) {
		if(input == null) {
			return true;
		}
		
		return false;
	}
}
