package com.energy.supplier.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.energy.supplier.controller.Controller;
import com.energy.supplier.model.Customer;
import com.energy.supplier.model.EnergyTariff;
import com.energy.supplier.model.Invoice;
import com.energy.supplier.model.User;

public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        // Initialize a new Controller object before each test
        controller = new Controller();
    }

    @Test
    public void testAddCustomer() {
        controller.addCustomer(1, "John", "Doe", "123 Main St", "555-1234");
        assertEquals(1, controller.getCustomerData().size());

        Customer addedCustomer = controller.getCustomerData().get(0);
        assertEquals(1, addedCustomer.getCustomerID());
        assertEquals("John", addedCustomer.getFirstName());
        assertEquals("Doe", addedCustomer.getLastName());
        assertEquals("123 Main St", addedCustomer.getAddress());
        assertEquals("555-1234", addedCustomer.getContactInfo());
    }

    @Test
    public void testAddTariff() {
        controller.addTariff(1, 1001, "Residential", 0.12, 30);
        assertEquals(1, controller.getTariffsData().size());

        EnergyTariff addedTariff = controller.getTariffsData().get(0);
        assertEquals(1, addedTariff.getCustomerId());
        assertEquals(1001, addedTariff.getTariffID());
        assertEquals("Residential", addedTariff.getTariffType());
        assertEquals(0.12, addedTariff.getPrice(), 0.01);
        assertEquals(30, addedTariff.getDuration());
    }

    @Test
    public void testAddUser() {
        controller.addUser(1, "John Doe", "john_doe", "password123");
        assertEquals(1, controller.getUser().size());

        User addedUser = controller.getUser().get(0);
        assertEquals(1, addedUser.getId());
        assertEquals("John Doe", addedUser.getName());
        assertEquals("john_doe", addedUser.getUsername());
        assertEquals("password123", addedUser.getPassword());
    }

    @Test
    public void testUpdateInfo() {
        controller.addCustomer(1, "John", "Doe", "123 Main St", "555-1234");
        controller.updateInfo(1, "Jane", "Smith", "456 Oak St", "555-5678");

        Customer updatedCustomer = controller.getCustomerData().get(0);
        assertEquals("Jane", updatedCustomer.getFirstName());
        assertEquals("Smith", updatedCustomer.getLastName());
        assertEquals("456 Oak St", updatedCustomer.getAddress());
        assertEquals("555-5678", updatedCustomer.getContactInfo());
    }

    @Test
    public void testUpdateTariff() {
        controller.addTariff(1, 1001, "Residential", 0.12, 30);
        controller.updateTariff(1001, "Commercial", 0.15, 45);

        EnergyTariff updatedTariff = controller.getTariffsData().get(0);
        assertEquals("Commercial", updatedTariff.getTariffType());
        assertEquals(0.15, updatedTariff.getPrice(), 0.01);
        assertEquals(45, updatedTariff.getDuration());
    }

    @Test
    public void testAddInvoice() {
        controller.addInvoice(1, "Residential", 50.0);
        assertEquals(1, controller.getInvoices().size());

        Invoice addedInvoice = controller.getInvoices().get(0);
        assertTrue(addedInvoice.getInvoiceId() > 0);
        assertEquals(1, addedInvoice.getCustomerId());
        assertEquals("Residential", addedInvoice.getTariffType());
        assertEquals(50.0, addedInvoice.getTotalAmount(), 0.01);
        assertEquals("UNPAID", addedInvoice.getPayment());
    }

    @Test
    public void testRemoveCustomer() {
        controller.addCustomer(1, "John", "Doe", "123 Main St", "555-1234");
        assertEquals(1, controller.getCustomerData().size());

        Customer customerToRemove = controller.getCustomerData().get(0);
        controller.removeCustomer(customerToRemove);
        assertEquals(0, controller.getCustomerData().size());
    }

    @Test
    public void testRemoveTariff() {
        controller.addTariff(1, 1001, "Residential", 0.12, 30);
        assertEquals(1, controller.getTariffsData().size());

        EnergyTariff tariffToRemove = controller.getTariffsData().get(0);
        controller.removeTariff(tariffToRemove);
        assertEquals(0, controller.getTariffsData().size());
    }
}

