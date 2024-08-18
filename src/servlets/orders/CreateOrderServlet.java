package servlets.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OrderDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mappers.OrderMapper;
import model.Order;
import response.ApiResponse;
import response.OrderResponse;

import java.io.IOException;

@WebServlet("/orders/createOrder")
public class CreateOrderServlet extends HttpServlet {
    private static OrderResponse orderResponse = new OrderResponse();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static OrderMapper orderMapper = new OrderMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDto orderDto = objectMapper.readValue(req.getReader(), OrderDto.class);
        Order entity = orderMapper.toEntity(orderDto);
        ApiResponse order = orderResponse.createOrder(entity.getCar(), entity.getClient(), entity.getStatus(), entity.getDate());
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(order.getData()));
    }
}
