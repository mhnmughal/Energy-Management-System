package com.energy.supplier.ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import com.energy.supplier.controller.Controller;
import java.io.IOException;
public class RootPaneController {
	 @FXML
	    private Tab customer;
	 @FXML
	    private Tab dashboard;
	    @FXML
	    private Tab tariff;
	    @FXML
	    private MenuItem loadData;

	    @FXML
	    private MenuItem SaveData;
	    @FXML
	    private MenuItem about;


	    @FXML
	    private Tab invoiceManager;
	    private Controller controller; 
	    

	    @FXML
	    private void initialize() {
	        // Load the customerManagementScreen.fxml into the Customer Manager tab
	        loadCustomerManagementScreen();
	     // Load the tariffManagementScreen.fxml into the Tariff Manager tab
	        loadTariffManagementScreen();
	        loadInvoiceGenerationScreen();
	        loadDashboardScreen();
	        loadData.setOnAction(event -> loadCustomerData());
	        SaveData.setOnAction(event -> saveCustomerData());
	        about.setOnAction(event -> showAboutInfo());
	    }

	    private void loadCustomerManagementScreen() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerManagementScreen.fxml"));
	            AnchorPane customerManagementScreen = loader.load();

	            // Get the controller associated with the loaded FXML
	            CustomerManagementController customerManagementController = loader.getController();

	            // Set the loaded content to the Customer Manager tab
	            customer.setContent(customerManagementScreen);
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }
	    private void loadTariffManagementScreen() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("TariffManagementScreen.fxml"));
	            AnchorPane tariffManagementScreen = loader.load();

	            // Get the controller associated with the loaded FXML
	            TariffManagementController tariffManagementController = loader.getController();

	            // Set the loaded content to the Tariff Manager tab
	            tariff.setContent(tariffManagementScreen);
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }
	    private void loadInvoiceGenerationScreen() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("InvoiceGeneration.fxml"));
	            AnchorPane invoiceGenerationScreen = loader.load();

	            // Get the controller associated with the loaded FXML
	            InvoiceGenerationController invoiceGenerationController = loader.getController();

	            // Set the loaded content to the Invoice Manager tab
	            invoiceManager.setContent(invoiceGenerationScreen);
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }
	    private void loadDashboardScreen() {
	        try {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
	            AnchorPane Dashboard = loader.load();

	            // Get the controller associated with the loaded FXML
	            DashboardController controller = loader.getController();

	            // Set the loaded content to the Invoice Manager tab
	            dashboard.setContent(Dashboard);
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }
	    private void loadCustomerData() {
	    	controller=new Controller();
	        controller.getCustomerDetails();
	    	showAlert("Load CustomerData", "Customer Data is successfully loaded.");
	    }

	    private void saveCustomerData() {
	        showAlert("Save CustomerData", "Customer Data is successfully saved.");
	    }
	    private void showAboutInfo() {
	        String aboutText = "This system is designed to manage energy supply System.\n" +
	                "It includes features for customer management, tariff management, and invoice generation.";
	        
	        showAlert("About", aboutText);
	    }

	    private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	    }


