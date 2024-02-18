package com.energy.supplier.model;

public class Invoice {
	public static int a=0;
	 private int invoiceId;
	    private int customerId;
	    private String TariffType;
	    private double totalAmount;
	    private String payment="UNPAID";
	
	

	public String getPayment() {
			return payment;
		}

		public void setPayment(String payment) {
			this.payment = payment;
		}
		
	public Invoice()
	{
		invoiceId=++a;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getTariffType() {
		return TariffType;
	}
	public void setTariffType(String TariffType) {
		this.TariffType = TariffType;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
