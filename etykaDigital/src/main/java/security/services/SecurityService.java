package security.services;

import DTOs.RegisterUserDTO;
import lombok.RequiredArgsConstructor;
import models.Role;
import models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repositories.RoleRepository;
import repositories.UserRepository;

import java.text.Normalizer;
import java.util.Collections;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void registerUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.findByEmail(registerUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use. Please choose a different email.");
        }

        User user = new User();
        user.setName(registerUserDTO.getName());
        user.setActive(registerUserDTO.isActive());
        user.setAdult(registerUserDTO.isAdult());
        user.setPocket(registerUserDTO.getPocket());
        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        userRepository.save(user);
    }

    private String generateToken() {
        UUID tokenUUID = UUID.randomUUID();
        return tokenUUID.toString();
    }

    private String generateNickname(String name) {
        name = removeDiacritics(name);

        if (userRepository.existsByNickname(name)) {
            int index = 1;
            while (userRepository.existsByNickname(name + index)) {
                index++;
            }
            return (name + index).toLowerCase();
        }
        return (name).toLowerCase();
    }

    private String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
}
