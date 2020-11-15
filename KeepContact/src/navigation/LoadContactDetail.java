package navigation;

import controller.contact.details.Contact_details;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ContactModel;

//this class load contact_detail page
public class LoadContactDetail {
	
	/**
	 * this function load contact_detail page fxmlloader and then call its controller
	 * @param primaryStage
	 * @param ContactModel
	 * @return void
	 * */
	public void loadContactDetailPage(Stage primaryStage,ContactModel contact) {
		try {
			//load page with fxml loader
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/ContactDetail.fxml"));
			Parent parent = fxmlLoader.load();	
			Scene scene = new Scene(parent,900,600);			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Contact Details");
			primaryStage.setMinWidth(900);
			primaryStage.show();
			
			//call its controller
			Contact_details detailController = (Contact_details)fxmlLoader.getController();
			detailController.init(primaryStage,contact);
			detailController.load(contact);
			
		} catch (Exception e) {
			System.out.println("Errorr in loadContactDetailPage");
			e.printStackTrace();
		}
	}
	
}
