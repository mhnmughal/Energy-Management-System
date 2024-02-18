package com.energy.supplier.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.energy.supplier.model.Invoice;

public class InvoiceTest {

    private Invoice invoice;

    @Before
    public void setUp() {
        // Initialize a new Invoice object before each test
        invoice = new Invoice();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, invoice.getCustomerId());
        assertEquals(4, invoice.getInvoiceId()); 
        assertEquals(null, invoice.getTariffType());
        assertEquals(0.0, invoice.getTotalAmount(), 0.01);
        assertEquals("UNPAID", invoice.getPayment());
    }

    @Test
    public void testSettersAndGetters() {
        invoice.setCustomerId(2);
        invoice.setInvoiceId(1001);
        invoice.setTariffType("Commercial");
        invoice.setTotalAmount(50.0);
        invoice.setPayment("PAID");

        assertEquals(2, invoice.getCustomerId());
        assertEquals(1001, invoice.getInvoiceId());
        assertEquals("Commercial", invoice.getTariffType());
        assertEquals(50.0, invoice.getTotalAmount(), 0.01);
        assertEquals("PAID", invoice.getPayment());
    }

    @Test
    public void testInvoiceIdIncrement() {
        Invoice anotherInvoice = new Invoice();
        assertEquals(2, anotherInvoice.getInvoiceId());
    }
}