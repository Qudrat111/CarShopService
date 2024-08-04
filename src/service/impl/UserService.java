package service.impl;

import model.User;
import service.BaseService;

import java.util.ArrayList;
import java.util.List;

public class UserService implements BaseService<User> {

    private List<User> users = new ArrayList<>();

    public UserService() {
            users.add(new User("admin", "admin123", "admin"));
            users.add(new User("manager", "manager123", "manager"));
            users.add(new User("client", "client123", "client"));
    }

    @Override
    public void add(User user) {
      if(!existUser(user.getUserName())){
          users.add(user);

      }
    }

    public User findUser(String userName,String password){
        for(User user : users){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(User user) {
        if(user.getUserName() != null &&
        user.getPassword() != null &&
        user.getRole() != null){
            User byId = findById(user.getId());
            if(byId != null){
                users.remove(byId);
                users.add(user);
            }
        }
    }

    @Override
    public void delete(User user) {
        User byId = findById(user.getId());
        if(byId != null) {
            users.remove(byId);
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Integer id) {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public boolean existUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}
