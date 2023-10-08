package services;

import DTOs.DrinkStatisticsDTO;
import DTOs.OrderStatisticsDTO;
import DTOs.OverallStatisticsDTO;
import DTOs.UserStatisticsDTO;

public interface StatisticsService {
    OverallStatisticsDTO getOverallStatistics();

    OrderStatisticsDTO getOrderStatistics();

    DrinkStatisticsDTO getDrinkStatistics();

    UserStatisticsDTO getUserStatistics();
}
