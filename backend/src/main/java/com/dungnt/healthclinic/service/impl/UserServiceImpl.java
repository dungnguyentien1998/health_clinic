package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.model.User;
import com.dungnt.healthclinic.repository.UserRepository;
import com.dungnt.healthclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void lock(User user) {

    }

    @Override
    public void unLock(User user) {

    }
}
