package response;

import model.User;
import service.impl.UserService;

import java.util.List;

public class UserResponse {
    private UserService userService =new UserService();

    public UserResponse (){

    }

    public ApiResponse register(User user){
        userService.add(user);
        return new ApiResponse("success",200,user);
    }

    public ApiResponse login(String username, String password){
        User user = userService.findUser(username, password);
        if(user != null){
            return new ApiResponse("success",200,user);
        }
        return new ApiResponse("failed",404,null);
    }

    public boolean existUser(String clientUsername) {
        return userService.existUser(clientUsername);
    }

    public ApiResponse getAllUsers(){
        List<User> all = userService.findAll();
        return new ApiResponse("success",200,all);
    }
}
