package servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mappers.UserMapper;
import model.User;
import response.ApiResponse;
import response.UserResponse;

import java.io.IOException;


@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private static UserResponse userResponse = new UserResponse();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto1 = objectMapper.readValue(req.getReader(), UserDto.class);
        ApiResponse login = userResponse.login(userDto1.getUsername(), userDto1.getPassword());
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(login.getData()));
    }
}
