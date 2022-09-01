package com.services.implementations;


import com.data.User;
import com.repository.UserRepository;
import com.services.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
}
