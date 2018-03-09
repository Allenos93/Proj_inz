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

public class WarehouseSceneController {

	private MainController mainController = null;
	public ResultSet result = null;
	private ObservableList<Item> data;
	
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
	private TableView<Item> warehouseTableView;
	@FXML
	private TableColumn warehouseTableColumnID;
	@FXML
	private TableColumn warehouseTableColumnNazwa;
	@FXML
	private TableColumn warehouseTableColumnTyp;
	@FXML
	private TableColumn warehouseTableColumnStan;
	
	
	
	
	@FXML
	public void initialize() {
		warehouseTableColumnID.setCellValueFactory(new PropertyValueFactory<Item, Integer>("ID"));
		warehouseTableColumnNazwa.setCellValueFactory(new PropertyValueFactory<Item, String>("Nazwa"));
		warehouseTableColumnTyp.setCellValueFactory(new PropertyValueFactory<Item, String>("Typ"));
		warehouseTableColumnStan.setCellValueFactory(new PropertyValueFactory<Item, String>("Stan"));
		data = FXCollections.observableArrayList();
	}
	
	@FXML
	public void onActionMagazyn() {
		DBFillTable();
		warehouseTableView.refresh();
	}
	
	@FXML
	public void onActionPracownicy() {
		mainController.SceneProgram();
	}
	
	@FXML
	public void onActionDodaj() {
		
	}
	@FXML
	public void onActionUsun() {
		Item item = warehouseTableView.getSelectionModel().getSelectedItem();
		mainController.DBUpdate("Delete From Magazyn WHERE ID=" + item.getID());
		DBFillTable();
		warehouseTableView.refresh();
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
				data.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		warehouseTableView.setItems(data);
		warehouseTableView.refresh();
		
		
		
	}
	
}

	
