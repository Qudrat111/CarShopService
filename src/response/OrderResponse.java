package response;

import model.Car;
import model.Order;
import model.User;
import service.impl.OrderService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrderResponse {
    private OrderService orderService = new OrderService();
    public ApiResponse createOrder(Car car, User client, String pending, Date now) {
        Order order = new Order(now,pending,client,car);
     orderService.add(order);
     return new ApiResponse("succes",200,order);
    }

    public ApiResponse getAllOrders() {
        List<Order> all = orderService.findAll();
        return new ApiResponse("succes",200,all);
    }

    public ApiResponse updateOrderStatus(String orderId, String status) {
        boolean b = orderService.updateOrderStatus(orderId, status);
        if (b){
            return new ApiResponse("succes",200,"Order updated");
        }
        return new ApiResponse("failed",404,"Order not found");

    }

    public ApiResponse cancelOrder(Integer orderId) {
        boolean b = orderService.cancelOrder(orderId);
        if (b){
            return new ApiResponse("succes",200,"Order cancelled");
        }
        return new ApiResponse("failed",404,"Order not found");
    }
}
