//package test;
//
//import model.Car;
//import model.Order;
//import model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import service.impl.CarService;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CarServiceTest {
//    private CarService carService;
//
//    @BeforeEach
//    void setUp() {
//        carService = new CarService();
//    }
//
//    @Test
//    void testAddCar() {
//        Car car = new Car("Toyota", "Camry", 2020, 30000, "new");
//        carService.add(car);
//        assertEquals(1, carService.getCars().size());
//    }
//
//    @Test
//    void testRemoveCar() {
//        Car car = new Car("Toyota", "Camry", 2020, 30000, "new");
//        carService.addCar(car);
//        carService.removeCar(car);
//        assertEquals(0, carService.getCars().size());
//    }
//}
//
//class UserServiceTest {
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        userService = new UserService();
//    }
//
//    @Test
//    void testAddUser() {
//        User user = new User("user1", "password1", "client");
//        userService.addUser(user);
//        assertEquals(1, userService.getUsers().size());
//    }
//
//    @Test
//    void testExistUser() {
//        User user = new User("user1", "password1", "client");
//        userService.addUser(user);
//        assertTrue(userService.existUser("user1"));
//    }
//
//    @Test
//    void testLogin() {
//        User user = new User("user1", "password1", "client");
//        userService.addUser(user);
//        assertNotNull(userService.login("user1", "password1"));
//    }
//}
//
//class OrderServiceTest {
//    private OrderService orderService;
//
//    @BeforeEach
//    void setUp() {
//        orderService = new OrderService();
//    }
//
//    @Test
//    void testCreateOrder() {
//        Car car = new Car("Toyota", "Camry", 2020, 30000, "new");
//        User user = new User("client", "password", "client");
//        Order order = orderService.createOrder(car, user, "pending", new Date());
//        assertNotNull(order);
//    }
//
//    @Test
//    void testUpdateOrderStatus() {
//        Car car = new Car("Toyota", "Camry", 2020, 30000, "new");
//        User user = new User("client", "password", "client");
//        Order order = orderService.createOrder(car, user, "pending", new Date());
//        assertTrue(orderService.updateOrderStatus(order.getOrderId(), "completed"));
//    }
//
//    @Test
//    void testCancelOrder() {
//        Car car = new Car("Toyota", "Camry", 2020, 30000, "new");
//        User user = new User("client", "password", "client");
//        Order order = orderService.createOrder(car, user, "pending", LocalDateTime.now());
//        assertTrue(orderService.cancelOrder(order.getOrderId()));
//    }
//}