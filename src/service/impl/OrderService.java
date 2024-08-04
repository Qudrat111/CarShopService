package service.impl;

import model.Car;
import model.Order;
import service.BaseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService implements BaseService<Order> {
    List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order order) {
        orders.add(order);
    }

    @Override
    public void update(Order order) {
        Order byId = findById(order.getOrderId());
        if (byId != null) {
            orders.remove(byId);
            orders.add(order);
        }
    }

    @Override
    public void delete(Order order) {
        if(order.getStatus().equals("canceled")){
            orders.remove(order);
        }
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        for (Order order : orders) {
            if (order.getOrderId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public boolean updateOrderStatus(String orderId, String status) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus(status);
                return true;
            }
        }
        return false;
    }
    public boolean cancelOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus("canceled");
                return true;
            }
        }
        return false;
    }

    public List<Order> searchOrders(LocalDateTime date, String clientName, String status, Car car) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            boolean matches = true;
            if (date != null && !order.getDate().equals(date)) matches = false;
            if (clientName != null && !order.getClient().getUserName().equalsIgnoreCase(clientName)) matches = false;
            if (status != null && !order.getStatus().equalsIgnoreCase(status)) matches = false;
            if (car != null && !order.getCar().equals(car)) matches = false;
            if (matches) result.add(order);
        }
        return result;
    }

}
