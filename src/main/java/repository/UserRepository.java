package repository;

import model.User;
import org.springframework.stereotype.Repository;
import util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class UserRepository {
   private final DataBaseUtil dataBaseUtil;

    public UserRepository(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }


    public void save(User user) {
        try (Connection con = dataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("insert into users (user_name,password,role)" +
                    "values (?,?,?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findUser(String userName, String password) {
        try (Connection con = dataBaseUtil.getConnection()) {
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
            preparedStatement.close();
            resultSet.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        try (Connection con = dataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("update users set " +
                    "user_name = ?, " +
                    "password = ?," +
                    "role = ? ");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) {
        try (Connection con = dataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("delete * from users where id = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<List<User>> findAll() {
        try (Connection con = dataBaseUtil.getConnection()) {
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
            preparedStatement.close();
            resultSet.close();
            return Optional.of(users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(int id) {
        try (Connection con = dataBaseUtil.getConnection()) {
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
            preparedStatement.close();
            resultSet.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User existUser(String userName) {
        try (Connection con = dataBaseUtil.getConnection()) {
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
            preparedStatement.close();
            resultSet.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
