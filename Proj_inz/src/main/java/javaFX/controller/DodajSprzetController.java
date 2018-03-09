package javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DodajSprzetController {
	private MainController mainController;
	
	@FXML
	private Button buttonDodaj;
	@FXML
	private Button buttonAnuluj;
	@FXML
	private TextField fieldNazwa;
	@FXML
	private ComboBox<String> comboTyp;
	@FXML
	private ComboBox<String> comboStan;
	
	@FXML
	public void initialize() {
	    comboTyp.getItems().removeAll(comboTyp.getItems());
	    comboTyp.getItems().addAll("Laptop", "Desktop", "Monitor", "Mysz", "Klawiatura");
	    comboTyp.getSelectionModel().select("Laptop");
	    comboStan.getItems().removeAll(comboStan.getItems());
	    comboStan.getItems().addAll("Sprawny","Niesprawny");
	    comboStan.getSelectionModel().select("Sprawny");
	}
	
	@FXML
	private void onActionDodaj() {
		String typ = comboTyp.getSelectionModel().getSelectedItem();
		String stan = comboStan.getSelectionModel().getSelectedItem();
		mainController.DBUpdate("Insert into Magazyn (NAZWA,TYP,STAN) VALUES ('"+ fieldNazwa.getText() +"','"+ typ +"','" + stan + "')");
		mainController.SceneWarehouse();
	}
	
	@FXML
	private void onActionAnuluj() {
		mainController.SceneWarehouse();
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
}
