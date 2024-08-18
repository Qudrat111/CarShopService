package servlets.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import response.ApiResponse;
import response.OrderResponse;

import java.io.IOException;

@WebServlet("/orders/viewOrders")
public class ViewOrdersServlet extends HttpServlet {
    private static OrderResponse orderResponse = new OrderResponse();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiResponse allOrders = orderResponse.getAllOrders();
        resp.setContentType("application/json");
        resp.getWriter().write(allOrders.getData().toString());
    }
}
