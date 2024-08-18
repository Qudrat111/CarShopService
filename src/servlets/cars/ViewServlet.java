package servlets.cars;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import response.ApiResponse;
import response.CarResponse;

import java.io.IOException;

@WebServlet("/cars/views")
public class ViewServlet extends HttpServlet {
    private static CarResponse car = new CarResponse();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApiResponse allCars = car.getAllCars();
        resp.setContentType("application/json");
        resp.getWriter().write(allCars.getData().toString());
    }
}
