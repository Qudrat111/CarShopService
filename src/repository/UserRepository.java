package repository;

import model.User;
import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    Connection con;

    public UserRepository() {
        try {
            con = DataBaseUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into users (user_name,password,role)" +
                    "values (?,?,?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findUser(String userName, String password) {
        try {
            User user = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from users where " +
                    "user_name = ? and password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("update users set " +
                    "user_name = ?, " +
                    "password = ?," +
                    "role = ? ");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("delete * from users where id = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<List<User>> findAll() {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
            return Optional.of(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(int id) {
        try {
            User user = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User existUser(String userName) {
        try {
            User user = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from users where user_name = ?");
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
