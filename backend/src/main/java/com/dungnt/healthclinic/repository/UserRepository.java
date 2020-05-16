package com.dungnt.healthclinic.repository;

import com.dungnt.healthclinic.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    Page<User> findByUserId(Integer userId, Pageable pageable);

//    Optional<User> findByIdAndUserId(Integer id, Integer clinicServiceId);
//
//    @Query(value = "Select ca.* from users ca, user cl where ca.service_id = cl.id", nativeQuery = true)
//    User findAllWithUserId();
}
