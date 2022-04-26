package uz.function;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.JoinSerivice.Impl.ProductServiceImpl;
import uz.JoinSerivice.ProductService;
import uz.Model.Product;
import uz.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class BasketFunction {

    public ReplyKeyboardMarkup getDastavka(){
        ProductService productService=new ProductServiceImpl();


            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> rowList = new ArrayList<KeyboardRow>();

            KeyboardRow row = new KeyboardRow();
            KeyboardButton btn = new KeyboardButton();

            btn.setText(Constants.BYURTMA);
            row.add(btn);

            btn = new KeyboardButton();
            btn.setText(Constants.ORTGA);
            row.add(btn);
            rowList.add(row);

            replyKeyboardMarkup.setKeyboard(rowList);

            return replyKeyboardMarkup;
    }

}
