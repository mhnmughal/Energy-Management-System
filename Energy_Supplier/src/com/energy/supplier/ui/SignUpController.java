package com.energy.supplier.ui;

import java.io.IOException;
import com.energy.supplier.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignUpController {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Controller controller;
	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	
    // Sample method to navigate to Customer Management screen
    @FXML
    public void toBack(ActionEvent e)
    {
    	try {
    		 root= FXMLLoader.load(getClass().getResource("Login.fxml"));
    		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
    		scene=new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e1) {
    		
    		e1.printStackTrace();
    	}
    }
    @FXML
    public void AddUser(ActionEvent e)
    {
    	controller=new Controller();
    	controller.addUser(Integer.parseInt(id.getText()), name.getText(), username.getText(), password.getText());
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("User Added In Database");
    	alert.setHeaderText("Username: "+username.getText()+ "  is added successfully!");
    	alert.setContentText("If You click on Ok button then you will go to the Login page directly");
    	if(alert.showAndWait().get()== ButtonType.OK)
    	{
    		try {
       		 root= FXMLLoader.load(getClass().getResource("Login.fxml"));
       		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
       		scene=new Scene(root);
       		stage.setScene(scene);
       		stage.show();
       	} catch (IOException e1) {
       		
       		e1.printStackTrace();
       	}
    	}
    }
}
