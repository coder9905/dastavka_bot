package uz.JoinSerivice;

import uz.Model.Category;
import uz.Model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct(String cmd);

    List<Product> getAllBasket(String userId);

    List<Product> getAllProduct(Long id);

}
