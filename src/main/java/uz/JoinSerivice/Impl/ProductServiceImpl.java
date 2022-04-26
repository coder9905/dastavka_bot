package uz.JoinSerivice.Impl;

import uz.JoinSerivice.ProductService;
import uz.JoinSerivice.UserService;
import uz.Model.Basket;
import uz.Model.Category;
import uz.Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final String URL = "jdbc:postgresql://localhost:5432/eltuvchi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    UserService userService=new UserServiceImpl();


//    @Override
//    public List<Product> getAllProduct(String cmd){
//
//        List<Product> productList=new ArrayList<>();
//        Product product=new Product();
//
//        String sql=" ";
//
//        sql = "select * from public.products p INNER JOIN public.category c on c.id=p.category_id where c.name=?";
//
//        PreparedStatement statement = null;
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//            statement.setString(1,cmd);
//            ResultSet resultSet=statement.executeQuery();
//
//            while (resultSet.next()){
//                product=new Product().builder()
//                        .id(resultSet.getLong("id"))
//                        .name(resultSet.getString("name"))
//                        .build();
//                productList.add(product);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return productList;
//    }

    @Override
    public List<Product> getAllBasket(String userId){

        List<Product> basketList=new ArrayList<>();
        Product product=new Product();

        String sql=" ";

        sql="select * from public.basket b INNER JOIN public.products p on b.products_id=p.id INNER JOIN adress a on b.adress_id=a.id INNER JOIN public.user u on a.user_id=u.id where u.chat_id=?";
//        sql = "select * from public.basket b INNER JOIN public.products p on b.products_id=p.id INNER JOIN adress a on a.user_id=u.id INNER JOIN user u where u.chat_id=?";

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
                basketList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basketList;
    }
    @Override
    public List<Product> getAllProduct(Long id){

        List<Product> productList=new ArrayList<>();
        Product product=new Product();

        String sql=" ";

      sql = "select * from public.products p where p.category_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);
            statement.setLong(1,id);
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

    @Override
    public List<Product> getAllProduct(String cmd){

        System.out.println(cmd+"=cmd");
        List<Product> productList=new ArrayList<>();
        Product product=new Product();
        String sql=" ";

        sql = "select * from products p INNER JOIN category c on p.category_id=c.id where c.name=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);
            statement.setString(1,cmd);

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
