package uz.JoinSerivice.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.JoinSerivice.AdressService;
import uz.JoinSerivice.UserService;
import uz.Model.Adress;
import uz.Model.User;
import uz.result.Result;

import java.sql.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdressSeviceImpl implements AdressService {

    private static final String URL = "jdbc:postgresql://localhost:5432/eltuvchi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    UserService userService=new UserServiceImpl();

    @Override
    public Result SaveUser(String userId, String region){
        String sql=" ";

        sql = "insert into public.adress(user_id,region) values(?,?)";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setLong(1, userService.getUserId(userId).getId());
            statement.setString(2,region);

            if (statement.execute()){
                return new Result(false,null,"Region saqlanmadi");
            }else {
                new Result(true,null,"Region saqlandi");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Adress getAdress(String userId){
        Adress adress=new Adress();
        String sql=" ";

        sql = "select * from public.adress a INNER JOIN public.user u on u.id=a.user_id where u.chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1,userId);

            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
                adress=new Adress().builder()
                        .id(resultSet.getLong("id"))
                        .region(resultSet.getString("region"))
                        .user_id(resultSet.getLong("user_id"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adress;
    }

}
