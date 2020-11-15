package controller.contact.add;

import java.sql.Date;
import java.sql.SQLException;
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

public class Contact_add {
	
	@FXML
    private TextField txtf_firstname;

    @FXML
    private TextField txtf_street;

    @FXML
    private Button btn_add;

    @FXML
    private TextField txtf_lastname;

    @FXML
    private TextField txtf_street_number;

    @FXML
    private Button btn_back;

    @FXML
    private ComboBox<SalutationModel> combo_salute;

    @FXML
    private GridPane gridp_contact_table;

    @FXML
    private DatePicker datep_birthday;
    
    /**
	 * initial method of add_contact page which initial 
	 * components and set page listeners
	 * @param Stage take current stage  	
	 * @return void
	 * */
    public void init(Stage primaryStage) {   
    	initComponentes();
    	setListener(primaryStage);
	}
    
    /**
     * method to initial components inside the add page
     * @return void
     * */
    private void initComponentes() {
    	//fill gender combo box
		combo_salute.setItems(FXCollections.observableArrayList(ContactDAO.getSalutation()));
    }
    
    /**
	 * method for set page buttons and events
	 * @param Stage take current stage 
	 * @return void
	 * */
    private void setListener(Stage primaryStage) {
    	
    	//handle back button
		btn_back.setOnAction((event) -> {
			LoadMainstage mainStage = new LoadMainstage();
			mainStage.loadMainstagePage(primaryStage);
    	});
    	
		//handle add button
    	btn_add.setOnAction((event) -> {
    		
    		try {    			
    			ContactModel model = getCurrentModel();       			
    			if (model.getSalutation_id() != 0 && model.getBirthday() != null) {
    				//validate user input
    				if (ValidationUtil.isValid_user(model)==false) {
        				return ;
        			}
    				// add user 
            		try {            			
            			if (ContactHelper.createNewUser(model)) {
            				showAlert("confirmation", "contact added successfully");

                    		LoadMainstage mainStage = new LoadMainstage();
                			mainStage.loadMainstagePage(primaryStage);
            			} else {
            				showAlert("Error", "contact did not add because of internal error");
            			}
            		} catch (SQLException e) {
            			e.printStackTrace();
            		}
				}
    			
			} catch (Exception e) {
				System.out.println("error in add contact init");
				e.printStackTrace();
			}
    	});
		
	} 

    /**
     * method to get user inputs from text fields 
     * @return ContactModel
     * */
    public ContactModel getCurrentModel() {
    	ContactModel model = new ContactModel();            		
		model.setFirstname(txtf_firstname.getText().toLowerCase());
		model.setLastname(txtf_lastname.getText().toLowerCase());
		model.setStreet(txtf_street.getText().toLowerCase());
		model.setStreet_number(txtf_street_number.getText().toLowerCase());		
		
		//gender validation
		if (combo_salute.getSelectionModel().getSelectedItem() != null) {
			model.setSalutation_id(combo_salute.getSelectionModel().getSelectedItem().getId());
		}
		else {
			model.setSalutation_id(0);
			showAlert("Error", "Gender required");
		}

		//birthday validation
		if (DateUtil.isEmpty(datep_birthday.getValue()) ==false) {
			model.setBirthday(convertToTimestamp(datep_birthday.getValue()));
		}
		else {
			model.setBirthday(null);
			showAlert("Error", "bithday requierd");
		}

		return model;
    }
	/**
	 * method for show alert to user
	 * @param title
	 * @param content
	 * @return void
	 * */
    private void showAlert(String title,String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        
        alert.setContentText(content);
 
        alert.showAndWait();
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
}
