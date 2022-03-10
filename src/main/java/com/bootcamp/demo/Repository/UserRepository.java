package com.bootcamp.demo.Repository;

import com.bootcamp.demo.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT u FROM User u WHERE u.email= ?1")
  public User findByEmail(String email);

}
