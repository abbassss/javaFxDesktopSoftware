package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import navigation.LoadMainstage;

// Main class which is start point of our program 
public class Main extends Application {
	
	/**
	 * this start function run after main method and 
	 * call load home page of our program
	 * */
	@Override
	public void start(Stage primaryStage) {
		try {

			LoadMainstage mainStage = new LoadMainstage();
			mainStage.loadMainstagePage(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main function is where our project start
	 * */
	public static void main(String[] args) {
		launch(args);
	}
}
