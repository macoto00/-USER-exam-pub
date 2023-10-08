package controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summary")
public class SummaryController {

    @GetMapping("/all")
    public ResponseEntity<?> getAllSummary() {
        // Implement logic to get summary for all drinks
        return null;
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
