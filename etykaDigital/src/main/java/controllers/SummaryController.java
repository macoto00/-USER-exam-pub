import DTOs.SummaryDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.DrinkService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/summary")
public class SummaryController {

    private DrinkService drinkService;

    @GetMapping("/all")
    public ResponseEntity<List<SummaryDTO>> getAllSummary() {
        List<SummaryDTO> summaryList = drinkService.getAllDrinkSummaries();
        return ResponseEntity.ok(summaryList);
    }

    @GetMapping("/product")
    public ResponseEntity<List<SummaryDTO>> getSummaryByProduct() {
        List<SummaryDTO> summaryList = drinkService.getDrinkSummariesByProduct();
        return ResponseEntity.ok(summaryList);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SummaryDTO>> getSummaryByUser(@PathVariable Long userId) {
        List<SummaryDTO> summaryList = drinkService.getDrinkSummariesByUser(userId);
        return ResponseEntity.ok(summaryList);
    }
}
