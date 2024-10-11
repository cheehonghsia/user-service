package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.model.SavingsAccount;
import com.example.userservice.model.PaymentAccount;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initializeUsers() {
        // Add some existing users with predefined bank accounts
        User john = new User("John", "Doe", "john@example.com");
        john.addBankAccount(new SavingsAccount("123456789", 1000.00, 10d)); // Example account
        john.addBankAccount(new PaymentAccount("987654321", 1500.00, 10d)); // Example account
        addUser(john);

        User jane = new User("Jane", "Smith", "jane@example.com");
        jane.addBankAccount(new SavingsAccount("2342423", 222.00, 34)); // Example account
        jane.addBankAccount(new PaymentAccount("8787687", 23423.00, 76)); // Example account
        addUser(jane);

        User bob = new User("Bob", "Johnson", "bob@example.com");
        bob.addBankAccount(new SavingsAccount("98765", 299.32, 342)); // Example account
        bob.addBankAccount(new PaymentAccount("23434", 7282.64, 1210)); // Example account
        addUser(bob);

        User alice = new User("Alice", "Brown", "alice@example.com");
        alice.addBankAccount(new SavingsAccount("34238", 112.66, 642)); // Example account
        alice.addBankAccount(new PaymentAccount("9783", 23.96, 2342.20)); // Example account
        addUser(alice);

        User charlie = new User("Charlie", "Davis", "charlie@example.com");
        charlie.addBankAccount(new SavingsAccount("2846", 553.980, 888.8)); // Example account
        charlie.addBankAccount(new PaymentAccount("974", 344.22, 97324.342)); // Example account
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setEmail(updatedUser.getEmail());
                    return userRepository.save(existingUser);
                });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
