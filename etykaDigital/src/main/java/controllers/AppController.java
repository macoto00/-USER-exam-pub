package controllers;

import models.BuyRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.DrinkService;
import services.OrderService;

@RestController
@AllArgsConstructor
public class AppController {

    private final DrinkService drinkService;
    private final OrderService orderService;

    @GetMapping("/drink-menu")
    public ResponseEntity<?> getDrinkMenu() {
        return ResponseEntity.ok().body(drinkService.getDrinkMenu());
    }
    @PostMapping("/buy")
    public ResponseEntity<String> buyDrink(@RequestBody BuyRequest buyRequest) {
        Long userId = buyRequest.getUserId();
        Long productId = buyRequest.getProductId();
        double productPrice = buyRequest.getPrice();
        int amount = buyRequest.getAmount();

        boolean purchaseSuccessful = orderService.processPurchase(userId, productId, productPrice, amount);

        if (purchaseSuccessful) {
            return ResponseEntity.status(HttpStatus.OK).body("Purchase successful.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Purchase unsuccessful.");
        }
    }
}
