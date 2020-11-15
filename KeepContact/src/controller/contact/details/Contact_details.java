package controller.contact.details;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import dao.ContactDAO;
import helper.ContactHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ContactModel;
import model.SalutationModel;
import navigation.LoadMainstage;
import utils.DateUtil;
import utils.ValidationUtil;

public class Contact_details {

	@FXML
	private TextField txtf_firstname;

	@FXML
	private TextField txtf_street;

	@FXML
	private TextField txtf_lastname;

	@FXML
	private TextField txtf_street_number;

	@FXML
	private Button btn_back;

	@FXML
	private ComboBox<SalutationModel> combo_salute_name;

	@FXML
	private GridPane gridp_contact_table;

	@FXML
	private Button btn_update;

	@FXML
	private DatePicker datep_birthday;
	
	/**
	 * initial method of contact_detail page which initial 
	 * components and set page listeners
	 * @param Stage take current stage
	 * @param ContactModel to initial fields	
	 * @return void
	 * */
	public void init(Stage primaryStage,ContactModel contact) {
		initComponents();
		setListener(primaryStage,contact);
	}
	
	/**
     * method to initial components inside the contact_detail page
     * @return void
     * */
	public void initComponents() {
		//fill gender combo box
		combo_salute_name.setItems(FXCollections.observableArrayList(ContactDAO.getSalutation()));
	}
	
	/**
	 * method for set page buttons and events
	 * @param Stage take current stage 
	 * @return void
	 * */
	private void setListener(Stage primaryStage,ContactModel contact) {

		try {
			//handle back button
			btn_back.setOnAction((event) -> {
				LoadMainstage mainStage = new LoadMainstage();
				mainStage.loadMainstagePage(primaryStage);
				//Navigation.loadPage(primaryStage, View.CONTACT_ALL);
			});
			
			//handle update button
			btn_update.setOnAction((event) -> {

				try {
					ContactModel model = getCurrentModel(contact);
					
					if (model.getSalutation_id() != 0 && model.getBirthday() != null) {
						if (!ValidationUtil.isValid_user(model)) {
							return;
						}
						try {
							if (ContactHelper.updateUser(model)) {
								showAlert("confirmation", "contact updated");
								LoadMainstage mainStage = new LoadMainstage();
								mainStage.loadMainstagePage(primaryStage);
							} else {
								showAlert("Error", "contact did not update");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else {
						return;
					}

				} catch (Exception e) {
					System.out.println("error in update button");
					e.printStackTrace();
				}

			});

		} catch (Exception e) {
			System.out.println("Error happened in contact detail init");
			System.err.println(e);
		}

	}
	
	/**
	 * load method of contact_detail class which fill text fields when class called
	 * @param ContactModel
	 * @return void
	 * */
	public void load(ContactModel contact) throws IOException {
		try {
			//load contact values into text fields 
			txtf_firstname.setText(contact.getFirstname());

			txtf_lastname.setText(contact.getLastname());

			txtf_street.setText(contact.getStreet());

			txtf_street_number.setText(contact.getStreet_number());
			
			//test
			/*System.out.println(contact.getSalModel().getId());
			System.out.println(contact.getSalutation_id());
			System.out.println(contact.getSalutation_name());*/
			
			SalutationModel salutationModel = null;			
			for(SalutationModel current_salutationModel : FXCollections.observableArrayList(ContactDAO.getSalutation())) {
				if(current_salutationModel.getId() == contact.getSalutation_id()) {
					salutationModel = current_salutationModel;	
				}				
			}
			combo_salute_name.getSelectionModel().select(salutationModel);

			Timestamp timestamp = contact.getBirthday();
			LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
			datep_birthday.setValue(localDate);

		} catch (Exception e) {
			System.out.println("error in contact detail controller");
			e.printStackTrace();
		}

	}
	
	/**
     * method to get user inputs from text fields 
     * @param ContactModel
     * @return ContactModel
     * */
	public ContactModel getCurrentModel(ContactModel contact) {		
		contact.setFirstname(txtf_firstname.getText().toLowerCase());
		contact.setLastname(txtf_lastname.getText().toLowerCase());
		contact.setStreet(txtf_street.getText().toLowerCase());
		contact.setStreet_number(txtf_street_number.getText().toLowerCase());		
		//contact.setSalutation_id(combo_salute_name.getSelectionModel().getSelectedItem().getId());
		
		if (combo_salute_name.getSelectionModel().getSelectedItem() != null) {
			contact.setSalutation_id(combo_salute_name.getSelectionModel().getSelectedItem().getId());
		}
		else {
			contact.setSalutation_id(0);
			showAlert("Error", "Gender required");
		}
		
		if (DateUtil.isEmpty(datep_birthday.getValue()) ==false) {
			contact.setBirthday(convertToTimestamp(datep_birthday.getValue()));
		}
		else {
			contact.setBirthday(null);
			showAlert("Error", "bithday requierd");
		}
		return contact;
	}

	/**
     * method for convert LocalDate to timestamp
     * @param LocalDate
     * @return Timestamp
     * */
	public static Timestamp convertToTimestamp(LocalDate birthday) {
		Date date = Date.valueOf(birthday);
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
	
	/**
	 * method for show alert to user
	 * @param title
	 * @param content
	 * @return void
	 * */
	private void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);

		alert.setContentText(content);

		alert.showAndWait();
	}

}
