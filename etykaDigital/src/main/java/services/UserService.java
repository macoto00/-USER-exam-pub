package services;

import DTOs.UpdateUserDTO;
import models.User;
import models.UserUpdateResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    UserUpdateResponse updateUser(Long userId, UpdateUserDTO updateUserDTO);
    void deleteUser(Long userId);
    Optional<User> findByEmail(String name);
}
