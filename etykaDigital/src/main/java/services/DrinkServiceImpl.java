package services;

import DTOs.SummaryDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import models.Drink;
import org.springframework.stereotype.Service;
import repositories.DrinkRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private DrinkRepository drinkRepository;

    @Override
    public List<Drink> getDrinkMenu() {
        return drinkRepository.findAll();
    }

    @Override
    public List<SummaryDTO> getDrinkSummaries() {
        List<Drink> drinks = drinkRepository.findAll();

        List<SummaryDTO> summaryList = new ArrayList<>();
        for (Drink drink : drinks) {
            SummaryDTO summaryDTO = createSummaryDTO(drink);
            summaryList.add(summaryDTO);
        }

        return summaryList;
    }

    @Override
    @Transactional
    public void updateDrink(Long id, int amount) {
        Optional<Drink> optionalDrink = drinkRepository.findById(id);

        if (optionalDrink.isPresent()) {
            Drink drink = optionalDrink.get();

            drink.setTotalSoldAmount(drink.getTotalSoldAmount() + amount);

            drinkRepository.save(drink);
        } else {
            throw new RuntimeException("Drink not found with ID: " + id);
        }
    }

    private SummaryDTO createSummaryDTO(Drink drink) {
        int totalAmount = drink.getTotalSoldAmount();
        double unitPrice = drink.getPrice();

        double summaryPrice = calculateSummaryPrice(totalAmount, unitPrice);

        return new SummaryDTO(drink.getProductName(), totalAmount, unitPrice, summaryPrice);
    }

    private double calculateSummaryPrice(int totalAmount, double unitPrice) {
        return totalAmount * unitPrice;
    }

}
