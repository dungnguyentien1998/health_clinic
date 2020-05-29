package com.dungnt.healthclinic.repository;

import com.dungnt.healthclinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
//    User findAllByPrivilege(Integer privilege);
    List<User> findAllByRoom(String room);

}
