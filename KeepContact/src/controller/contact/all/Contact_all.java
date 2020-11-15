package controller.contact.all;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import helper.ContactHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ContactModel;
import navigation.LoadAddContact;
import navigation.LoadContactDetail;
import utils.StringUtil;

//class to handle contact_all page functionality
public class Contact_all {

	@FXML
	private Button btn_add;
	
	@FXML
    private Button btn_filter;


	@FXML
	private TableColumn<ContactModel, String> col_firstname;

	@FXML
	private TableColumn<ContactModel, String> col_fullname;

	@FXML
	private TableColumn<ContactModel, Timestamp> col_birthday;

	@FXML
	private TableColumn<ContactModel, String> col_address;

	@FXML
	private TableColumn<ContactModel, String> col_salute_name;

	@FXML
	private TextField txtf_fullname;

	@FXML
	private TableView<ContactModel> tbl_contact;

	@FXML
	private TableColumn<ContactModel, String> col_lastname;

	@FXML
	private DatePicker datep_birthday;
	
	/**
	 * initial method of contact_all page which set 
	 * columns of table and set page listeners
	 * @param Stage take current stage  	
	 * @return void
	 * */
	public void init(Stage primaryStage) {
		try {

			col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));

			col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));

			col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));

			col_birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));

			col_address.setCellValueFactory(new PropertyValueFactory<>("address"));

			col_salute_name.setCellValueFactory(new PropertyValueFactory<>("name"));
			
			//set listener of home page
			setListener(primaryStage);

		} catch (Exception e) {
			System.out.println("Error happened in mainstage init");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * method for set page buttons and events
	 * @param Stage take current stage 
	 * @return void
	 * */
	private void setListener(Stage primaryStage) {
		try {
			//handle add button
			btn_add.setOnAction((event) -> {
				LoadAddContact add = new LoadAddContact();
				add.loadAddContactPage(primaryStage);
			});
			
			//handle click on table row
			tbl_contact.setRowFactory( tv -> {
			    TableRow<ContactModel> row = new TableRow<ContactModel>();
			    row.setOnMouseClicked(event -> {
			        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
			        	ContactModel rowData = row.getItem();
			            if (rowData != null) {
			            	LoadContactDetail loadContactDetail = new LoadContactDetail();					
							loadContactDetail.loadContactDetailPage(primaryStage, rowData);
						}
			        }
			    });
			    return row ;
			});
			
			//handle filter button
			btn_filter.setOnAction((event) ->{
				
				//if both text field are empty
				if (txtf_fullname.getLength() == 0 && datep_birthday.getValue() == null) {
					return;
				}
				
				//if one of them or both are fill
				else if (txtf_fullname.getText() != null || datep_birthday.getValue() != null) {
					ContactModel model = new ContactModel();
					model.setFullname(txtf_fullname.getText().toLowerCase());						
					
					if (datep_birthday.getValue() != null) {
						model.setBirthday(convertToTimestamp(datep_birthday.getValue()));
					}
					
					//filter tableview
					try {
						searchResult(primaryStage,model);
					} catch (IOException |SQLException e) {
						System.out.println("Error in search result");
						e.printStackTrace();
					}										
				}
				
			});
		} catch (Exception e) {
			System.out.println("error in setListener");
			e.printStackTrace();
		}
	}
	/**
	 * method which take a model and search table for that model
	 * then show the search result in table
	 * @param Stage take current stage 
	 * @param ContactModel ContactModel object to search for
	 * @return void
	 * */ 
	public void searchResult(Stage primaStage, ContactModel model)throws IOException, SQLException  {
		ArrayList<ContactModel> contactList = new ArrayList<ContactModel>();
		contactList = ContactHelper.tableFilter(model);
		
		if (contactList != null) {
			//load contacts to table
			loadTable(contactList);	
		}				
	}

	/**
	 * load method of contact_all class which fill table when class called
	 * @param Stage take current stage
	 * @return void
	 * */
	public void load(Stage primaryStage) throws IOException, SQLException {
		ArrayList<ContactModel> contactList = new ArrayList<ContactModel>();
		contactList = ContactHelper.getUserList();
		
		//load contacts to table
		loadTable(contactList);		

	}
	
	/**
	 * method to convert LocadDate to Timestamp
	 * @param LocalDate birthday
	 * @return Timestamp timestamp
	 * */
	public static Timestamp convertToTimestamp(LocalDate birthday) {
		Date date = Date.valueOf(birthday);
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	} 
	
	/**
	 * method for load a list of model in table
	 * @param ArrayList<ContactModel>
	 * @return void
	 * */
	private void loadTable(ArrayList<ContactModel> contactList) {
		tbl_contact.getItems().clear();
		tbl_contact.getItems().addAll(contactList);
	}
}
