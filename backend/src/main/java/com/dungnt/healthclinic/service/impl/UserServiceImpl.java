package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.model.CustomUserDetails;
import com.dungnt.healthclinic.model.User;
import com.dungnt.healthclinic.repository.UserRepository;
import com.dungnt.healthclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
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
    public Optional<User> findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Gia tri id null");
        }
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    public List<User> findAllByRoom(String room) throws Exception {
        if (room == null || room == "") {
            throw new Exception("Gia tri room null");
        }
        return userRepository.findAllByRoom(room);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.get() == null) {
            throw new UsernameNotFoundException("User not found with id = " + id);
        }
        return new CustomUserDetails(user.get());
    }
}
