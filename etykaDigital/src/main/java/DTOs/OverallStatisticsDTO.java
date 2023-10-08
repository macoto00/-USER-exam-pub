package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverallStatisticsDTO {
    private long totalOrders;
    private double totalRevenue;
}
