package javaFX.controller;

import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProgramSceneController {

	private MainController mainController = null;
	public ResultSet result = null;
	private ObservableList<Person> data;
	
	public class Person {
	    private SimpleIntegerProperty ID;
	    private SimpleLongProperty Pesel;
	    private SimpleStringProperty Imie;
	    private SimpleStringProperty Nazwisko;
	 
	    private Person(int id, long pesel, String imie,String nazwisko) {
	        this.setID(new SimpleIntegerProperty(id));
	        this.setPesel(new SimpleLongProperty(pesel));
	        this.setImie(new SimpleStringProperty(imie));
	        this.setNazwisko(new SimpleStringProperty(nazwisko));
	        
	    }

		public int getID() {
			return ID.getValue();
		}

		public void setID(SimpleIntegerProperty id) {
			ID = id;
		}

		public Long getPesel() {
			return Pesel.getValue();
		}

		public void setPesel(SimpleLongProperty pesel) {
			Pesel = pesel;
		}
	    
		public String getImie() {
			return Imie.getValue();
		}
		
		public void setImie(SimpleStringProperty imie) {
			Imie = imie;
		}
	    
		public String getNazwisko() {
			return Nazwisko.getValue();
		}
		
		public void setNazwisko(SimpleStringProperty nazwisko) {
			Nazwisko = nazwisko;
		}
		
	}
	
	@FXML
	private MenuItem menuClose;
	@FXML
	private MenuItem menuLogout;
	
	@FXML
	private Button buttonPracownicy;
	@FXML
	private Button buttonMagazyn;
	@FXML
	private Button buttonDodaj;
	@FXML
	private Button buttonUsun;
	
	
	@FXML
	private TableView<Person> programTableView;
	@FXML
	private TableColumn programTableColumnID;
	@FXML
	private TableColumn programTableColumnPesel;
	@FXML
	private TableColumn programTableColumnImie;
	@FXML
	private TableColumn programTableColumnNazwisko;
	
	
	
	
	@FXML
	public void initialize() {
		programTableColumnID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("ID"));
		programTableColumnPesel.setCellValueFactory(new PropertyValueFactory<Person, Long>("Pesel"));
		programTableColumnImie.setCellValueFactory(new PropertyValueFactory<Person, String>("Imie"));
		programTableColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Person, String>("Nazwisko"));
		data = FXCollections.observableArrayList();
	}
	
	@FXML
	public void onActionPracownicy() {
		DBFillTable();
		programTableView.refresh();
	}
	
	public void onActionMagazyn() {
		mainController.SceneWarehouse();
	}
	
	@FXML
	public void onActionDodaj() {
		mainController.SceneDodajPracownika();
	}
	@FXML
	public void onActionUsun() {
		Person person = programTableView.getSelectionModel().getSelectedItem();
		mainController.DBUpdate("Delete From Pracownicy WHERE ID=" + person.getID());
		DBFillTable();
		programTableView.refresh();
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
	
	public void DBFillTable() {
		data.clear();
		result = mainController.DBQuerry("SELECT * FROM Pracownicy");
		Person osoba = null;
		try {
			while (result.next()) {
				if(result == null) System.out.println("result nullem jest");
				osoba = new Person(
						result.getInt("ID"),
						result.getLong("Pesel"),
						result.getString("Imie"),
						result.getString("Nazwisko")
				);
				
				data.add(osoba);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		programTableView.setItems(data);
		programTableView.refresh();
		
		
		
	}
	
}

	
