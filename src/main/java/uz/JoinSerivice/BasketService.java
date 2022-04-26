package uz.JoinSerivice;

import uz.Model.Adress;
import uz.Model.Basket;

import java.util.List;

public interface BasketService {
    List<Basket> getAllBasket(String userId, String region);

    boolean save(Long id, String chatId);

    Basket getBasket(String chatId);

    List<Basket> getAllBasket(String chatId);

    boolean saveBasket(String chatId);

    boolean deleteBasket(String chatId);

    boolean deleteAllBasket(String chatId);

    List<Adress> getAllBasketId(String chatId);
}
