package com.shenmajr.boot.repo.security;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shenmajr.boot.domain.security.User;

public interface UserRepo extends JpaRepository<User, String> {

	User findByUserNameAndPassword(String username, String password);

    User findByUsername(String username);

    Page<User> findByUsernameLikeOrderByCreatedTimeDesc(String username, Pageable page);

    @Query(value = "SELECT u FROM User u WHERE u.username like :username  ")
    Page<User> searchByUserName(@Param(value = "username") String username, Pageable page);
}