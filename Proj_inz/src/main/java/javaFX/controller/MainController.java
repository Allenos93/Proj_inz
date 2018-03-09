package javaFX.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.sql.*;
import java.util.Properties;

import com.j256.ormlite.jdbc.*;
import com.j256.ormlite.support.ConnectionSource;



public class MainController {
	
	public int x;
	public Connection conn = null;
	Properties connectionProps = new Properties();
	public String databaseURL = "jdbc:h2:tcp://localhost/~/ProjektInz";
	Statement stmt = null;
	
	
	
	
	@FXML
	private StackPane mainStackPane;
	

	@FXML
	public void initialize() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoginScene.fxml"));
		Pane pane = null;
		try {
			 pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginSceneController loginSceneController = loader.getController();
		loginSceneController.setMainController(this);
		
		
		mainStackPane.getChildren().add(pane);
	}
	
	
	public void DBConnect(String Login,String Password) {
		
		try {
			connectionProps.put("user", Login);
			connectionProps.put("password", Password);
			conn = DriverManager.getConnection(databaseURL,connectionProps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(conn != null){
			SceneProgram();
		}
	}
	
	public void SetScreen(Pane pane){
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
		
	}
	
	public void DBDisconnect() {
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoginScene.fxml"));
		Pane pane = null;
		try {
			 pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginSceneController loginSceneController = loader.getController();
		loginSceneController.setMainController(this);
		
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
	
	public ResultSet DBQuerry(String querry) {
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = statement.executeQuery(querry);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void DBUpdate(String query) {
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void SceneWarehouse() {
		Pane pane = null;
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/WarehouseScene.fxml"));
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WarehouseSceneController warehouseController = loader.getController();
		warehouseController.setMainController(this);
		SetScreen(pane);
	}
	
	public void SceneProgram() {
		Pane pane = null;
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/ProgramScene.fxml"));
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProgramSceneController programController = loader.getController();
		programController.setMainController(this);
		SetScreen(pane);
	}
	public void SceneDodajPracownika() {
		Pane pane = null;
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/DodajPracownika.fxml"));
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DodajPracownikaController dodajPracownikaController = loader.getController();
		dodajPracownikaController.setMainController(this);
		SetScreen(pane);
	}
	public void SceneDodajSprzet() {
		Pane pane = null;
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/DodajSprzet.fxml"));
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DodajSprzetController dodajSprzetController = loader.getController();
		dodajSprzetController.setMainController(this);
		SetScreen(pane);
	}
}
