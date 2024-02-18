package com.energy.supplier.controller;

import com.energy.supplier.model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Controller {
    private List<Customer> customerData = new ArrayList<>();
    private List<EnergyTariff> tariffsDetail = new ArrayList<>();
    private List<Invoice> invoices=new ArrayList<>();
    private List<User> user=new ArrayList<>();
    
    
 // Functionality to get customer data
    public List<Customer> getCustomerData() {
        return customerData;
    }
 // Functionality to get customer data
    public List<EnergyTariff> getTariffsData() {
        return tariffsDetail;
    }
 // Functionality to get customer data
    public List<Invoice> getInvoices() {
        return invoices;
    }
    // Functionality to get customer data
    public List<User> getUser() {
        return user;
    }
   
    
    // Functionality to add a customer
    public void addCustomer(int id, String fname, String lname, String address, String contact) {
        Customer customer = new Customer(id, fname, lname, address, contact);
        customerData.add(customer);
        writeCustomersToFile("customers.csv");
    }

    // Functionality to add an energy tariff
    public void addTariff(int cid, int id, String type, double price, int duration) {
        EnergyTariff tariff = new EnergyTariff(cid,id, type, price, duration);
        tariffsDetail.add(tariff);
        writeTariffsToFile("tariffs.csv");
    }
    // Functionality to add User
    public void addUser(int id,String name,String username,String password) {
     User u=new User();
     u.setId(id);
     u.setName(name);
     u.setUsername(username);
     u.setPassword(password);
     user.add(u);
     writeUserToFile("user.csv");
    }

    // Functionality to update customer information
    public void updateInfo(int id, String fname, String lname, String address, String contact) {
        for (Customer c : customerData) {
            if (c.getCustomerID() == id) {
                c.setFirstName(fname);
                c.setLastName(lname);
                c.setAddress(address);
                c.setContactInfo(contact);
                writeCustomersToFile("customers.csv");
                break;
            }
        }
    }

    // Functionality to update tariff information
    public void updateTariff(int id, String type, double price, int duration) {
        for (EnergyTariff t : tariffsDetail) {
            if (t.getTariffID() == id) {
                t.setTariffType(type);
                t.setPrice(price);
                t.setDuration(duration);
                writeTariffsToFile("tariffs.csv");
                break;
            }
        }
    }
 // Functionality to add an invoice
    public void addInvoice(int id,String type,double cost) {
        // Generate a new InvoiceId based on the existing invoices
        
        Invoice invoice = new Invoice();
        
            invoice.getInvoiceId();
            invoice.setCustomerId(id);
            invoice.setTariffType(type);
            invoice.setTotalAmount(cost);
            invoice.getPayment();
            
            invoices.add(invoice);
            writeInvoicesToFile("invoices.csv");
    }
    // Functionality to get customer details from file
    public void getCustomerDetails() 
    {
    	
        readCustomersFromFile("customers.csv");  
    }
    public void getInvoice() {
    	readInvoicesFromFile("invoices.csv");
    }

    // Functionality to get tariff details from file
    public void getTariffDetails() 
    {
        readTariffsFromFile("tariffs.csv"); 
    }
    // Functionality to get tariff details from file
    public void getUserDetails() 
    {
        readUserFromFile("user.csv");
    }

    private void writeCustomersToFile(String filePath) {
    	FileWriter fileWriter=null;
        try  {
        	 fileWriter = new FileWriter(filePath);
            fileWriter.append("CustomerID,firstName,lastName,address,contactInfo\n");
            for (Customer c : customerData) {
                fileWriter.append(String.valueOf(c.getCustomerID())).append(",");
                fileWriter.append(c.getFirstName()).append(",");
                fileWriter.append(c.getLastName()).append(",");
                fileWriter.append(c.getAddress()).append(",");
                fileWriter.append(c.getContactInfo()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
        	closeWriter(fileWriter);
        }
    }

    private void writeTariffsToFile(String filePath) {
    	FileWriter fileWriter=null;
        try  {
        	 fileWriter = new FileWriter(filePath);
            fileWriter.append("CustomerID,TariffID,TariffType,Price,Duration\n");
            for (EnergyTariff t : tariffsDetail) {
            	fileWriter.append(String.valueOf(t.getCustomerId())).append(",");
                fileWriter.append(String.valueOf(t.getTariffID())).append(",");
                fileWriter.append(t.getTariffType()).append(",");
                fileWriter.append(String.valueOf(t.getPrice())).append(",");
                fileWriter.append(String.valueOf(t.getDuration())).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
        	closeWriter(fileWriter);
        }
    }

   private void readCustomersFromFile(String filePath) {
    customerData.clear();
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filePath));
        // Skip the header line
        reader.readLine();
        
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length > 0) {
                Customer customer = new Customer();
                customer.setCustomerID(Integer.parseInt(fields[0]));
                customer.setFirstName(fields[1]);
                customer.setLastName(fields[2]);
                customer.setAddress(fields[3]);
                customer.setContactInfo(fields[4]);
                customerData.add(customer);
            }
        }
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    } finally 
    {
    	closeReader(reader);
    }
}

   private void writeUserToFile(String filePath)
   {
	   FileWriter fileWriter=null;
       try  {
       	 fileWriter = new FileWriter(filePath);
           fileWriter.append("ID,Name,Username,Password\n");
           for (User u : user) {
           	fileWriter.append(String.valueOf(u.getId())).append(",");
               fileWriter.append(u.getName()).append(",");
               fileWriter.append(u.getUsername()).append(",");
               fileWriter.append(u.getPassword()).append("\n");
               
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       finally
       {
       	closeWriter(fileWriter);
       }
   }
   private void readUserFromFile(String filePath)
   {
	   user.clear();
       BufferedReader reader = null;
       try {
           reader = new BufferedReader(new FileReader(filePath));
           // Skip the header line
           reader.readLine();
           
           String line;
           while ((line = reader.readLine()) != null) {
               String[] fields = line.split(",");
               if (fields.length > 0) {
                   User u=new User();
                   u.setId(Integer.parseInt(fields[0]));
                   u.setName(fields[1]);
                   u.setUsername(fields[2]);
                   u.setPassword(fields[3]);
                   user.add(u);
               }
           }
       } catch (IOException | NumberFormatException e) {
           e.printStackTrace();
       } finally {
           try {
               if (reader != null) {
                   reader.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
   }
    private void readTariffsFromFile(String filePath) {
    	tariffsDetail.clear();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            // Skip the header line
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    EnergyTariff tariff = new EnergyTariff();
                    tariff.setCustomerId(Integer.parseInt(fields[0]));
                    tariff.setTariffID(Integer.parseInt(fields[1]));
                    
                    tariff.setTariffType(fields[2]);
                    tariff.setPrice(Double.parseDouble(fields[3]));
                    tariff.setDuration(Integer.parseInt(fields[4]));
                    tariffsDetail.add(tariff);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 // Functionality to write invoices to CSV
    private void writeInvoicesToFile(String filePath) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
            fileWriter.append("invoiceID,customerID,tariffType,totalAmount,payment\n");
            for (Invoice invoice : invoices) {
                fileWriter.append(String.valueOf(invoice.getInvoiceId())).append(",");
                fileWriter.append(String.valueOf(invoice.getCustomerId())).append(",");
                fileWriter.append(invoice.getTariffType()).append(",");
                fileWriter.append(String.valueOf(invoice.getTotalAmount())).append(",");
                fileWriter.append(invoice.getPayment()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeWriter(fileWriter);
        }
    }
 // Functionality to read invoices from CSV
    private void readInvoicesFromFile(String filePath) {
        invoices.clear();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            // Skip the header line
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    Invoice invoice = new Invoice();
                    invoice.setInvoiceId(Integer.parseInt(fields[0]));
                    invoice.setCustomerId(Integer.parseInt(fields[1]));
                    invoice.setTariffType(fields[2]);
                    invoice.setTotalAmount(Double.parseDouble(fields[3]));
                    invoice.setPayment(fields[4]);
                    invoices.add(invoice);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            closeReader(reader);
        }
    }
 // Functionality to remove a customer
    public void removeCustomer(Customer customer) {
        customerData.remove(customer);
        writeCustomersToFile("customers.csv");
    }
 // Functionality to remove a customer
    public void removeTariff(EnergyTariff tariff) {
    	tariffsDetail.remove(tariff);
        writeCustomersToFile("tariffs.csv");
    }
    
 
 // Helper method to close FileWriter
    private void closeWriter(FileWriter writer) {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to close BufferedReader
    private void closeReader(BufferedReader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
   
}


