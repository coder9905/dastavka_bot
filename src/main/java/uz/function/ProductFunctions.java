package uz.function;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.JoinSerivice.CategoryService;
import uz.JoinSerivice.Impl.CategoryServiceImpl;
import uz.JoinSerivice.Impl.ProductServiceImpl;
import uz.JoinSerivice.ProductService;
import uz.Model.Category;
import uz.Model.Product;
import uz.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProductFunctions {

    public ReplyKeyboardMarkup getproduct(String cmd){
        CategoryService categoryService=new CategoryServiceImpl();
        ProductService productService=new ProductServiceImpl();
        Category category=new Category();
        category=categoryService.getAllCategory(cmd);
        if (category != null) {

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> rowList = new ArrayList<KeyboardRow>();

            KeyboardRow row = new KeyboardRow();
            KeyboardButton btn = new KeyboardButton();


            List<Product> productList = new ArrayList<>();
            productList=productService.getAllProduct(category.getId());

            System.out.println(productList.toString() + "=products");

            for (int i = 0; i < productList.size(); i++) {

                btn = new KeyboardButton();
                btn.setText(productList.get(i).getName());
                row.add(btn);

                if (i % 2 != 0) {
                    rowList.add(row);
                    row = new KeyboardRow();
                }
            }

            btn = new KeyboardButton();
            btn.setText(Constants.ORTGA);
            row.add(btn);
            rowList.add(row);

            replyKeyboardMarkup.setKeyboard(rowList);

            return replyKeyboardMarkup;
        }
        return null;
    }
}
