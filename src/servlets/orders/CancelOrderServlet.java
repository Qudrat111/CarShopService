package servlets.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OrderDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import response.ApiResponse;
import response.OrderResponse;

import java.io.IOException;

@WebServlet("/orders/cancel")
public class CancelOrderServlet extends HttpServlet {
    private static OrderResponse orderResponse = new OrderResponse();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDto orderDto = objectMapper.readValue(req.getReader(), OrderDto.class);
        ApiResponse apiResponse = orderResponse.cancelOrder(orderDto.getOrderId());
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(apiResponse.getData()));
    }
}
