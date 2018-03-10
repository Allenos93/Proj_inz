package javaFX.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javaFX.controller.ProgramSceneController.Person;
import javaFX.controller.WarehouseSceneController.Item;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class WypozyczController {
	private MainController mainController;
	public ResultSet result = null;
	private ObservableList<Person> data;
	private ObservableList<Item> data2;
	
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
	
	public class Item {
	    private SimpleIntegerProperty ID;
	    private SimpleStringProperty Nazwa;
	    private SimpleStringProperty Typ;
	    private SimpleStringProperty Stan;
	 
	    private Item(int id, String nazwa, String typ,String stan) {
	        this.setID(new SimpleIntegerProperty(id));
	        this.setNazwa(new SimpleStringProperty(nazwa));
	        this.setTyp(new SimpleStringProperty(typ));
	        this.setStan(new SimpleStringProperty(stan));
	        
	    }

		public int getID() {
			return ID.getValue();
		}

		public void setID(SimpleIntegerProperty iD) {
			ID = iD;
		}

		public String getNazwa() {
			return Nazwa.getValue();
		}

		public void setNazwa(SimpleStringProperty nazwa) {
			Nazwa = nazwa;
		}

		public String getTyp() {
			return Typ.getValue();
		}

		public void setTyp(SimpleStringProperty typ) {
			Typ = typ;
		}

		public String getStan() {
			return Stan.getValue();
		}

		public void setStan(SimpleStringProperty stan) {
			Stan = stan;
		}

		
	}
	
	
	@FXML
	private Button buttonDodaj;
	@FXML
	private Button buttonAnuluj;
	
	@FXML
	private TableView pracownicyTableView;
	@FXML
	private TableColumn pracownicyTableColumnID;
	@FXML
	private TableColumn pracownicyTableColumnPesel;
	@FXML
	private TableColumn pracownicyTableColumnImie;
	@FXML
	private TableColumn pracownicyTableColumnNazwisko;
	
	
	@FXML
	private TableView magazynTableView;
	@FXML
	private TableColumn magazynTableColumnID;
	@FXML
	private TableColumn magazynTableColumnNazwa;
	@FXML
	private TableColumn magazynTableColumnTyp;
	@FXML
	private TableColumn magazynTableColumnStan;
	
	@FXML
	public void initialize() {
		pracownicyTableColumnID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("ID"));
		pracownicyTableColumnPesel.setCellValueFactory(new PropertyValueFactory<Person, Long>("Pesel"));
		pracownicyTableColumnImie.setCellValueFactory(new PropertyValueFactory<Person, String>("Imie"));
		pracownicyTableColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Person, String>("Nazwisko"));
		magazynTableColumnID.setCellValueFactory(new PropertyValueFactory<Item, Integer>("ID"));
		magazynTableColumnNazwa.setCellValueFactory(new PropertyValueFactory<Item, String>("Nazwa"));
		magazynTableColumnTyp.setCellValueFactory(new PropertyValueFactory<Item, String>("Typ"));
		magazynTableColumnStan.setCellValueFactory(new PropertyValueFactory<Item, String>("Stan"));
		
		data2 = FXCollections.observableArrayList();
		data = FXCollections.observableArrayList();
	}
	
	@FXML
	private void onActionDodaj() {
		DBFillTablePracownicy();
		DBFillTableMagazyn();
	}
	
	@FXML
	private void onActionAnuluj() {
		mainController.SceneWypozyczenia();
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void DBFillTablePracownicy() {
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
		
		pracownicyTableView.setItems(data);
		pracownicyTableView.refresh();
		
		
		
	}
	
	public void DBFillTableMagazyn() {
		data2.clear();
		result = mainController.DBQuerry("SELECT * FROM Magazyn");
		Item item = null;
		try {
			while (result.next()) {
				if(result == null) System.out.println("result nullem jest");
				item = new Item(
						result.getInt("ID"),
						result.getString("Nazwa"),
						result.getString("Typ"),
						result.getString("Stan")
				);
				if(item != null)
				data2.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		magazynTableView.setItems(data2);
		magazynTableView.refresh();
		
		
		
	}
	
}
