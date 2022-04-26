package uz.JoinSerivice.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;
import uz.JoinSerivice.BasketService;
import uz.JoinSerivice.ProductCommendService;
import uz.Model.Basket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCommendServiceImpl implements ProductCommendService {

    private static final String URL = "jdbc:postgresql://localhost:5432/eltuvchi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    BasketService basketService = new BasketServiceImpl();

    @Override
    public void saveCommand(String command, String chatId) {

        String sql = "";

        String com = getCommand(chatId);
        System.out.println(com + " " + command + chatId);
        if (!getChatId(chatId)) {
            sql = "insert into public.category_commanda(name,chat_id) values(?,?)";
        } else {
            sql = "update public.category_commanda set name=? where chat_id=?";
        }


        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1, command);
            statement.setString(2, chatId);

            if (!getChatId(chatId)) {
                if (!statement.execute()) {
                    return;
                }
            } else {
                if (statement.executeUpdate() != 0) {
                    return;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getCommand(String chatId) {

        String sql = "";
        String command = "";

        sql = "select * from public.category_commanda c where c.chat_id=? ";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1, chatId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                command = resultSet.getString("name");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return command;
    }

    @Override
    public Boolean getChatId(String chatId) {

        String sql = "";
        boolean a=false;

        sql = "select * from public.category_commanda c where c.chat_id=? ";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1, chatId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                a=true;

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return a;
    }


}
