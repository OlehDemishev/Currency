package repository;

import model.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user); // Сохранение пользователя
    Optional<User> findById(int id); // Поиск пользователя по ID
    Optional<User> findByEmail(String email); // Поиск пользователя по email
}