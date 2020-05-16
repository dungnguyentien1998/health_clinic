package com.dungnt.healthclinic.service;

import com.dungnt.healthclinic.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Integer id);
    void save(User user);
    void lock(User user);
    void unLock(User user);
}
