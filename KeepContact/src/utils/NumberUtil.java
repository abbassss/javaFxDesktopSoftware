package utils;

/**
 * class for validate number
 * */
public class NumberUtil {
	
	/**
	 * method for validate integer input
	 * @param Integer
	 * @return boolean
	 * */
	public static boolean isEmpty(Integer input) {
		if(input == 0 || input == null) {
			return true;
		}
		return false;
	}
}
