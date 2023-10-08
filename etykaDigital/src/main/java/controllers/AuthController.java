package controllers;

import DTOs.LoginResponseDto;
import DTOs.LoginUserDTO;
import DTOs.RegisterUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import security.jwt.JwtUtils;
import security.services.SecurityService;
import services.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDTO loginUserDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(authentication);
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(authentication);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                    .body(new LoginResponseDto(userService.findByEmail(authentication.getName())
                            .orElseThrow(() -> new IllegalStateException("User not found"))
                            .getName()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid login credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDto) {
        if (securityService.userExistsByEmail(registerUserDto.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        securityService.registerUser(registerUserDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        if (jwtUtils.getRefreshTokenValidateAndGenerateAccessToken(request) != null) {
            ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(jwtUtils.getRefreshTokenValidateAndGenerateAccessToken(request));

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).build();
        }
        return ResponseEntity.badRequest().body("Refresh token is expired");
    }
}
