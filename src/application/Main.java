package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	

	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
//primaryStage.setResizable(false);
        initRootLayout();

       
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        // Load root layout from fxml file.
    	try {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Gui.fxml"));
	    rootLayout =  loader.load();
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    
    /**
     * Shows the person overview inside the root layout.
     */
  
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	
	public static void main(String[] args) {
	        launch(args);
	    }
}
