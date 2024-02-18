package com.energy.supplier.ui;
import com.energy.supplier.controller.Controller;
import com.energy.supplier.model.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class CustomerManagementController implements Initializable {
	@FXML
	private Stage stage;
	
	@FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, String> contactInfoColumn;
    @FXML
    private TableColumn<Customer, Void> removeColumn;
    
    @FXML
    private TextField IdField;
    @FXML
    private TextField ContactField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField nameField1;
 // Instance of the Supplier class
    private Controller supplier;  
    Customer customer;
         String filepath="/remove";
         
         File file=new File(filepath);
        

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	 // Initialize the Supplier instance
        supplier = new Controller();
    	customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
    	firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    	lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    	addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    	contactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
    	
    	
    	removeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button removeButton = new Button("Delete");

            {
            	 if (file.exists()) {
             	    Image image = new Image(file.toURI().toString());
             	    // Further processing with the image
             	   removeButton.setGraphic(new ImageView(image));
            	 }
            	 
            	//Image removeImage = new Image(getClass().getResourceAsStream("/remove.png"));
            	//removeButton.setGraphic(new ImageView(image));
                removeButton.setOnAction(event -> {
                    Customer customer = getTableView().getItems().get(getIndex());
                    removeSelectedCustomer(customer);
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
    

    	
    	//Data imported form the CSV file
    	supplier.getCustomerDetails();
    	customerTable.setItems(observableList);
    	customerTable.setItems(FXCollections.observableArrayList(supplier.getCustomerData()));	
    	
        
    }
    ObservableList<Customer> observableList=FXCollections.observableArrayList();
   
    // Sample method to navigate to Tariff Management screen
    
   
    
    @FXML
    public void toAdd(ActionEvent e)
    {
    	customer = new Customer(Integer.parseInt(IdField.getText()), nameField.getText(), nameField1.getText(), AddressField.getText(), ContactField.getText());
        customerTable.getItems().add(customer);
        supplier.addCustomer(customer.getCustomerID(), customer.getFirstName(), customer.getLastName(), customer.getAddress(), customer.getContactInfo());
        
        System.out.println("Customer added: " + customer.getFirstName() + " " + customer.getLastName());
    
        System.out.println("I'm from CustomerManagement---\n");
        
    }
    @FXML
    public void removeSelectedCustomer(ActionEvent e) {
        // Get the selected item from the table
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            // Remove the selected customer from the table
            customerTable.getItems().remove(selectedCustomer);
         // Remove the selected customer from the supplier's data
            supplier.getCustomerData().remove(selectedCustomer);
         // Remove the selected customer from the supplier's data and CSV file
            supplier.removeCustomer(selectedCustomer);

            System.out.println("Customer removed: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
            
            System.out.println("Customer removed: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
        } else {
            System.out.println("No customer selected.");
        }
    }
    
    @FXML
    public void refreshData(ActionEvent event) {
        // Read invoices from the CSV file and populate the table
    	supplier.getCustomerDetails();

        // Print the contents of invoices
        System.out.println("Invoices: " + supplier.getCustomerData());

        customerTable.setItems(FXCollections.observableArrayList(supplier.getCustomerData()));
    }
    
    public void removeSelectedCustomer(Customer selectedCustomer) {
        // Remove the selected customer from the table
        customerTable.getItems().remove(selectedCustomer);
        // Remove the selected customer from the supplier's data
        supplier.getCustomerData().remove(selectedCustomer);
        // Remove the selected customer from the supplier's data and CSV file
        supplier.removeCustomer(selectedCustomer);

        System.out.println("Customer removed: " + selectedCustomer.getFirstName() + " " + selectedCustomer.getLastName());
    }
   
    public int getCustomer()
    {
    	return Integer.parseInt(IdField.getText());
    }
    public Customer getSelectedCustomer() {
        return customerTable.getSelectionModel().getSelectedItem();
    }
}
