package loaders;

import models.Drink;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repositories.DrinkRepository;

import java.util.Arrays;

@Component
public class DrinkDataLoader implements CommandLineRunner {

    private final DrinkRepository drinkRepository;

    public DrinkDataLoader(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (drinkRepository.findAll().isEmpty()) {
            loadInitialDrinks();
        }
    }

    private void loadInitialDrinks() {

        Drink drink1 = createDrink("Soda", 2.5, false);
        Drink drink2 = createDrink("Water", 1.0, false);
        Drink drink3 = createDrink("Juice", 3.0, false);
        Drink drink4 = createDrink("Beer", 5.0, true);
        Drink drink5 = createDrink("Wine", 7.0, true);

        Drink[] drinks = {drink1, drink2, drink3, drink4, drink5};
        drinkRepository.saveAll(Arrays.asList(drinks));
    }

    private Drink createDrink(String productName, double price, boolean isForAdult) {
        Drink drink = new Drink();
        drink.setProductName(productName);
        drink.setPrice(price);
        drink.setForAdult(isForAdult);
        return drink;
    }
}
