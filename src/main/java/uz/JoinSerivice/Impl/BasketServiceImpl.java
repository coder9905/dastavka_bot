package uz.JoinSerivice.Impl;

import uz.JoinSerivice.AdressService;
import uz.JoinSerivice.BasketService;
import uz.JoinSerivice.UserService;
import uz.Model.Adress;
import uz.Model.Basket;
import uz.Model.User;
import uz.result.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketServiceImpl implements BasketService {

    private static final String URL = "jdbc:postgresql://localhost:5432/eltuvchi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    UserService userService=new UserServiceImpl();
    AdressService adressService=new AdressSeviceImpl();

    @Override
    public List<Basket> getAllBasket(String userId, String region){

        List<Basket> basketList=new ArrayList<>();
        Basket basket=new Basket();

        String sql=" ";

        sql = "select * from basket";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                basket=new Basket().builder()
                        .id(resultSet.getLong("id"))
                        .adressId(resultSet.getLong("adress_id"))
                        .productsId(resultSet.getLong("products_id"))
                        .build();
                basketList.add(basket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basketList;
    }
    @Override
    public boolean save(Long id, String chatId){

        String sql=" ";

        sql = "insert into public.basket1(products_id,adress_id,chat_id) values(?,?,?)";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            Adress adress=new Adress();
            adress=adressService.getAdress(chatId);

            statement.setLong(1,id);
            statement.setLong(2,adress.getId());
            statement.setString(3,chatId);

            if (!statement.execute()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Basket getBasket(String chatId){

        String sql=" ";
        Basket basket=new Basket();

        sql = "select * from public.basket1 where chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1,chatId);

            ResultSet resultSet=statement.executeQuery();


            if (resultSet.next()){
                basket=new Basket().builder()
                        .productsId(resultSet.getLong("products_id"))
                        .adressId(resultSet.getLong("adress_id"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basket;
    }

    @Override
    public List<Basket> getAllBasket(String chatId){

        String sql=" ";
        List<Basket> basketList=new ArrayList<>();
        Basket basket=new Basket();

        sql = "select * from public.basket b INNER JOIN public.adress a on b.adress_id=a.id INNER JOIN public.user u on u.id=a.user_id where u.chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1,chatId);

            ResultSet resultSet=statement.executeQuery();


            while (resultSet.next()){
                basket=new Basket().builder()
                        .productsId(resultSet.getLong("products_id"))
                        .adressId(resultSet.getLong("adress_id"))
                        .build();
                basketList.add(basket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basketList;
    }

    @Override
    public boolean saveBasket(String chatId){

        boolean a=false;

        String sql=" ";

        sql = "insert into public.basket(products_id,adress_id) values(?,?)";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            Basket basket=new Basket();
            basket=getBasket(chatId);



            System.out.println(getBasket(chatId)+"=dfxghj");

            statement.setLong(1,basket.getProductsId());
            statement.setLong(2,basket.getAdressId());

            if (!statement.execute()){
                a=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @Override
    public boolean deleteBasket(String chatId){

        String sql=" ";

        sql = "delete from public.basket1 b where b.chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1,chatId);

            if (statement.executeUpdate()!=0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAllBasket(String chatId){

        String sql=" ";

        Long id=getAllBasketId(chatId).get(0).getId();

        sql = "DELETE FROM basket b where b.adress_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setLong(1,id);

            if (statement.executeUpdate()!=0){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Adress> getAllBasketId(String chatId){

        String sql=" ";
        Adress adress=null;
        List<Adress> adressList=new ArrayList<>();

        sql = "select a.id from basket b INNER JOIN adress a on b.adress_id=a.id INNER JOIN public.user u on a.user_id=u.id where u.chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1,chatId);

            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getLong("id"));
                adress=new Adress().builder()
                        .id(resultSet.getLong("id"))
                        .build();
                adressList.add(adress);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adressList;
    }

}
