package javaFX.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProgramSceneController {

	private MainController mainController;
	public ResultSet result;
	
	@FXML
	private MenuItem menuClose;
	@FXML
	private MenuItem menuLogout;
	
	@FXML
	private TableView programTableView;
	@FXML
	private TableColumn programTableColumnID;
	@FXML
	private TableColumn programTableColumnPesel;
	@FXML
	private TableColumn programTableColumnImie;
	@FXML
	private TableColumn programTableColumnNazwisko;
	
	public static class Person {
	    private final SimpleStringProperty ID;
	    private final SimpleStringProperty Pesel;
	    private final SimpleStringProperty Imie;
	    private final SimpleStringProperty Nazwisko;
	 
	    private Person(String id, String pesel, String imie,String nazwisko) {
	        this.ID = new SimpleStringProperty(id);
	        this.Pesel = new SimpleStringProperty(pesel);
	        this.Imie = new SimpleStringProperty(imie);
	        this.Nazwisko = new SimpleStringProperty(nazwisko);
	        
	    }
	
	@FXML
	public void initialize() {
		
	}
	
	
	@FXML
	public void onActionMenuClose() {
		Platform.exit();
	}
	
	@FXML
	public void onActionMenuLogout() {
		mainController.DBDisconnect();
	}
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
