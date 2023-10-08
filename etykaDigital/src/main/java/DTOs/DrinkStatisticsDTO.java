package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkStatisticsDTO {
    private String drinkName;
    private int totalOrders;
    private double totalRevenue;
}
