package uz.JoinSerivice.Impl;

import uz.JoinSerivice.UserService;
import uz.Model.Commands;
import uz.Model.User;
import uz.Model.User1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {


    private static final String URL = "jdbc:postgresql://localhost:5432/eltuvchi";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0705";

    @Override
    public boolean getUser(String chatId){
        boolean users=false;

        String sql = "select * from public.user where chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);
            statement.setString(1,chatId);

            ResultSet result = statement.executeQuery();

             if (result.next()) {
                users=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserId(String chatId){

        User user=null;

        String sql = "select * from public.user where chat_id=?";

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);
            statement.setString(1,chatId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                 user=new User(
                  result.getLong("id")
                );
            }
            return user;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void UpdateUser(String chatId, Commands command, String name, Integer number){
        String sql=" ";

        if(number == 0){
            sql = "update public.user set command=?, user_name=?  where chat_id=?";
        }else if (number == 1){
            sql = "update public.user set command=?, fikr=?  where chat_id=?";
        }else if (number == 2){
            sql="update public.user set command=? where chat_id=?";
        }else {
            sql = "insert into public.user(command ,user_name ,chat_id) values(?,?,?)";
        }

        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            if (number != 2) {
                statement.setString(1, command.toString());
                statement.setString(2, name);
                statement.setString(3, chatId);
            }else {
                statement.setString(1, command.toString());
                statement.setString(2,chatId);
            }

            if (number>2){
                statement.execute();
            }else {
                if ( statement.executeUpdate() != 0 ){
                    System.out.println("succes");
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Commands getCommand(String chat_id){
        Commands commands=null;

        String sql = "select * from public.user where chat_id=?";
        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            statement = connection.prepareStatement(sql);

            statement.setString(1,chat_id);

            ResultSet resultSet=statement.executeQuery();

            if (resultSet.next()){
                commands= Commands.valueOf(resultSet.getString("command"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commands;
    }

    @Override
    public List<String> getCategory() {

        List<String> categorys=new ArrayList<>();

        String sql = "select name from category";

        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name=resultSet.getString("name");
                categorys.add(name);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


        return categorys;
    }

    @Override
    public List<User1> getByurtma(String chat_id){

        User1 user=new User1();
        List<User1> user1List=new ArrayList<>();
        String sql = "select name from public.products p INNER JOIN ";
        PreparedStatement statement = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Short id = result.getShort("basket_id");
                if (id > 0) {
                    user=new User1(

                    );
                }
                user1List.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user1List;
    }




//
//    @Override
//    public void saveUser(Integer id, String chat_id, Commands commands, String username) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        String sql = "insert into public.users(id,chat_id,command,user_name) values(?,?,?,?)";
//        PreparedStatement statement = null;
//
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//            statement.setString(2, chat_id);
//            statement.setString(3, commands.toString());
//            statement.setString(4,username);
//            statement.setInt(1, id);
//
//            if (statement.execute()) {
//                Result result1 = new Result().builder()
//                        .success(false)
//                        .message("not saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            } else {
//                Result result1 = new Result().builder()
//                        .success(true)
//                        .message("saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//    @Override
//    public User getEVOS(String chatId) {
//        String sql = "select * from evos u where u.chat_id=?";
//
//        PreparedStatement preparedStatement = null;
//        Connection connection = null;
//
//        try {
//
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, chatId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return new User()
//                        .builder()
//                        .set(resultSet.getString("sett"))
//                        .lavash(resultSet.getString("lavash"))
//                        .shaurma(resultSet.getString("shaurma"))
//                        .donar(resultSet.getString("donar"))
//                        .burger(resultSet.getString("burger"))
//                        .hotdog(resultSet.getString("hot_dog"))
//                        .ichimlik(resultSet.getString("ichimlik"))
//                        .garnir(resultSet.getString("garnir"))
//                        .region(resultSet.getString("region"))
//                        .build();
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }
//
//
//        return null;
//    }
//
//    @Override
//    public boolean getUser(String chat_id, String username) {
//
//        boolean Chat_id = false;
//
//        String sql = "select * from users where chat_id=?";
//        PreparedStatement statement = null;
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//
//            statement.setString(1,chat_id);
//
//            ResultSet result = statement.executeQuery();
//
//            while (result.next()) {
//                String a = result.getString("chat_id");
//                if (chat_id.equals(a)) {
//                    Chat_id = true;
//                    break;
//                } else {
//                    saveUser(1,chat_id, Commands.MENU, username);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Chat_id;
//    }
//
//    @Override
//    public void UpdateUserRegion(String chat_id, Commands commands, String region, Integer number) {
//        Scanner scanner = new Scanner(System.in);
//        String sql="";
//        if (number==0){
//            sql = "update adress  set command=? where chat_id=?";
//        }else {
//            sql = "update adress  set command=?, region=? where chat_id=?";
//        }
//        PreparedStatement statement = null;
//
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//
//            if (number==0){
//                statement.setString(1, commands.toString());
//                statement.setString(2, chat_id);
//            }else {
//                statement.setString(1, commands.toString());
//                statement.setString(2, region);
//                statement.setString(3,chat_id);
//            }
//
//            if (statement.executeUpdate() ==0) {
//                Result result1 = new Result().builder()
//                        .success(false)
//                        .message("not saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            } else {
//                Result result1 = new Result().builder()
//                        .success(true)
//                        .message("saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//
//
//    @Override

//
//    @Override
//    public void UpdateUser(String chat_id, String fikr) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        String sql = "update public.users set fikr=? where chat_id=?";
//        PreparedStatement statement = null;
//
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, fikr);
//            statement.setString(2, chat_id);
//
//            if (statement.executeUpdate()==0) {
//                Result result1 = new Result().builder()
//                        .success(false)
//                        .message("not saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            } else {
//                Result result1 = new Result().builder()
//                        .success(true)
//                        .message("saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void UpdateSavat(String chat_id, String foot, String cmd) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        String sql = "update public.evos set "+foot+"=? where chat_id=?";
//        PreparedStatement statement = null;
//
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, cmd);
//            statement.setString(2, chat_id);
//
//            if (statement.executeUpdate()==0) {
//                Result result1 = new Result().builder()
//                        .success(false)
//                        .message("not saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            } else {
//                Result result1 = new Result().builder()
//                        .success(true)
//                        .message("saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void SaveByurtma(String chat_id, String foot, String region) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        String sql = "insert into public.byurtma(chat_id,byurtma,region) values(?,?,?)";
//        PreparedStatement statement = null;
//
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, chat_id);
//            statement.setString(2, foot);
//            statement.setString(3,region);
//
//            if (statement.execute()) {
//                Result result1 = new Result().builder()
//                        .success(false)
//                        .message("not saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            } else {
//                Result result1 = new Result().builder()
//                        .success(true)
//                        .message("saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public void UpdateEVOS(String chat_id) {
//
//        Scanner scanner = new Scanner(System.in);
//
//        String sql = "update public.evos set sett=?,lavash=?,shaurma=?,donar=?,burger=?,hot_dog=?,ichimlik=?,garnir=? where chat_id=?";
//        PreparedStatement statement = null;
//
//        try {
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, null);
//            statement.setString(2, null);
//            statement.setString(3, null);
//            statement.setString(4, null);
//            statement.setString(5, null);
//            statement.setString(6, null);
//            statement.setString(7, null);
//            statement.setString(8, null);
//            statement.setString(9,chat_id);
//            if (statement.executeUpdate()==0) {
//                Result result1 = new Result().builder()
//                        .success(false)
//                        .message("not saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            } else {
//                Result result1 = new Result().builder()
//                        .success(true)
//                        .message("saved:(")
//                        .build();
//                System.out.println(result1.getMessage());
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//




}

