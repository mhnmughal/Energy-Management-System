package com.energy.supplier.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
    // Sample method to navigate to Customer Management screen
    @FXML
    public void toLogin(ActionEvent e)
    {
    	try {
    		 root= FXMLLoader.load(getClass().getResource("Login.fxml"));
    		stage=(Stage)((Node)e.getSource()).getScene().getWindow();
    		scene=new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    }
}