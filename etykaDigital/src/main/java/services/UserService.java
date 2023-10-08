package services;

import DTOs.CreateUserDTO;
import DTOs.UpdateUserDTO;
import models.User;
import models.UserUpdateResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    User createUser(CreateUserDTO createUserDTO);
    UserUpdateResponse updateUser(Long userId, UpdateUserDTO updateUserDTO);
    void deleteUser(Long userId);
}
