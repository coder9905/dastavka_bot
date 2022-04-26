package uz.JoinSerivice.Impl;

import uz.JoinSerivice.BasketService;
import uz.JoinSerivice.OrderService;
import uz.Model.Basket;
import uz.Model.Order;
import uz.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final String URL = "jdbc:postgresql://localhost:5432/eltuvchi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    BasketService basketService=new BasketServiceImpl();

    @Override
    public void saveOrder(String chatId) {

        List<Basket> basketList = new ArrayList<>();
        basketList = basketService.getAllBasket(chatId);

        System.out.println("orderga keldi="+basketList.toString());

        for (int i = 0; i < basketList.size(); i++) {
            String sql = " ";

            sql = "insert into public.order(products_id,adress_id) values(?,?)";

            PreparedStatement statement = null;
            try {
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                statement = connection.prepareStatement(sql);

                statement.setLong(1, basketList.get(i).getProductsId());
                statement.setLong(2, basketList.get(i).getAdressId());

                if (!statement.execute()){
                    System.out.println("Saqlandi");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        basketService.deleteAllBasket(chatId);
    }

    @Override
    public List<Order> getAllOrder(String chatId){

        String sql=" ";
        List<Order> orderList=new ArrayList<>();
        Order order=new Order();

        sql = "select * from public.order where chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1,chatId);

            ResultSet resultSet=statement.executeQuery();


            if (resultSet.next()){
                order=new Order().builder()
                        .productsId(resultSet.getLong("products_id"))
                        .adressId(resultSet.getLong("adress_id"))
                        .time(resultSet.getTimestamp("time"))
                        .build();
                orderList.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Product> getAllOrderProduct(String userId){

        List<Product> productList=new ArrayList<>();
        Product product=new Product();

        String sql=" ";

        sql="select p.id,p.name from public.order b INNER JOIN public.products p on b.products_id=p.id INNER JOIN adress a on b.adress_id=a.id INNER JOIN public.user u on a.user_id=u.id where u.chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1,userId);

            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                product=new Product().builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

}
