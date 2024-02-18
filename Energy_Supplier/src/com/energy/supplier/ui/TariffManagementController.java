package com.energy.supplier.ui;



import com.energy.supplier.controller.Controller;

import com.energy.supplier.model.EnergyTariff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class TariffManagementController implements Initializable {
	   @FXML
		private Stage stage;
		
		@FXML
	    private TableView<EnergyTariff> tariffTable;
		@FXML
	    private TableColumn<EnergyTariff, Integer> a;
		  @FXML
		    private TableColumn<EnergyTariff, Integer> TariffId;
		    @FXML
		    private TableColumn<EnergyTariff, String> TariffType;
		    @FXML
		    private TableColumn<EnergyTariff, Double> Price;
		    @FXML
		    private TableColumn<EnergyTariff, Integer> Duration;
		    @FXML
		    private TableColumn<EnergyTariff, Void> remove;
		    @FXML
		    private TextField CField;
		    @FXML
		    private TextField IdField;
		    @FXML
		    private TextField TariffField;
		    @FXML
		    private TextField DurationField;
		    @FXML
		    private TextField PriceField;
		 // Instance of the Supplier class
		    private Controller supplier; 
		    private EnergyTariff tariff;
		   
		    //Initializing method that initialize the data in table
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			 // Initialize the Supplier instance
		    supplier = new Controller();

		    // Initialize the table columns
		    a.setCellValueFactory(new PropertyValueFactory<>("customerId")); // Check the correct property name
		    TariffId.setCellValueFactory(new PropertyValueFactory<>("tariffID"));
		    TariffType.setCellValueFactory(new PropertyValueFactory<>("tariffType"));
		    Price.setCellValueFactory(new PropertyValueFactory<>("price"));
		    Duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		    remove.setCellFactory(param -> new TableCell<>() {
	            private final Button removeButton = new Button("Delete");

	            {
	                removeButton.setOnAction(event -> {
	                    EnergyTariff tariff = getTableView().getItems().get(getIndex());
	                    removeSelectedTariff(tariff);
	                });
	            }

	            @Override
	            protected void updateItem(Void item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty) {
	                    setGraphic(null);
	                } else {
	                    setGraphic(removeButton);
	                }
	            }
	        });
		    
		    
		    
		    
		    tariffTable.setItems(observableList);

		    // Data imported from the CSV file
		    supplier.getTariffDetails();
		    
		   tariffTable.setItems(FXCollections.observableArrayList(supplier.getTariffsData()));
		// Only set the items to the TableView once
		    tariffTable.setItems(observableList);
		    // Data imported from the CSV file
		    supplier.getTariffDetails();
		    observableList.addAll(supplier.getTariffsData());
		}
		
		ObservableList<EnergyTariff> observableList=FXCollections.observableArrayList();
		// method to navigate to Invoice Generation screen
		@FXML
	    public void refreshData(ActionEvent event) {
	        // Read invoices from the CSV file and populate the table
			supplier.getTariffDetails();

	        // Print the contents of invoices
	        System.out.println("Invoices: " + supplier.getTariffsData());

	        tariffTable.setItems(FXCollections.observableArrayList(supplier.getTariffsData()));
	    }
    @FXML
    public void toAddTriff(ActionEvent e)
    {
    	 tariff=new EnergyTariff(
    			Integer.parseInt(CField.getText()),
    			Integer.parseInt(IdField.getText()),
    			TariffField.getText(),
    			Double.parseDouble(PriceField.getText()),
    			Integer.parseInt(DurationField.getText())
    			);
    	tariffTable.getItems().add(tariff);
    	supplier.addTariff(tariff.getCustomerId(),tariff.getTariffID(), tariff.getTariffType(), tariff.getPrice(), tariff.getDuration());
    	 supplier.addInvoice(tariff.getCustomerId(), tariff.getTariffType(), tariff.calculateCost());
          
           
    	System.out.println("Tariff added: " + tariff.getTariffType() + " " + tariff.getPrice());
        
    	System.out.println("I'm from TariffMangement---\n");
       
    }
    
   @FXML
    public void removeSelectedTariff(ActionEvent e) {
        // Get the selected item from the table
    	EnergyTariff SelectedTariff = tariffTable.getSelectionModel().getSelectedItem();

        if (SelectedTariff != null) {
            // Remove the selected tariff from the table
        	tariffTable.getItems().remove(SelectedTariff);
        	// Remove the selected customer from the supplier's data and CSV file
            supplier.removeTariff(SelectedTariff);
        	System.out.println("Customer removed: " + SelectedTariff.getTariffType() + " " + SelectedTariff.getPrice());
        } else {
            System.out.println("No customer selected.");
        }
    }
	
   public void removeSelectedTariff(EnergyTariff tariff)
   {
	   tariffTable.getItems().remove(tariff);
	   supplier.getTariffsData().remove(tariff);
	   supplier.removeTariff(tariff);
	   System.out.println("Customer removed: " + tariff.getTariffID()+ " " + tariff.getTariffType());
	    	   
	   
   }
   public EnergyTariff getSelectedTariff() {
       return tariffTable.getSelectionModel().getSelectedItem();
   }
	
}
