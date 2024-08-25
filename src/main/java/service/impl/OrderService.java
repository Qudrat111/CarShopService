package service.impl;

import model.Car;
import model.Order;
import model.User;
import org.springframework.stereotype.Service;
import repository.OrderRepository;
import service.BaseService;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements BaseService<Order> {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void add(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        Order byId = orderRepository.findById(order.getOrderId());
        if (byId != null) {
            orderRepository.save(order);
        }
    }

    @Override
    public void delete(Order order) {
        boolean b = cancelOrder(order.getOrderId());
        if (b) {
            orderRepository.remove(order);
        }
    }

    @Override
    public Order get(Integer id) {
        Order byId = orderRepository.findById(id);
        return byId;
    }

    @Override
    public List<Order> findAll() {
        Optional<List<Order>> all = orderRepository.findAll();
        return all.get();
    }

    public boolean updateOrderStatus(String orderId, String status) {
        Integer i = orderRepository.updateOrderStatus(orderId, status);
        return i != null;
    }

    public boolean cancelOrder(Integer orderId) {
        Order byId = orderRepository.findById(orderId);
        if (byId != null) {
            return true;
        }
        return false;
    }

    public List<Order> searchOrders(Date date, User client, String status, Car car) {
        Optional<List<Order>> orders = orderRepository.searchOrders(date, client, status, car);
        return orders.get();
    }

}
