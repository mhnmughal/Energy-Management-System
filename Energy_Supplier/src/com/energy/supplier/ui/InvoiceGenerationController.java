package com.energy.supplier.ui;


import com.energy.supplier.controller.Controller;
import com.energy.supplier.model.*;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InvoiceGenerationController implements Initializable {
	@FXML
	private Stage stage;
	
	 
	 @FXML
	    private Label customerIdLabel;
	    @FXML
	    private TextField CField;
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

            // Print the contents of invoices
            System.out.println("Invoices: " + supplier.getInvoices());

            Invoicetable.setItems(FXCollections.observableArrayList(supplier.getInvoices()));
            }
	    
        @FXML
        public void refreshData(ActionEvent event) {
            // Read invoices from the CSV file and populate the table
            supplier.getInvoice();

            // Print the contents of invoices
            System.out.println("Invoices: " + supplier.getInvoices());

            Invoicetable.setItems(FXCollections.observableArrayList(supplier.getInvoices()));
        }
        @FXML
        private void generateAndPrintInvoice(ActionEvent event) {
            // Implement your logic to generate and print the invoice
            Invoice selectedInvoice = Invoicetable.getSelectionModel().getSelectedItem();
            
            if (selectedInvoice != null) {
            	Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setTitle("Success");
            	alert.setHeaderText("Printed successfully");
            	alert.setContentText("Invoice ID: "+selectedInvoice.getInvoiceId()+"Customer ID: "+selectedInvoice.getCustomerId()+"Tarriff Type : "+ selectedInvoice.getTariffType()+"TotalAmount: "+selectedInvoice.getTotalAmount());
                alert.showAndWait().get();
            } else {
                
                Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Error");
            	alert.setHeaderText("Please select an invoice from the table.");
            	alert.showAndWait().get();
            	
            }
        }
      

    @FXML
    private void searchCoustomerId(ActionEvent event) {
    	String customerIdText = CField.getText().trim();

        if (!customerIdText.isEmpty()) {
            try {
                int customerId = Integer.parseInt(customerIdText);
                Invoice foundInvoice = searchInvoiceByCustomerId(customerId);

                if (foundInvoice != null) {
                    // Clear previous search results
                    Invoicetable.getItems().clear();

                    // Display the found invoice in the table
                    Invoicetable.getItems().add(foundInvoice);
                    // Select the row related to the customer ID
                    Invoicetable.getSelectionModel().select(foundInvoice);

                    System.out.println("Invoice found and displayed: " + foundInvoice);
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                	alert.setTitle("Found");
                	alert.setHeaderText("Invoice found and displayed: " + foundInvoice.getCustomerId());
                	alert.showAndWait().get();
                } else {
                    
                    Alert alert = new Alert(AlertType.ERROR);
                	alert.setTitle("Error");
                	alert.setHeaderText("No invoice found for Customer ID: " + customerId);
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

	
}
