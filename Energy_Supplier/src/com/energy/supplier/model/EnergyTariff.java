package com.energy.supplier.model;

import javafx.scene.control.Button;

public class EnergyTariff {
	 private int tariffId;
	 private int customerId;
	    private String tariffType;
	    private double price;
	    private int duration;
	    private Button removeButton;

	    public Button getRemoveButton() {
	        return removeButton;
	    }

	    public void setRemoveButton(Button removeButton) {
	        this.removeButton = removeButton;
	    }
	    // Constructors, getters, setters

	    public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		public EnergyTariff(int Cid ,int id,String type,double price,int time )
	    {
			this.customerId=Cid;
	    	this.tariffId=id;
	    	this.tariffType=type;
	    	this.price=price;
	    	this.duration=time;
	    	
	    	
	    }
	    public EnergyTariff()
	    {/*
	    	this.customerId=0;
	    	this.tariffId=0;
	    	this.tariffType=null;
	    	this.price=0.0;
	    	this.duration=0;
	    	*/
	    	
	    }
	    public void setTariffID(int tariffID) {
			this.tariffId = tariffID;
		}
	    public int getTariffID() {
			return tariffId;
		}

	    public void setTariffType(String tariffType) {
			this.tariffType = tariffType;
		}
	    
		public String getTariffType() {
			return tariffType;
		}

		public void setPrice(double price)
		{
			this.price=price;
		}
		public double getPrice() {
			return price;
		}
		
		public void setDuration(int duration)
		{
			this.duration=duration;
		}
		public int getDuration() {
			return duration;
		}
	    public double calculateCost() {
	        // Implementation to calculate the cost based on the tariff
	        return price * duration;
	    }		
}
