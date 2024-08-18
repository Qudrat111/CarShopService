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

@WebServlet("/orders/updateStatus")
public class UpdateOrderStatusServlet extends HttpServlet {
    private static OrderResponse orderResponse = new OrderResponse();
    private static OrderMapper orderMapper = new OrderMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDto orderDto = objectMapper.readValue(req.getReader(), OrderDto.class);
        Order entity = orderMapper.toEntity(orderDto);
        ApiResponse apiResponse = orderResponse.updateOrderStatus(String.valueOf(entity.getId()), entity.getStatus());
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(apiResponse.getData()));
    }
}
