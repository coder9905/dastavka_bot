package uz;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.JoinSerivice.*;
import uz.JoinSerivice.Impl.*;
import uz.Model.Commands;
import uz.Model.Product;
import uz.constants.Constants;
import uz.function.BasketFunction;
import uz.function.CategoryFunction;
import uz.function.MainFunctions;
import uz.function.ProductFunctions;
import uz.response.Response;
import uz.result.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Application extends TelegramLongPollingBot {

    public static final String API_Key="&sco=latlong&lang=uz";
    public static final String Look_API_Key="https://geocode-maps.yandex.ru/1.x/?apikey=e0f46766-7a95-4862-bda3-fc1a5c7a2ffe&format=json&geocode=";

    private final String username = "ItTatu_bot";
    private final String token = "5143635128:AAFFaU1RkYCXb40aIbBRP-xt2xfE7dRcr8U";

    UserService service = new UserServiceImpl();
    AdressService adressService=new AdressSeviceImpl();
    BasketService basketService=new BasketServiceImpl();
    ProductService productService=new ProductServiceImpl();
    OrderService orderService=new OrderServiceImpl();
    ProductCommendService productCommendService=new ProductCommendServiceImpl();


    MainFunctions functions = new MainFunctions();
    CategoryFunction categoryFunction=new CategoryFunction();
    ProductFunctions productFunctions=new ProductFunctions();
    BasketFunction basketFunction=new BasketFunction();

    SendPhoto sendPhoto = new SendPhoto();
    private String products=null;
    String food="";
    String food_1="";

    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Application());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {

        Long chatId = update.getMessage().getChatId();
        String cmd = null;

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));



        System.out.println("keldi=");

        if (update.getMessage() != null && update.getMessage().getText() != null) {
            cmd = update.getMessage().getText();
        }
        try {

            Commands commands1=service.getCommand(String.valueOf(chatId));
            System.out.println(commands1+"=commands1");

            if (cmd != null && cmd.equals("/start")) {
                    if (service.getUser(String.valueOf(chatId))) {

                        sendMessage.setReplyMarkup(getMenu());
                        sendMessage.setText(Constants.MENU_TANLASH);
                        service.UpdateUser(String.valueOf(chatId), Commands.MENU, update.getMessage().getChat().getFirstName(), 0);
                        sendMessage.setText("select:)");

                    } else {
                        service.UpdateUser(String.valueOf(chatId), Commands.MENU, update.getMessage().getChat().getFirstName(), 3);
                        sendMessage.setText(Constants.MENU_TANLASH);
                        sendMessage.setReplyMarkup(getMenu());
                        sendMessage.setText("select:)");
                    }
            } else if (cmd != null && cmd.equals(Constants.BUYURTMA)) {

                service.UpdateUser(String.valueOf(chatId), Commands.MENU, null, 2);
                List<Product> productList=orderService.getAllOrderProduct(String.valueOf(chatId));
                String a="";
                if (productList.size() > 0) {
                    for (int i = 0; i < productList.size(); i++) {
                        a=a+(productList.get(i).getName()+"\n");
                    }
                    sendMessage.setText(a);
                } else {
                    sendMessage.setText("Siz byurtma qilmagansz !!!");
                    sendMessage.setReplyMarkup(getMenu());
                }
            } else if (cmd != null && cmd.equals(Constants.FIKR)) {

                    service.UpdateUser(String.valueOf(chatId), Commands.FIKR, cmd, 1);
                    sendMessage.setText(Constants.FIKR_1);

            } else if (cmd != null && commands1.equals(Commands.FIKR)) {

                service.UpdateUser(String.valueOf(chatId), Commands.MENU, cmd, 1);
                sendMessage.setReplyMarkup(getMenu());
                sendMessage.setText("Select:(");

            } else if (cmd != null && commands1.equals(Commands.MENU)) {

                {
                    service.UpdateUser(String.valueOf(chatId), Commands.LOCATION, update.getMessage().getChat().getFirstName(), 2);
                    sendMessage.setText("select:(");
                    sendMessage.setReplyMarkup(functions.getKirish());
                }

            } else if (commands1.equals(Commands.LOCATION)) {

                System.out.println("location");

                if (cmd != null && Constants.ORTGA.equals(cmd)){
                    service.UpdateUser(String.valueOf(chatId), Commands.MENU, null, 2);
                    sendMessage.setReplyMarkup(getMenu());
                    sendMessage.setText("select:(");
                } else {
                    Gson gson=new Gson();
                    URL url = null;
                    try {
                        url = new URL(Look_API_Key + (update.getMessage().getLocation().getLatitude()) + "," + String.valueOf(update.getMessage().getLocation().getLongitude()) + API_Key);
                        URLConnection urlConnection = url.openConnection();
                        System.out.println(url);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        Response root = gson.fromJson(reader, Response.class);
                        String a=root.getResponse().getGeoObjectCollection().getFeatureMember().get(0).getGeoObject().getMetaDataProperty().getGeocoderMetaData().getText();
                        System.out.println(a);

                        Result result=adressService.SaveUser(String.valueOf(chatId),a);
                        System.out.println(result);
                        service.UpdateUser(String.valueOf(chatId), Commands.CATEGORY, null, 2);
                        sendMessage.setReplyMarkup(functions.getBolim());
                        sendMessage.setText("select:(");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JsonIOException e) {
                        e.printStackTrace();
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    }
                }
            } else if (commands1.equals(Commands.CATEGORY)) {

                if (cmd.equals(Constants.ORTGA)) {
                    service.UpdateUser(String.valueOf(chatId), Commands.LOCATION, null, 2);
                    sendMessage.setText("select:(");
                    sendMessage.setReplyMarkup(functions.getKirish());
                } else if (cmd.equals(Constants.SAVAT)) {

                    service.UpdateUser(String.valueOf(chatId), Commands.DOSTAVKA, null, 2);
                    List<Product> productList = productService.getAllBasket(String.valueOf(chatId));
                    System.out.println(productList.toString()+"=savatcha");
                    if (productList.size() > 0) {
                        for (int i = 0; i < productList.size(); i++) {
                            sendMessage.setText(productList.get(i).getName());
                            execute(sendMessage);
                            service.UpdateUser(String.valueOf(chatId), Commands.DOSTAVKA, null, 2);
                            sendMessage.setReplyMarkup(basketFunction.getDastavka());
                            sendMessage.setText("select:(");
                        }
                    } else {
                        sendMessage.setText("Savatingz bo'sh");
                        service.UpdateUser(String.valueOf(chatId), Commands.CATEGORY, null, 2);
                        sendMessage.setReplyMarkup(functions.getBolim());
                    }
                } else {
                    if (cmd != null) {
                        productCommendService.saveCommand(cmd, String.valueOf(chatId));
                        products=productCommendService.getCommand(String.valueOf(chatId));
                        System.out.println(products);
                        sendPhoto.setPhoto(new InputFile(new File("D:\\Java\\Zako\\Bazauchun\\Eltuvchi_bot\\img\\" + products + "\\"+products+".jpg")));
                        sendPhoto.setChatId(String.valueOf(chatId));
                        execute(sendPhoto);
                        service.UpdateUser(String.valueOf(chatId), Commands.PRODUCT, null, 2);
                        sendMessage.setReplyMarkup(categoryFunction.getCategory(products));
                        sendMessage.setText("select:)");
                    }
                }
            }else if (commands1.equals(Commands.DOSTAVKA)) {

                if (cmd.equals(Constants.ORTGA)) {
                    service.UpdateUser(String.valueOf(chatId), Commands.CATEGORY, null, 2);
                    sendMessage.setText("select:(");
                    sendMessage.setReplyMarkup(functions.getBolim());
                } else if (cmd.equals(Constants.BYURTMA)){
                    service.UpdateUser(String.valueOf(chatId), Commands.MENU, null, 2);
                    orderService.saveOrder(String.valueOf(chatId));
                    sendMessage.setReplyMarkup(getMenu());
                    sendMessage.setText("select:)");
                }
            } else if (commands1.equals(Commands.PRODUCT)) {

                if (cmd.equals(Constants.ORTGA)) {
                    service.UpdateUser(String.valueOf(chatId), Commands.CATEGORY, null, 2);
                    sendMessage.setText("select:(");
                    sendMessage.setReplyMarkup(functions.getBolim());
                } else {
                    if (cmd != null) {
                        products=productCommendService.getCommand(String.valueOf(chatId));
                        System.out.println(products+"="+cmd);
                        List<Product> productList=new ArrayList<>();
                        productList=productService.getAllProduct(products);
                        for (int i = 0; i < productList.size(); i++) {
                            if (productList.get(i).getName().equals(cmd)){
                                basketService.save(productList.get(i).getId(), String.valueOf(chatId));
                                sendPhoto.setPhoto(new InputFile(new File("D:\\Java\\Zako\\Bazauchun\\Eltuvchi_bot\\img\\" + products + "\\"+(i+1)+".jpg")));
                                sendPhoto.setChatId(String.valueOf(chatId));
                                execute(sendPhoto);
                            }
                        }
                        service.UpdateUser(String.valueOf(chatId), Commands.FINISH, null, 2);
                        sendMessage.setReplyMarkup(functions.getSavatQoshish());
                        sendMessage.setText("select:)");
                    }
                }
            } else if (commands1.equals(Commands.FINISH)) {

                if (cmd.equals(Constants.ORTGA)) {
                    products=productCommendService.getCommand(String.valueOf(chatId));
                    basketService.deleteBasket(String.valueOf(chatId));
                    service.UpdateUser(String.valueOf(chatId), Commands.PRODUCT, null, 2);
                    sendMessage.setText("select:(");
                    sendMessage.setReplyMarkup(productFunctions.getproduct(products));
                } else if (cmd.equals(Constants.SAVAT)) {

                    service.UpdateUser(String.valueOf(chatId), Commands.DOSTAVKA, null, 2);
                    List<Product> productList = productService.getAllBasket(String.valueOf(chatId));
                    System.out.println(productList.toString() + "=savatcha");
                    if (productList.size() > 0) {
                        for (int i = 0; i < productList.size(); i++) {
                            sendMessage.setText(productList.get(i).getName());
                            execute(sendMessage);
                            service.UpdateUser(String.valueOf(chatId), Commands.DOSTAVKA, null, 2);
                            sendMessage.setReplyMarkup(basketFunction.getDastavka());
                            sendMessage.setText("select:(");
                        }
                    } else {
                        sendMessage.setText("Savatingz bo'sh");
                        service.UpdateUser(String.valueOf(chatId), Commands.FINISH, null, 2);
                        sendMessage.setReplyMarkup(functions.getSavatQoshish());
                    }
                } else if (cmd.equals(Constants.BOLIM_10)){
                    System.out.println("savatga qowiw");
                    if (basketService.saveBasket(String.valueOf(chatId))){
                        System.out.println("saqlandi");
                        basketService.deleteBasket(String.valueOf(chatId));
                        service.UpdateUser(String.valueOf(chatId), Commands.CATEGORY, null, 2);
                        sendMessage.setReplyMarkup(functions.getBolim());
                        sendMessage.setText("select:)");
                    }else {
                        System.out.println("saqlanmadi");
                        service.UpdateUser(String.valueOf(chatId), Commands.FINISH, null, 2);
                        sendMessage.setReplyMarkup(functions.getBolim());
                        sendMessage.setText("select:)");
                    }
                }
            }
            if (sendMessage.getText() == null){
                sendMessage.setText("select:)");
            }
            execute(sendMessage);
            } catch(TelegramApiException telegramApiException){
                telegramApiException.printStackTrace();
            }catch(Exception e){
                e.getMessage();
            }
    }
    public ReplyKeyboardMarkup getMenu(){
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> rowList=new ArrayList<KeyboardRow>();

        KeyboardRow row=new KeyboardRow();
        KeyboardButton btn=new KeyboardButton();
        btn.setText(Constants.MENU);

        row.add(btn);
        rowList.add(row);

        row=new KeyboardRow();
        btn=new KeyboardButton();
        btn.setText(Constants.BUYURTMA);
        row.add(btn);
        rowList.add(row);

        row=new KeyboardRow();
        btn=new KeyboardButton();
        btn.setText(Constants.FIKR);
        row.add(btn);

//        btn=new KeyboardButton();
//        btn.setText(Constants.EDIT);
//        row.add(btn);

        rowList.add(row);

        replyKeyboardMarkup.setKeyboard(rowList);

        return replyKeyboardMarkup;

    }

    public String getBotUsername() {
        return username;
    }

    public String getBotToken() {
        return token;
    }
}
