package repository;

import model.Car;
import model.Order;
import model.User;
import org.springframework.stereotype.Repository;
import util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    private final DataBaseUtil dataBaseUtil;

    public OrderRepository(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }


    public void save(Order order) {
        try (Connection con = dataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("insert into orders (order_id,car_id,user_id,status,date) " +
                    "values (?,?,?,?,?);");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCar().getId());
            preparedStatement.setInt(3, order.getClient().getId());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setDate(5, (Date) order.getDate());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Order order) {
        try (Connection con = dataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("update orders set " +
                    "order_id = ?," +
                    "car_id = ?," +
                    "user_id = ?," +
                    "status = ?, " +
                    "date = ?" +
                    " where id = ?");
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCar().getId());
            preparedStatement.setInt(3, order.getClient().getId());
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setDate(5, (Date) order.getDate());
            preparedStatement.setInt(6, order.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Order order = null;
        try (Connection con = dataBaseUtil.getConnection()) {
            preparedStatement = con.prepareStatement("select * from orders where" +
                    "id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setOrderId(resultSet.getInt("order_id"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                Car car = new Car();
                car.setId(resultSet.getInt("car_id"));
                order.setCar(car);
                order.setClient(user);
                order.setStatus(resultSet.getString("status"));
                order.setDate(resultSet.getDate("date"));
            }
            preparedStatement.close();
            resultSet.close();
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Order order) {
        try (Connection con = dataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("delete * from cars where id = ?");
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Order>> findAll() {
        try (Connection con = dataBaseUtil.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders");
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                Car car = new Car();
                car.setId(resultSet.getInt("car_id"));
                order.setCar(car);
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                order.setClient(user);
                order.setStatus(resultSet.getString("status"));
                order.setDate(resultSet.getDate("date"));
                orders.add(order);
            }
            statement.close();
            resultSet.close();
            return Optional.of(orders);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateOrderStatus(String orderId, String status) {
        try (Connection con = dataBaseUtil.getConnection()) {
            PreparedStatement preparedStatement = con.prepareStatement("update orders set status = ? where id = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, orderId);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Order>> searchOrders(Date date, User client, String status, Car car) {
        try (Connection con = dataBaseUtil.getConnection()) {
            List<Order> orders = new ArrayList<>();
            PreparedStatement preparedStatement = con.prepareStatement("select * from orders where " +
                    "date = ? and user_id = ? and status = ? and car_id = ?");
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, client.getId());
            preparedStatement.setString(3, status);
            preparedStatement.setInt(4, car.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getInt("order_id"));
                Car car1 = new Car();
                car1.setId(resultSet.getInt("car_id"));
                order.setCar(car1);
                User user1 = new User();
                user1.setId(resultSet.getInt("user_id"));
                order.setClient(user1);
                order.setStatus(resultSet.getString("status"));
                order.setDate(resultSet.getDate("date"));
                orders.add(order);
            }
            preparedStatement.close();
            resultSet.close();
            return Optional.of(orders);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
