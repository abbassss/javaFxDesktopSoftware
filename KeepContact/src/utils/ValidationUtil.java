package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.ContactModel;

/**
 * class to done all validation once
 * */
public class ValidationUtil {
	
	/**
	 * method for validate ContactModel fields
	 * @param ContactModel
	 * @return boolean
	 * */
	public static boolean isValid_user(ContactModel model) {

		Alert alert = new Alert(AlertType.ERROR); 
		if (StringUtil.isEmpty(model.getFirstname())) {			  
	        alert.setContentText("Firstname is required");
	        alert.show();
	        return false;
		} else if (StringUtil.isEmpty(model.getLastname())) {			
	        alert.setContentText("Lastname is required");
	        alert.show();
	        return false;
		} else if (StringUtil.isEmpty(model.getStreet())) {
			alert.setContentText("Street is required");
			alert.show();
			return false;
		}else if (StringUtil.isEmpty(model.getStreet_number())) {
			alert.setContentText("Street number is required");
			alert.show();
			return false;
		}
		return true;
	}
}
