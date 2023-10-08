package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserDTO {
    private String email;
    private String password;
    private Role role;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
}
