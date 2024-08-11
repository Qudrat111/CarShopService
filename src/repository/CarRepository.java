package repository;

import model.Car;
import util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarRepository {
    Connection con;

    public CarRepository() {
        try {
            con = DataBaseUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Car car) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into cars (make,model,year,condition,price) " +
                    "values (?,?,?,?,?);");
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setString(4, car.getCondition());
            preparedStatement.setDouble(5, car.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Car car) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("update cars set " +
                    "make = ?," +
                    "model = ?," +
                    "year = ?," +
                    "condition = ?, " +
                    "price = ?" +
                    " where id = ?");
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getYear());
            preparedStatement.setString(4, car.getCondition());
            preparedStatement.setDouble(5, car.getPrice());
            preparedStatement.setInt(6, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Car findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Car car = null;
        try {
            preparedStatement = con.prepareStatement("select * from cars where" +
                    "id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMake(resultSet.getString("make"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setCondition(resultSet.getString("condition"));
                car.setPrice(resultSet.getDouble("price"));
            }
            return car;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Car car) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("delete * from cars where id = ?");
            preparedStatement.setInt(1, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Car>> findAll() {
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from cars");
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMake(resultSet.getString("make"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setCondition(resultSet.getString("condition"));
                car.setPrice(resultSet.getDouble("price"));
                cars.add(car);
            }
            return Optional.of(cars);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Car>> getCarByDetails(String make, String model, Integer year){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from cars where " +
                    "make = ? and model = ? and year = ?");
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, year);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMake(resultSet.getString("make"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setCondition(resultSet.getString("condition"));
                car.setPrice(resultSet.getDouble("price"));
                cars.add(car);
            }
            return Optional.of(cars);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Car>> searchCars(String make, String model, int year, double price, String condition){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from cars where " +
                    "make = ? and model = ? and year = ? and price = ? and condition = ?");
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);
            preparedStatement.setInt(3, year);
            preparedStatement.setDouble(4, price);
            preparedStatement.setString(5, condition);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setMake(resultSet.getString("make"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setCondition(resultSet.getString("condition"));
                car.setPrice(resultSet.getDouble("price"));
                cars.add(car);
            }
            return Optional.of(cars);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
