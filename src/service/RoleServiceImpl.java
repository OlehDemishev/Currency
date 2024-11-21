package service;

import model.UserRole;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    private final Map<Integer, UserRole> roleStorage = new HashMap<>();

    public RoleServiceImpl(HashMap<Integer, UserRole> roleStorage) {
    }

    @Override
    public void assignRole(int userId, UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        roleStorage.put(userId, userRole);
    }

    @Override
    public Optional<UserRole> getRole(int userId) {
        return Optional.ofNullable(roleStorage.get(userId));
    }

    @Override
    public Map<Integer, UserRole> getAllRoles() {
        return new HashMap<>(roleStorage);
    }
}