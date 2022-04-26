package uz.JoinSerivice;

public interface ProductCommendService {

    void saveCommand(String command, String chatId);

    String getCommand(String chatId);

    Boolean getChatId(String chatId);
}
