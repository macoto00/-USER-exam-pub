package services;

import DTOs.SummaryDTO;
import models.Drink;

import java.util.List;

public interface DrinkService {
    List<Drink> getDrinkMenu();
    List<SummaryDTO> getDrinkSummaries();
    void updateDrink(Long id, int amount);
}
