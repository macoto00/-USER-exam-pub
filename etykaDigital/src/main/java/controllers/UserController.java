package controllers;

import DTOs.CreateUserDTO;
import DTOs.UpdateUserDTO;
import exceptions.EmailAlreadyInUseException;
import lombok.AllArgsConstructor;
import models.User;
import models.UserUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
        try {
            User response = userService.createUser(createUserDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EmailAlreadyInUseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserUpdateResponse(null, e.getMessage()).getUser());
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDTO updateUserDTO) {
        User updatedUser = userService.updateUser(userId, updateUserDTO).getUser();
        if (updatedUser != null) {
            return ResponseEntity.ok().body(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
