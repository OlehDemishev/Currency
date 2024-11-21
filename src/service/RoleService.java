package service;

import model.UserRole;

import java.util.Map;
import java.util.Optional;

public interface RoleService {
    void assignRole(int userId, UserRole userRole); // Назначение роли пользователю
    Optional<UserRole> getRole(int userId); // Получение роли пользователя
    Map<Integer, UserRole> getAllRoles(); // Получение всех ролей пользователей
}