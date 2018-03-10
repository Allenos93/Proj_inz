package javaFX.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.j256.ormlite.field.types.DateType;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RentalSceneController {

	private MainController mainController = null;
	public ResultSet result = null;
	private ObservableList<Rent> dane;
	
	public class Rent {
	    private SimpleIntegerProperty ID;
	    private SimpleStringProperty Imie;
	    private SimpleStringProperty Nazwisko;
	    private SimpleStringProperty Nazwa;
	    private SimpleStringProperty Typ;
	 
	    private Rent(int id, String imie,String nazwisko,String nazwa,String typ) {
	        this.setID(new SimpleIntegerProperty(id));
	        this.setImie(new SimpleStringProperty(imie));
	        this.setNazwisko(new SimpleStringProperty(nazwisko));
	        this.setNazwa(new SimpleStringProperty(nazwa));
	        this.setTyp(new SimpleStringProperty(typ));

	        
	    }

		public int getID() {
			return ID.getValue();
		}

		public void setID(SimpleIntegerProperty id) {
			ID = id;
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
	private Button buttonWypozyczenia;
	
	@FXML
	private Button buttonDodaj;
	@FXML
	private Button buttonUsun;
	
	
	@FXML
	private TableView<Rent> rentalTableView;
	@FXML
	private TableColumn rentalTableColumnID;
	@FXML
	private TableColumn rentalTableColumnImie;
	@FXML
	private TableColumn rentalTableColumnNazwisko;
	@FXML
	private TableColumn rentalTableColumnNazwa;
	@FXML
	private TableColumn rentalTableColumnTyp;
	@FXML
	private TableColumn rentalTableColumnData;
	
	
	
	
	@FXML
	public void initialize() {
		rentalTableColumnID.setCellValueFactory(new PropertyValueFactory<Rent, Integer>("ID"));
		rentalTableColumnImie.setCellValueFactory(new PropertyValueFactory<Rent, String>("Imie"));
		rentalTableColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Rent, String>("Nazwisko"));
		rentalTableColumnNazwa.setCellValueFactory(new PropertyValueFactory<Rent, String>("Nazwa"));
		rentalTableColumnTyp.setCellValueFactory(new PropertyValueFactory<Rent,String>("Typ"));
		rentalTableColumnData.setCellValueFactory(new PropertyValueFactory<Rent,String>("Data"));
		dane = FXCollections.observableArrayList();
	}
	
	@FXML
	public void onActionPracownicy() {
		mainController.SceneProgram();
	}
	
	public void onActionMagazyn() {
		mainController.SceneWarehouse();
	}
	
	@FXML
	public void onActionDodaj() {
		mainController.SceneWypozycz();
	}
	@FXML
	public void onActionWypozyczenia() {
		DBFillTable();
		rentalTableView.refresh();
	}
	
	@FXML
	public void onActionUsun() {
		Rent rent = rentalTableView.getSelectionModel().getSelectedItem();
		mainController.DBUpdate("Delete From Wypozyczenia WHERE ID=" + rent.getID());
		DBFillTable();
		rentalTableView.refresh();
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
		dane.clear();		
		result = mainController.DBQuerry("Select Wypozyczenia.ID,Pracownicy.Imie,Pracownicy.Nazwisko,Magazyn.Nazwa,Magazyn.Typ FROM Wypozyczenia Left join Pracownicy ON Wypozyczenia.ID_PRACOWNIKA = Pracownicy.ID LEFT JOIN Magazyn on Wypozyczenia.ID_Sprzetu = Magazyn.id");
		Rent rent = null;
		try {
			while (result.next()) {
				rent = new Rent(
						result.getInt("ID"),
						result.getString("Imie"),
						result.getString("Nazwisko"),
						result.getString("Nazwa"),
						result.getString("Typ")
				);
				
				dane.add(rent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rentalTableView.setItems(dane);
		rentalTableView.refresh();
		
		
		
	}
	
}

	
