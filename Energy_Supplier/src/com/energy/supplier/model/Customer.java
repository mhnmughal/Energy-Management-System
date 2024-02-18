package com.energy.supplier.model;

import javafx.scene.control.Button;

public class Customer {
	 private int customerId;
	    private String firstName;
	    private String lastName;
	    private String address;
	    private String contactInfo;
	    private Button removeButton;

	    public Button getRemoveButton() {
	        return removeButton;
	    }

	    public void setRemoveButton(Button removeButton) {
	        this.removeButton = removeButton;
	    }

	    // Constructors, getters, setters
	    public Customer(int customerId,String firstName,String lastName,String address,String contactInfo)
	    {
	    	this.customerId=customerId;
	    	this.firstName=firstName;
	    	this.lastName=lastName;
	    	this.address=address;
	    	this.contactInfo=contactInfo;
	    
	    }
	    public Customer()
	    {
	    	this.customerId=0;
	    	this.firstName=null;
	    	this.lastName=null;
	    	this.address=null;
	    	this.contactInfo=null;
	    	
	    }
	    public void setCustomerID(int customerId) {
			this.customerId = customerId;
		}
	    public int getCustomerID() {
			return customerId;
		}
	    public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getFirstName() {
			return firstName;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAddress() {
			return address;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public void setContactInfo(String contactInfo) {
			this.contactInfo = contactInfo;
		}
		
		public String getContactInfo() {
			return contactInfo;
		}		
}
