package utils;

/**
 * class for validate string
 * */
public class StringUtil {
	
	/**
	 * method for validate string input
	 * @param String
	 * @return boolean
	 * */
	public static boolean isEmpty(String input) {
		if(input == null || input.trim().isEmpty() || input.length()==0) {
			return true;
		}
		return false;
	}
}
