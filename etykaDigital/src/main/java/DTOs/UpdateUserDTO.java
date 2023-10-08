package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDTO {
    private String email;
    private String password;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private double pocket;
}
