package com.dungnt.healthclinic.repository;

import com.dungnt.healthclinic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    void lock(Integer id);
    void unLock(Integer id);
}
