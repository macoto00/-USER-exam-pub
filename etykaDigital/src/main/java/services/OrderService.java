package services;

public interface OrderService {
    public boolean processPurchase(Long userId, Long productId, double productPrice, int amount);
}
