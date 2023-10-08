package services;

import DTOs.CreateUserDTO;
import DTOs.UpdateUserDTO;
import exceptions.EmailAlreadyInUseException;
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
    public User createUser(CreateUserDTO createUserDTO) {
        if (userRepository.findByEmail(createUserDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseException("Email is already in use. Please choose a different email.");
        }

        User user = new User();
        user.setName(createUserDTO.getName());
        user.setActive(createUserDTO.isActive());
        user.setAdult(createUserDTO.isAdult());
        user.setPocket(createUserDTO.getPocket());
        user.setRole(createUserDTO.getRole());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(createUserDTO.getPassword());
        userRepository.save(user);
        return user;
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
}
