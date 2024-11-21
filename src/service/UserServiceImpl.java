package service;

import model.User;
import model.UserRole;
import repository.UserRepository;

import java.util.Optional;
import java.util.Scanner;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        userRepository.save(user);
        System.out.println("User registered successfully: " + user.getName());
    }

    @Override
    public Optional<User> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password));
        if (user.isPresent()) {
            System.out.println("Login successful for user: " + user.get().getName());
        } else {
            System.out.println("Login failed for email: " + email);
        }
        return user;
    }

    @Override
    public void loginUser(Scanner scanner, UserService userService, AccountService accountService, RoleService roleService) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.loginUser(email, password).ifPresentOrElse(
                user -> {
                    System.out.println("Welcome, " + user.getName() + "!");
                    if (user.getRole() == UserRole.ADMIN) {
                        System.out.println("You have admin privileges.");
                    } else if (user.getRole() == UserRole.USER) {
                        System.out.println("You are logged in as a standard user.");
                    } else {
                        System.out.println("Your account is blocked.");
                    }
                },
                () -> System.out.println("Invalid email or password.")
        );
    }

    @Override
    public void updateUserRole(int userId, UserRole userRole) {
        userRepository.findById(userId).ifPresentOrElse(
                user -> {
                    user.setRole(userRole);
                    System.out.println("Updated role for user ID " + userId + " to " + userRole);
                },
                () -> {
                    throw new IllegalArgumentException("User with ID " + userId + " not found");
                }
        );
    }
}