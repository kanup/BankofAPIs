package com.example.login.service;


import com.example.login.dao.UserDao;
import com.example.login.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUsers(){
        return (List<User>) userDao.findAll();
    }

    public User addUser( User u){
        return userDao.save(u);
    }

    public User changePassword(User u) {
        return userDao.save(u);
    }
}
