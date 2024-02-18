package com.energy.supplier.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.energy.supplier.model.EnergyTariff;



public class EnergyTariffTest {

    private EnergyTariff energyTariff;

    @Before
    public void setUp() {
        // Initialize a new EnergyTariff object before each test
        energyTariff = new EnergyTariff();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, energyTariff.getTariffID());
        assertEquals(0, energyTariff.getCustomerId());
        assertEquals(null, energyTariff.getTariffType());
        assertEquals(0.0, energyTariff.getPrice(), 0.01);
        assertEquals(0, energyTariff.getDuration());
    }

    @Test
    public void testParameterizedConstructor() {
        EnergyTariff energyTariff = new EnergyTariff(1, 1001, "Residential", 0.12, 30);
        assertEquals(1, energyTariff.getCustomerId());
        assertEquals(1001, energyTariff.getTariffID());
        assertEquals("Residential", energyTariff.getTariffType());
        assertEquals(0.12, energyTariff.getPrice(), 0.01);
        assertEquals(30, energyTariff.getDuration());
    }

    @Test
    public void testSettersAndGetters() {
        energyTariff.setTariffID(2002);
        energyTariff.setCustomerId(2);
        energyTariff.setTariffType("Commercial");
        energyTariff.setPrice(0.15);
        energyTariff.setDuration(45);

        assertEquals(2002, energyTariff.getTariffID());
        assertEquals(2, energyTariff.getCustomerId());
        assertEquals("Commercial", energyTariff.getTariffType());
        assertEquals(0.15, energyTariff.getPrice(), 0.01);
        assertEquals(45, energyTariff.getDuration());
    }

    @Test
    public void testCalculateCost() {
        energyTariff.setPrice(0.10);
        energyTariff.setDuration(50);

        assertEquals(5.0, energyTariff.calculateCost(), 0.01);
    }

    
}
