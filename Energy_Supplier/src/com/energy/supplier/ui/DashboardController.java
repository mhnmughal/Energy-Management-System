package com.energy.supplier.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.energy.supplier.controller.Controller;
import com.energy.supplier.model.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DashboardController implements Initializable {
	@FXML
	private Stage stage;
    @FXML
    private TextField TariffField;

    @FXML
    private TextField IdField;
    @FXML
    private TextField Id;
    @FXML
    private TextField customerName;
    @FXML
    private TextField tariff;
    @FXML
    private TextField meterReading;
    @FXML
    private TableView<Invoice> Invoicetable;
    @FXML
    private TableColumn<Invoice, Integer> invoiceId;
    @FXML
    private TableColumn<Invoice, Integer> customerId;
    @FXML
    private TableColumn<Invoice, String> tariffType;
    @FXML
    private TableColumn<Invoice, Double> cost;
    @FXML
    private TableColumn<Invoice, String> payment;
    private Controller supplier=null;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	 // Initialize the Supplier instance
        supplier = new Controller();

        // Initialize the table columns
        invoiceId.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tariffType.setCellValueFactory(new PropertyValueFactory<>("tariffType"));
        cost.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        payment.setCellValueFactory(new PropertyValueFactory<>("payment"));
       

        // Read invoices from the CSV file and populate the table
        supplier.getInvoice();
        supplier.getCustomerDetails();
        supplier.getTariffDetails();
        // Print the contents of invoices
        System.out.println("Invoices: " + supplier.getInvoices());

        Invoicetable.setItems(FXCollections.observableArrayList(supplier.getInvoices()));
        supplier.getCustomerDetails();
        supplier.getTariffDetails();
		
	}

    
   

    @FXML
    public void serach(ActionEvent event)
    {
    	searchDetails();
    }
    @FXML
    public void refreshData(ActionEvent event) {
        // Read invoices from the CSV file and populate the table
        supplier.getInvoice();

        // Print the contents of invoices
        System.out.println("Invoices: " + supplier.getInvoices());

        Invoicetable.setItems(FXCollections.observableArrayList(supplier.getInvoices()));
    }
    private void searchDetails() {
    	String customerIdText = IdField.getText().trim();
    	
    	String TariffType= TariffField.getText().trim();

        if (!customerIdText.isEmpty()) {
            try {
                int customerId = Integer.parseInt(customerIdText);
                Invoice foundInvoice =  searchInvoiceByCustomerId(customerId);
                Customer foundCustomer= searchCustomerById(customerId);
                EnergyTariff foundTariff=searchTariffByCustomerIdAndType(customerId);
                if (foundInvoice != null ) {
                    // Clear previous search results
                    Invoicetable.getItems().clear();
                     Id.setText(customerIdText);
                     customerName.setText(foundCustomer.getFirstName()+" "+foundCustomer.getLastName());
                     tariff.setText(TariffType);
                     // Check if foundTariff is not null before accessing its properties
                     if (foundTariff != null) {
                         meterReading.setText(String.valueOf(foundTariff.getDuration()));
                     }
                    // Display the found invoice in the table
                    Invoicetable.getItems().add(foundInvoice);
                    // Select the row related to the customer ID
                    Invoicetable.getSelectionModel().select(foundInvoice);
                    
                    
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                	alert.setTitle("Found");
                	alert.setHeaderText("Customer found and displayed: " + foundInvoice.getCustomerId());
                	alert.showAndWait().get();
                } else {
                    
                    Alert alert = new Alert(AlertType.ERROR);
                	alert.setTitle("Error");
                	alert.setHeaderText("No Customer found for Customer ID: " + customerId);
                	alert.showAndWait().get();
                }
            } catch (NumberFormatException e) {
               Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Error");
            	alert.setHeaderText("Invalid Customer ID format. Please enter a valid number.");
            	alert.showAndWait().get();
            }
        }  else {
            
            Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Please enter a Customer ID for searching.");
        	alert.showAndWait().get();
        }
    }

   
    private Invoice searchInvoiceByCustomerId(int customerId) {
        // Assuming the invoices list is populated, you can replace this with your actual data retrieval logic
        Controller supplier = new Controller();
        supplier.getInvoice(); // Populate the invoices list

        for (Invoice invoice : supplier.getInvoices()) {
            if (invoice.getCustomerId() == customerId) {
                return invoice;
            }
        }

        return null; // Return null if no invoice is found
    }
 // Functionality to search for a customer by ID
    public Customer searchCustomerById(int customerId) {
        for (Customer customer : supplier.getCustomerData()) {
            if (customer.getCustomerID() == customerId) {
                return customer;
            }
        }
        return null;
    }

    // Functionality to search for a tariff by customer ID and tariff type
    public EnergyTariff searchTariffByCustomerIdAndType(int customerId) {
        for (EnergyTariff tariff : supplier.getTariffsData()) {
            if (tariff.getCustomerId() == customerId) {
                return tariff;
            }
        }
        return null;
    }
}
