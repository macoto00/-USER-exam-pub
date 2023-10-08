package services;

import DTOs.UpdateUserDTO;
import models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long userId, UpdateUserDTO updateUserDTO);
    void deleteUser(Long userId);
}
