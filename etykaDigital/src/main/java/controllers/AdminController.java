package controllers;

import DTOs.DrinkStatisticsDTO;
import DTOs.OrderStatisticsDTO;
import DTOs.OverallStatisticsDTO;
import DTOs.UserStatisticsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.StatisticsService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@AllArgsConstructor
public class AdminController {

    private StatisticsService statisticsService;

    @GetMapping("/statistics")
    public ResponseEntity<?> getOverallStatistics() {
        OverallStatisticsDTO statistics = statisticsService.getOverallStatistics();
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/statistics/orders")
    public ResponseEntity<?> getOrderStatistics() {
        OrderStatisticsDTO orderStatistics = statisticsService.getOrderStatistics();
        return ResponseEntity.ok(orderStatistics);
    }

    @GetMapping("/statistics/drinks")
    public ResponseEntity<?> getDrinkStatistics() {
        DrinkStatisticsDTO drinkStatistics = statisticsService.getDrinkStatistics();
        return ResponseEntity.ok(drinkStatistics);
    }

    @GetMapping("/statistics/users")
    public ResponseEntity<?> getUserStatistics() {
        UserStatisticsDTO userStatistics = statisticsService.getUserStatistics();
        return ResponseEntity.ok(userStatistics);
    }
}
