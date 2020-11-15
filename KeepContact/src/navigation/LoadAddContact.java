package navigation;

import controller.contact.add.Contact_add;
import controller.contact.all.Contact_all;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//this class load add contact page
public class LoadAddContact {
	
	/**
	 * this function load add_contact page fxmlloader and then call its controller
	 * */
	public void loadAddContactPage(Stage primaryStage) {
		try {
			//load page with fxml loader
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/AddContact.fxml"));
			Parent parent = fxmlLoader.load();	
			Scene scene = new Scene(parent,900,600);	
			primaryStage.setTitle("Add Contact");
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(900);
			primaryStage.show();
			
			//call its controller
			Contact_add addContact = (Contact_add)fxmlLoader.getController();;
			addContact.init(primaryStage);
			
			
		} catch (Exception e) {
			System.out.println("Error in loadAddContactPage");
			System.err.println(e);
		}
	}
}
