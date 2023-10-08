package controllers;

import DTOs.SummaryDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<?> getAllSummary() {
        List<SummaryDTO> summaryList = drinkService.getDrinkSummaries();

        return ResponseEntity.ok(summaryList);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getSummaryByProduct() {
        // Implement logic to get summary for each drink
        return null;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getSummaryByUser() {
        // Implement logic to get summary for each user
        return null;
    }
}
