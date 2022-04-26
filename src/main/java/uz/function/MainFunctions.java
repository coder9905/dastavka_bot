package uz.function;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.JoinSerivice.Impl.UserServiceImpl;
import uz.JoinSerivice.UserService;
import uz.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainFunctions {

    UserService service=new UserServiceImpl();

        public ReplyKeyboardMarkup getKirish(){

            ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> rowList=new ArrayList<KeyboardRow>();

            KeyboardRow row=new KeyboardRow();
            KeyboardButton btn=new KeyboardButton();

            btn.setText("Location");
            btn.setRequestLocation(true);
            row.add(btn);
            rowList.add(row);

            btn=new KeyboardButton();
            row=new KeyboardRow();
            btn.setText(Constants.ORTGA);
            row.add(btn);

            rowList.add(row);

            replyKeyboardMarkup.setKeyboard(rowList);

            return replyKeyboardMarkup;

        }

        public ReplyKeyboardMarkup getRuxsat(){

            ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);

            List<KeyboardRow> rowList=new ArrayList<KeyboardRow>();

            KeyboardRow row=new KeyboardRow();
            KeyboardButton btn=new KeyboardButton();

            btn.setText("Yoq");
            row.add(btn);

            btn=new KeyboardButton();
            btn.setText("✅ Ha");
            row.add(btn);
            rowList.add(row);

            row=new KeyboardRow();
            btn=new KeyboardButton();
            btn.setText(Constants.ORTGA);
            row.add(btn);

            rowList.add(row);

            replyKeyboardMarkup.setKeyboard(rowList);

            return replyKeyboardMarkup;

    }

//    public ReplyKeyboardMarkup getRegion(){
//
//        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//
//        List<KeyboardRow> rowList=new ArrayList<KeyboardRow>();
//
//        KeyboardRow row=new KeyboardRow();
//        KeyboardButton btn=new KeyboardButton();
//
//        btn.setText(Constants.REGION_1);
//        row.add(btn);
//        rowList.add(row);
//
//        row=new KeyboardRow();
//        btn=new KeyboardButton();
//        btn.setText(Constants.REGION_2);
//        row.add(btn);
//        rowList.add(row);
//
//        row=new KeyboardRow();
//        btn=new KeyboardButton();
//        btn.setText(Constants.REGION_3);
//        row.add(btn);
//        rowList.add(row);
//
//        row=new KeyboardRow();
//        btn=new KeyboardButton();
//        btn.setText(Constants.ORTGA);
//        row.add(btn);
//
//        rowList.add(row);
//
//        replyKeyboardMarkup.setKeyboard(rowList);
//
//        return replyKeyboardMarkup;
//
//    }
//
    public ReplyKeyboardMarkup getBolim(){

        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList=new ArrayList<KeyboardRow>();

        KeyboardRow row=new KeyboardRow();
        KeyboardButton btn=new KeyboardButton();


        List<String> users=new ArrayList<>();

        users=service.getCategory();

        System.out.println(users.toString()+"=users");

        for (int i = 0; i < users.size(); i++) {

            btn=new KeyboardButton();
            btn.setText(users.get(i));
            row.add(btn);

            if (i % 2 != 0 ){
                rowList.add(row);
                row=new KeyboardRow();
            }
        }

        row=new KeyboardRow();
        btn=new KeyboardButton();
        btn.setText(Constants.SAVAT);
        row.add(btn);

        btn=new KeyboardButton();
        btn.setText(Constants.ORTGA);
        row.add(btn);
        rowList.add(row);

        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;

    }

    public ReplyKeyboardMarkup getSavatQoshish(){

        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList=new ArrayList<KeyboardRow>();

        KeyboardRow row=new KeyboardRow();
        KeyboardButton btn=new KeyboardButton();

        btn.setText(Constants.BOLIM_10);
        row.add(btn);
        rowList.add(row);

        row=new KeyboardRow();
        btn=new KeyboardButton();
        btn.setText(Constants.SAVAT);
        row.add(btn);

        btn=new KeyboardButton();
        btn.setText(Constants.ORTGA);
        row.add(btn);
        rowList.add(row);

        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;

    }
//
}
