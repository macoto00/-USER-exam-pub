package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDTO {
    private String email;
    private String password;
    private UserRole role;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
}
