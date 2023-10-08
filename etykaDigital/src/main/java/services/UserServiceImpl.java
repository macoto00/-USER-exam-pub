package services;

import DTOs.UpdateUserDTO;
import lombok.AllArgsConstructor;
import models.User;
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
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(updateUserDTO.getName());
            existingUser.setActive(updateUserDTO.isActive());
            existingUser.setAdult(updateUserDTO.isAdult());
            existingUser.setPocket(updateUserDTO.getPocket());

            userRepository.save(existingUser);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
