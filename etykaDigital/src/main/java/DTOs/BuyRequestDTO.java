package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyRequestDTO {
    private Long userId;
    private Long productId;
    private double price;
    int amount;
}
