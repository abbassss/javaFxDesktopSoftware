package navigation;

import controller.contact.all.Contact_all;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//this class load mainstage page
public class LoadMainstage {
	
	/**
	 * this function load home page fxmlloader and then call its controller
	 * */
	public void loadMainstagePage(Stage primaryStage) {		
		try {
			//load page with fxml loader
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/Mainstage.fxml"));
			Parent parent = fxmlLoader.load();	
			Scene scene = new Scene(parent,1000,600);			
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(1000);
			primaryStage.show();
			
			//call its controller
			Contact_all controller=(Contact_all)fxmlLoader.getController();			
			controller.init(primaryStage);
			controller.load(primaryStage);
			
		} catch (Exception e) {
			System.out.println("Error in happened loadmainstagepage");
			e.printStackTrace();
		}
	}
}
