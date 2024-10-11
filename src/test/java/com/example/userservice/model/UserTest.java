package com.example.userservice.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testDefaultConstructor() {
        User user = new User();
        assertNull(user.getId());
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
    }

    @Test
    void testParameterizedConstructor() {
        User user = new User("John", "Doe", "john@example.com");
        assertNull(user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testSetAndGetId() {
        User user = new User();
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    void testSetAndGetName() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Smith");
        assertEquals("Jane Smith", user.getFirstName() + " " + user.getLastName());
    }

    @Test
    void testSetAndGetEmail() {
        User user = new User();
        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    void testToString() {
        User user = new User("John", "Doe", "john@example.com");
        user.setId(1L);
        String expected = "User{id=1, firstName='John', lastName='Doe', email='john@example.com', bankAccounts=[]}";
        assertEquals(expected, user.toString());
    }

    @Test
    void testToStringWithNullId() {
        User user = new User("John", "Doe", "john@example.com");
        String expected = "User{id=null, firstName='John', lastName='Doe', email='john@example.com', bankAccounts=[]}";
        assertEquals(expected, user.toString());
    }
}
