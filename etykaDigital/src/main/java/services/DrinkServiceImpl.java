package services;

import lombok.AllArgsConstructor;
import models.Drink;
import org.springframework.stereotype.Service;
import repositories.DrinkRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private DrinkRepository drinkRepository;

    @Override
    public List<Drink> getDrinkMenu() {
        return drinkRepository.findAll();
    }
}
