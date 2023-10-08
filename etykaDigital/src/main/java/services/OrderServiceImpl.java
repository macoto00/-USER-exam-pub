package services;

import DTOs.UpdateUserDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import models.Order;
import models.User;
import org.springframework.stereotype.Service;
import repositories.OrderRepository;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public boolean processPurchase(Long userId, Long productId, double productPrice, int amount) {
        User user = userService.getUserById(userId).orElse(null);

        if (user == null || !user.isAdult()) {
            return false;
        }

        if (user.getPocket() < productPrice) {
            return false;
        }

        user.setPocket(user.getPocket() - productPrice);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setPocket(user.getPocket());
        userService.updateUser(userId, updateUserDTO);

        Order order = new Order();
        order.setProductName("Product name");
        order.setAmount(amount);
        order.setPrice(productPrice);
        order.setUser(user);

        orderRepository.save(order);

        return true;
    }
}
