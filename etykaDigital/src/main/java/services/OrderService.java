package services;

import models.Order;

import java.util.List;

public interface OrderService {
    public boolean processPurchase(Long userId, Long productId, double productPrice, int amount);
    List<Order> getOrdersByDrinkId(Long drinkId);
}
