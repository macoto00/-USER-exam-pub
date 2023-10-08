package services;

import DTOs.UpdateUserDTO;
import lombok.AllArgsConstructor;
import models.User;
import models.UserUpdateResponse;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserUpdateResponse updateUser(Long userId, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (!updateUserDTO.getEmail().equals(existingUser.getEmail())
                    && userRepository.findByEmail(updateUserDTO.getEmail()).isPresent()) {
                return new UserUpdateResponse(null, "Email is already in use by another user.");
            }

            existingUser.setName(updateUserDTO.getName());
            existingUser.setActive(updateUserDTO.isActive());
            existingUser.setAdult(updateUserDTO.isAdult());
            existingUser.setPocket(updateUserDTO.getPocket());
            existingUser.setEmail(updateUserDTO.getEmail());
            existingUser.setPassword(updateUserDTO.getPassword());

            userRepository.save(existingUser);
            return new UserUpdateResponse(existingUser, "User updated successfully.");
        }

        return new UserUpdateResponse(null, "User not found with ID: " + userId);
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findByEmail(String name) {
        return userRepository.findByEmail(name);
    }
}
