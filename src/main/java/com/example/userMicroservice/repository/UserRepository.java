package com.example.userMicroservice.repository;

import com.example.userMicroservice.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "select usr from User usr where usr.id = :id")
    User findById(@Param(value = "id") Long id);

    @Query(value = "select usr from User usr where usr.email = :email ")
    User findByEmail(@Param(value = "email") String email);

    @Query(value = "select usr from User usr where usr.email = :email and usr.password = :password")
    User checkUserLoginCredentials(@Param(value = "email") String email, @Param(value = "password") String password);
}
