package javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DodajPracownikaController {
	private MainController mainController;
	
	@FXML
	private Button buttonDodaj;
	@FXML
	private Button buttonAnuluj;
	@FXML
	private TextField fieldPesel;
	@FXML
	private TextField fieldImie;
	@FXML
	private TextField fieldNazwisko;
	
	@FXML
	private void onActionDodaj() {
		mainController.DBUpdate("Insert into Pracownicy (PESEL,IMIE,NAZWISKO) VALUES ("+ fieldPesel.getText() +",'"+ fieldImie.getText() +"','" + fieldNazwisko.getText()+ "')");
		mainController.SceneProgram();
	}
	
	@FXML
	private void onActionAnuluj() {
		mainController.SceneProgram();
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
}
