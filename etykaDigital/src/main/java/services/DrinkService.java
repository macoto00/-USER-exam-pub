package services;

import DTOs.SummaryDTO;
import models.Drink;

import java.util.List;

public interface DrinkService {
    List<Drink> getDrinkMenu();
    List<SummaryDTO> getAllDrinkSummaries();
    List<SummaryDTO> getDrinkSummariesByProduct();
    List<SummaryDTO> getDrinkSummariesByUser(Long userId);
    void updateDrink(Long id, int amount);
}
