package test;


import model.Car;
import model.Order;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.impl.CarService;
import service.impl.OrderService;
import service.impl.UserService;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
    }

    @Test
    void testAddCar() {
        Car car = new Car("Toyota", "Camry", 2020, "NEW", 3000000.98);
        carService.add(car);
        assertEquals(1, carService.findAll().size());
    }

    @Test
    void testRemoveCar() {
        Car car = new Car("Toyota", "Camry", 2020, "new", 3000000.98);
        carService.add(car);
        carService.delete(car);
        assertEquals(0, carService.findAll().size());
    }
}

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testAddUser() {
        User user = new User("user1", "password1", "client");
        userService.add(user);
        assertEquals(1, userService.get(user.getId()));
    }

    @Test
    void testExistUser() {
        User user = new User("user1", "password1", "client");
        userService.add(user);
        assertTrue(userService.existUser("user1"));
    }

    @Test
    void testLogin() {
        User user = new User("user1", "password1", "client");
        userService.add(user);
        assertNotNull(userService.findUser("user1", "password1"));
    }
}

class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void testCreateOrder() {
        Car car = new Car("Toyota", "Camry", 2020, "new", 3000000.98);
        User user = new User("client", "password", "client");
        Order order = new Order();
        order.setCar(car);
        order.setClient(user);
        order.setStatus("pending");
        order.setDate((java.sql.Date) new Date());
        orderService.add(order);
        assertNotNull(order);
    }

    @Test
    void testUpdateOrderStatus() {
        Car car = new Car("Toyota", "Camry", 2020, "new", 3000000.98);
        User user = new User("client", "password", "client");
        Order order = new Order();
        order.setCar(car);
        order.setClient(user);
        order.setStatus("pending");
        order.setDate((java.sql.Date) new Date());
        orderService.add(order);
        assertTrue(orderService.updateOrderStatus(String.valueOf(order.getOrderId()), "completed"));
    }

    @Test
    void testCancelOrder() {
        Car car = new Car("Toyota", "Camry", 2020, "new", 3000000.98);
        User user = new User("client", "password", "client");
        Order order = new Order();
        order.setCar(car);
        order.setClient(user);
        order.setStatus("pending");
        order.setDate((java.sql.Date) new Date());
        orderService.add(order);
        assertTrue(orderService.cancelOrder(order.getOrderId()));
    }
}