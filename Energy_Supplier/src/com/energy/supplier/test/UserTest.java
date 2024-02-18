package com.energy.supplier.test;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.energy.supplier.model.User;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        // Initialize a new User object before each test
        user = new User();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, user.getId());
        assertEquals(null, user.getName());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        user.setId(1);
        user.setName("John Doe");
        user.setUsername("john_doe");
        user.setPassword("password123");

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
}
