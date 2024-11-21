package repository;

import model.User;
import model.UserRole;
import service.RoleService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<Integer, User> userStorage = new HashMap<>(); // Хранилище пользователей
    private final RoleService roleService; // Сервис для управления ролями

    // Конструктор, принимающий RoleService
    public UserRepositoryImpl(RoleService roleService) {
        if (roleService == null) {
            throw new IllegalArgumentException("RoleService cannot be null");
        }
        this.roleService = roleService;
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        userStorage.put(user.getId(), user);
        // Привязываем роль к пользователю через RoleService
        if (roleService != null) {
            roleService.assignRole(user.getId(), user.getRole());
        } else {
            throw new IllegalStateException("RoleService is not initialized");
        }
    }

    @Override
    public Optional<User> findById(int id) {
        // Ищем пользователя по ID
        Optional<User> user = Optional.ofNullable(userStorage.get(id));
        // Если пользователь найден, добавляем его роль
        user.ifPresent(u -> {
            UserRole role = roleService.getRole(id).orElse(UserRole.USER); // Роль по умолчанию — USER
            u.setRole(role);
        });
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        // Ищем пользователя по email
        return userStorage.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }
}