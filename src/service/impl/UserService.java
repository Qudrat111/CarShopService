package service.impl;

import model.User;
import repository.UserRepository;
import service.BaseService;

import java.util.List;
import java.util.Optional;

public class UserService implements BaseService<User> {

    UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    @Override
    public void add(User user) {
        boolean b = existUser(user.getUserName());
        if (b) {
            userRepository.save(user);
        }
    }

    public User findUser(String userName, String password) {
        User user = userRepository.findUser(userName, password);
        return user;
    }

    @Override
    public void update(User user) {
        User byId = userRepository.findById(user.getId());
        if (byId != null) {
            userRepository.save(user);
        }
    }

    @Override
    public void delete(User user) {
        User byId = userRepository.findById(user.getId());
        if (byId != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public User get(Integer id) {
        User byId = userRepository.findById(id);
        return byId;
    }

    @Override
    public List<User> findAll() {
        Optional<List<User>> all = userRepository.findAll();
        return all.get();
    }

    public boolean existUser(String userName) {
        User user = userRepository.existUser(userName);
        return user != null;
    }
}
