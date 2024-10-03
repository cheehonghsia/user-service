package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing User entities.
 *
 * <p>This controller provides CRUD operations for User resources via HTTP
 * endpoints. It handles the following operations:
 * <ul>
 *   <li>GET /api/users - Retrieve all users</li>
 *   <li>GET /api/users/{id} - Retrieve a specific user by ID</li>
 *   <li>POST /api/users - Create a new user</li>
 *   <li>PUT /api/users/{id} - Update an existing user</li>
 *   <li>DELETE /api/users/{id} - Delete a user</li>
 * </ul>
 *
 * <p>This controller delegates business logic to the {@link UserService}.
 */
@RestController
@RequestMapping("/api/users")
public final class UserController {

    /** The user service. */
    private final UserService userService;

    /**
     * Constructs a new UserController with the given UserService.
     *
     * @param service the user service to use
     */
    @Autowired
    public UserController(final UserService service) {
        this.userService = service;
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found, or a not found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable final Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    @PostMapping
    public User createUser(@RequestBody final User user) {
        return userService.addUser(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param userDetails the updated user details
     * @return the updated user if found, or a not found response
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable final Long id,
            @RequestBody final User userDetails) {
        return userService.updateUser(id, userDetails)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a user.
     *
     * @param id the ID of the user to delete
     * @return a no content response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
