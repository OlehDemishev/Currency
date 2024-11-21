package test;

import model.User;
import model.UserRole;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.RoleService;
import service.RoleServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    public void setup() {
        // Создаём хранилище ролей
        RoleService roleService = new RoleServiceImpl(new HashMap<>());

        // Создаём репозиторий и передаём RoleService
        UserRepository userRepository = new UserRepositoryImpl(roleService);

        // Создаём UserService
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testRegisterDuplicateUser() {
        User user1 = new User(1, "Alice", "alice@example.com", "password123", UserRole.USER);
        userService.registerUser(user1);

        User user2 = new User(2, "Alice", "alice@example.com", "password123", UserRole.USER);
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(user2));
    }
}