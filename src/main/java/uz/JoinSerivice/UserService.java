package uz.JoinSerivice;

import uz.Model.Commands;
import uz.Model.User;
import uz.Model.User1;

import java.util.List;

public interface UserService {

    boolean getUser(String chatId);

    User getUserId(String chatId);

    public void UpdateUser(String chatId, Commands command, String name, Integer number);

    public List<User1> getByurtma(String chat_id);

    public Commands getCommand(String chat_id);

    public List getCategory();



}
