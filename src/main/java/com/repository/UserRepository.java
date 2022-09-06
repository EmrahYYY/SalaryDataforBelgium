package com.repository;

import com.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    public User findByUserName(String userName);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByUserEmail(String email);
}
