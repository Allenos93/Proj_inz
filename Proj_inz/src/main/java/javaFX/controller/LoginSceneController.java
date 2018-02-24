package javaFX.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController {
	@FXML
	private Button buttonLogin;
	@FXML
	private Button buttonExit;
	@FXML
	private TextField login;
	@FXML
	private PasswordField password;
	
	
	
	public LoginSceneController() {
		
	}
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void onActionLogin() {
		
	}
	
	@FXML
	public void onActionExit() {
		Platform.exit();
	}
	
}
