package javaFX;

import javaFX.controller.LoginSceneController;
import javaFX.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/MainScene.fxml"));
		StackPane stackPane = loader.load();
		
		MainController controller = loader.getController();
		
		
		
		Scene scene = new Scene(stackPane);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}

}
