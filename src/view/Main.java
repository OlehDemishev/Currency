package view;

import model.UserRole;
import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.AccountService;
import service.AccountServiceImpl;
import service.RoleService;
import service.RoleServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создаём хранилище ролей
        HashMap<Integer, UserRole> roleStorage = new HashMap<>();
        RoleService roleService = new RoleServiceImpl(roleStorage);

        // Создаём репозиторий и сервисы
        UserRepository userRepository = new UserRepositoryImpl(roleService);
        UserService userService = new UserServiceImpl(userRepository);
        AccountService accountService = new AccountServiceImpl(userRepository);

        while (true) {
            System.out.println("=== Currency Exchange System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Manage Roles (Admin)");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner, userService);
                    break;
                case 2:
                    loginUser(scanner, userService, accountService, roleService);
                    break;
                case 3:
                    manageRoles(scanner, roleService);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void registerUser(Scanner scanner, UserService userService) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(0, name, email, password, UserRole.USER);
        try {
            userService.registerUser(user);
            System.out.println("User registered successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void loginUser(Scanner scanner, UserService userService, AccountService accountService, RoleService roleService) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userService.loginUser(email, password).ifPresentOrElse(
                user -> {
                    System.out.println("Welcome, " + user.getName() + "!");
                    if (user.getRole() == UserRole.ADMIN) {
                        adminMenu(scanner, roleService);
                    } else if (user.getRole() == UserRole.USER) {
                        userMenu(scanner, accountService, user);
                    } else {
                        System.out.println("Your account is blocked.");
                    }
                },
                () -> System.out.println("Invalid email or password.")
        );
    }

    private static void manageRoles(Scanner scanner, RoleService roleService) {
        System.out.println("Manage roles functionality not implemented yet.");
    }

    private static void userMenu(Scanner scanner, AccountService accountService, User user) {
        System.out.println("User menu functionality not implemented yet.");
    }

    private static void adminMenu(Scanner scanner, RoleService roleService) {
        System.out.println("Admin menu functionality not implemented yet.");
    }
}