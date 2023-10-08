package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDTO {
    private String productName;
    private int totalAmount;
    private double unitPrice;
    private double summaryPrice;
}
