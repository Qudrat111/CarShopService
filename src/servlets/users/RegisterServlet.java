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

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {
    private static UserResponse userResponse = new UserResponse();
    private static UserMapper userMapper = new UserMapper();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto userDto1 = objectMapper.readValue(req.getReader(), UserDto.class);
        boolean b = userResponse.existUser(userDto1.getUsername());
        if (b) {
            User entity = userMapper.toEntity(userDto1);
            ApiResponse register = userResponse.register(entity);
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().write(objectMapper.writeValueAsString(register.getData()));


        }
    }
}
