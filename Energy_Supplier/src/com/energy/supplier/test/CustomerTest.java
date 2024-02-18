package com.energy.supplier.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.energy.supplier.model.Customer;



public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() {
        // Initialize a new Customer object before each test
        customer = new Customer();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, customer.getCustomerID());
        assertEquals(null, customer.getFirstName());
        assertEquals(null, customer.getLastName());
        assertEquals(null, customer.getAddress());
        assertEquals(null, customer.getContactInfo());
    }

    @Test
    public void testParameterizedConstructor() {
        Customer customer = new Customer(1, "John", "Doe", "123 Main St", "555-1234");
        assertEquals(1, customer.getCustomerID());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals("555-1234", customer.getContactInfo());
    }

    @Test
    public void testSettersAndGetters() {
        customer.setCustomerID(2);
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setAddress("456 Oak St");
        customer.setContactInfo("555-5678");

        assertEquals(2, customer.getCustomerID());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("456 Oak St", customer.getAddress());
        assertEquals("555-5678", customer.getContactInfo());
    }

  
}
