package uz.JoinSerivice.Impl;

import uz.JoinSerivice.CategoryService;
import uz.JoinSerivice.UserService;
import uz.Model.Basket;
import uz.Model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static final String URL = "jdbc:postgresql://localhost:5432/eltuvchi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    UserService userService=new UserServiceImpl();

    @Override
    public Category getAllCategory(String cmd){

        List<Category> categoryList=new ArrayList<>();
        Category category=new Category();
        String sql=" ";

        sql = "select * from category";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                category=new Category().builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                categoryList.add(category);
            }

            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getName().equals(cmd)){
                    return categoryList.get(i);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
