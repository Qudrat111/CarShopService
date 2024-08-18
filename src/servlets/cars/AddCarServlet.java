package servlets.cars;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CarDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mappers.CarMapper;
import model.Car;
import response.CarResponse;

import java.io.IOException;

@WebServlet("/cars/add")
public class AddCarServlet extends HttpServlet {
    private static CarResponse carResponse = new CarResponse();
    private static CarMapper carMapper = new CarMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarDto carDto = objectMapper.readValue(req.getReader(), CarDto.class);
        Car entity = carMapper.toEntity(carDto);
        carResponse.addCar(entity);
    }
}
