package com.drishticon.kencoreapp.model.repository;

import com.drishticon.kencoreapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findAllByEmail(String email);
    public User findByPhoneNumber(String phoneNumber);
    public User save(User user);
}
