package service;

import model.User;
import model.UserRole;

import java.util.Optional;
import java.util.Scanner;

public interface UserService {
    void registerUser(User user); // Регистрация пользователя
    Optional<User> loginUser(String email, String password); // Авторизация пользователя
    void updateUserRole(int userId, UserRole userRole); // Обновление роли пользователя

    void loginUser(Scanner scanner, UserService userService, AccountService accountService, RoleService roleService);
}