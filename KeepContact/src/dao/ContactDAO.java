package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ContactModel;
import model.SalutationModel;;

/**
 * class for database queries
 * */
public class ContactDAO {
	
	/**
	 * method for database insert query
	 * @param ContactModel
	 * @return boolean
	 * */
	public static boolean insert_user(ContactModel model) throws SQLException {
		String sql = "INSERT INTO \"contact\"(firstname, lastname, street, street_nr, birthday,salutation_id)VALUES (?, ?, ?,?, ?, ?);";
		try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql)) {
			model.setStreet(model.getStreet());
			ps.setString(1, model.getFirstname());
			ps.setString(2, model.getLastname());
			ps.setString(3, model.getStreet());
			ps.setString(4, model.getStreet_number());
			ps.setTimestamp(5, model.getBirthday());
			ps.setInt(6, model.getSalutation_id());

			if (ps.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		}

	}
	
	/**
	 * method for database update query
	 * @param ContactModel
	 * @return boolean
	 * */
	public static boolean update_user(ContactModel model) {
		String sql = "UPDATE \"contact\" SET firstname = ? , lastname = ? , street = ?, street_nr = ?, birthday = ? , salutation_id = ? WHERE  id =?";

		try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql)) {
			ps.setString(1, model.getFirstname());
			ps.setString(2, model.getLastname());
			ps.setString(3, model.getStreet());
			ps.setString(4, model.getStreet_number());
			ps.setTimestamp(5, model.getBirthday());
			ps.setInt(6, model.getSalutation_id());
			ps.setLong(7, model.getId());

			if (ps.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error while updating user");
			System.err.println(e);
		}

		return true;
	}
	
	/**
	 * method for database delete query
	 * @param id
	 * @return boolean
	 * */
	public static boolean delete_user(long id) throws SQLException {
		String sql = "DELETE FROM \"contact\" WHERE id = ?";

		try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql)) {
			ps.setLong(1, id);
			if (ps.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}

		}
	}
	
	/**
	 * method for database query that return all contact table records
	 * @return ArrayList<ContactModel>
	 * */
	public static ArrayList<ContactModel> get_Users_List() throws SQLException {
		ArrayList<ContactModel> contactList = new ArrayList<ContactModel>();
		String sql = "SELECT id,firstname,lastname,fullname,name,address,birthday,street,street_nr,salutation_id FROM \"contact_view\" ";
		try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs != null) {					
					while (rs.next()) {
						contactList.add(new ContactModel(rs.getLong("id"),rs.getString("firstname"), rs.getString("lastname"),
								rs.getString("fullname"), rs.getString("name"), rs.getString("address"),
								rs.getTimestamp("birthday"),rs.getString("street"),rs.getString("street_nr"),rs.getInt("salutation_id")));
					}
				}
			}

		} 
		return contactList;
	}
	
	/**
	 * method for database query that return match records with input model
	 * @param ContactModel
	 * @return ArrayList<ContactModel>
	 * */
	public static ArrayList<ContactModel> filter(ContactModel model) throws SQLException {
		ArrayList<ContactModel> contactList = new ArrayList<ContactModel>();
		String sql = "SELECT * FROM \"contact_view\" WHERE fullname = ? OR birthday = ?";				
		try(PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(sql)){
			ps.setString(1, model.getFullname());
			ps.setTimestamp(2, model.getBirthday());			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs != null) {					
					while (rs.next()) {
						contactList.add(new ContactModel(rs.getLong("id"),rs.getString("firstname"), rs.getString("lastname"),
								rs.getString("fullname"), rs.getString("name"), rs.getString("address"),
								rs.getTimestamp("birthday"),rs.getString("street"),rs.getString("street_nr")));						
					}
				}
			}
		}
		return contactList;
	}
	
	/**
	 * method for database query that return all salutation table records
	 * @return ArrayList<ContactModel>
	 * */
	public static ArrayList<SalutationModel> getSalutation() {
		ArrayList<SalutationModel> model = new ArrayList<SalutationModel>();
		String query = "SELECT * FROM \"salutation_typ\"";
		try (PreparedStatement ps = DBConnection.getDBConnection().prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery()) {
				if (rs != null) {
					while (rs.next()) {
						model.add(new SalutationModel(rs.getInt("id"),rs.getString("name")));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error in get_salute_list");
			e.printStackTrace();
		}
		return model;
	}

}
