package mappers;

import dto.UserDto;
import model.User;

public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUserName());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUsername());
        user.setRole(userDto.getRole());
        return user;
    }
}
