package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitializeUsers() {
        userService.initializeUsers();
        verify(userRepository, times(5)).save(any(User.class));
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(
            new User("John Doe", "john@example.com"),
            new User("Jane Smith", "jane@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("jane@example.com", result.get(1).getEmail());
    }

    @Test
    void testGetUserById_ExistingUser() {
        User user = new User("John Doe", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void testGetUserById_NonExistingUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testAddUser() {
        User user = new User("John Doe", "john@example.com");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.addUser(user);

        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testUpdateUser_ExistingUser() {
        User existingUser = new User("John Doe", "john@example.com");
        User updatedUser = new User("John Updated", "johnupdated@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<User> result = userService.updateUser(1L, updatedUser);

        assertTrue(result.isPresent());
        assertEquals("John Updated", result.get().getName());
        assertEquals("johnupdated@example.com", result.get().getEmail());
    }

    @Test
    void testUpdateUser_NonExistingUser() {
        User updatedUser = new User("John Updated", "johnupdated@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.updateUser(1L, updatedUser);

        assertFalse(result.isPresent());
    }

    @Test
    void testDeleteUser_ExistingUser() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testDeleteUser_NonExistingUser() {
        when(userRepository.existsById(1L)).thenReturn(false);

        boolean result = userService.deleteUser(1L);

        assertFalse(result);
        verify(userRepository, never()).deleteById(1L);
    }
}
