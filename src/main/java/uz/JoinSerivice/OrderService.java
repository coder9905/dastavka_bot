package uz.JoinSerivice;

import uz.Model.Order;
import uz.Model.Product;

import java.util.List;

public interface OrderService {
    void saveOrder(String chatId);

    List<Order> getAllOrder(String chatId);

    List<Product> getAllOrderProduct(String userId);
}
