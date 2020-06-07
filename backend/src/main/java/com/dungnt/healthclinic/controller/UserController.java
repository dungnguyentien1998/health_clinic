package com.dungnt.healthclinic.controller;

import com.dungnt.healthclinic.dto.SignUpRequest;
import com.dungnt.healthclinic.model.Role;
import com.dungnt.healthclinic.model.User;
import com.dungnt.healthclinic.service.RoleService;
import com.dungnt.healthclinic.service.UserService;
import com.dungnt.healthclinic.service.impl.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) throws Exception {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        userService.save(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,
                                           @RequestBody User user) throws Exception {
        Optional<User> currentUser = userService.findById(id);
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentUser.get().setFirstName(user.getFirstName());
        currentUser.get().setLastName(user.getLastName());
        currentUser.get().setCountry(user.getCountry());
        currentUser.get().setEmail(user.getEmail());
        userService.save(currentUser.get());

        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }


    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequest signUpRequest) throws Exception {
        User user = new User();
        ValidationService.validateCredentials(signUpRequest);
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        Role role = roleService.findByName(signUpRequest.getRole());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

