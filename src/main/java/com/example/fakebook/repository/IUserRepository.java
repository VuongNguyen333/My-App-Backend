package com.example.fakebook.repository;

import com.example.fakebook.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

   @Query(value = "select * from user where username = ?1", nativeQuery = true)
   Optional<User> findByUsername(String name);


   Optional<User> findByUsernameAndPassword(String username, String password);
}
