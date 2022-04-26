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

public class CategoryFunction {

    public ReplyKeyboardMarkup getCategory(String cmd){
        ProductService productService=new ProductServiceImpl();
        List<Product> productList=productService.getAllProduct(cmd);

        if (productList.size()>0) {

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> rowList = new ArrayList<KeyboardRow>();

            KeyboardRow row = new KeyboardRow();
            KeyboardButton btn = new KeyboardButton();

            System.out.println(productList.toString() + "=category");

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
