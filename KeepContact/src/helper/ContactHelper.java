package helper;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.ContactDAO;
import model.ContactModel;
import model.SalutationModel;

/**
 * helper class for database connection
 * */
public class ContactHelper {
	/**
	 * method for create user
	 * @param ContactModle
	 * @return boolean
	 * */
	public static boolean createNewUser(ContactModel model) throws SQLException {
		return ContactDAO.insert_user(model);
	}
	
	/**
	 * method for update user
	 * @param ContactModle
	 * @return boolean
	 * */
	public static boolean updateUser(ContactModel model) {
		return ContactDAO.update_user(model);
	}
	
	/**
	 * method for delete user
	 * @param id
	 * @return boolean
	 * */
	public static boolean deleteUser(int id) throws SQLException {
		return ContactDAO.delete_user(id);
	}
	
	/**
	 * method for filter table
	 * @param ContactModle
	 * @return ArrayList<ContactModel>
	 * */
	public static ArrayList<ContactModel> tableFilter(ContactModel model) throws SQLException{
		return ContactDAO.filter(model);
	}
	
	/**
	 * method for get all user
	 * @return ArrayList<ContactModel>
	 * */
	public static ArrayList<ContactModel> getUserList() throws SQLException {
		return ContactDAO.get_Users_List();
	}
}
