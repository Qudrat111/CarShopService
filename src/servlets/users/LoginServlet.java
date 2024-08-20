package servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mappers.UserMapper;
import model.User;
import response.ApiResponse;
import response.UserResponse;

import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    private final UserResponse userResponse = new UserResponse(); // Consider dependency injection
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDto userDto = objectMapper.readValue(req.getReader(), UserDto.class);
            ApiResponse loginResponse = userResponse.login(userDto.getUsername(), userDto.getPassword());

            resp.setContentType("application/json");

            if (loginResponse.getData() != null) {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", loginResponse.getData());
                // Set status to 200 OK
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(objectMapper.writeValueAsString(loginResponse));
            } else {
                // Set status to 401 Unauthorized
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write(objectMapper.writeValueAsString(loginResponse));
            }
        } catch (Exception e) {
            // Handle exceptions like bad JSON input or other issues
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
