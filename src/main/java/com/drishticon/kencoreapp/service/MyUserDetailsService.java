package com.drishticon.kencoreapp.service;

import com.drishticon.kencoreapp.model.entity.User;
import com.drishticon.kencoreapp.model.repository.UserRepository;
import com.drishticon.kencoreapp.model.util.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
    User user = userRepository.findByPhoneNumber(phoneNumber);
    if(Objects.isNull(user)) {
        throw new UsernameNotFoundException("User with phone : [" + phoneNumber + "] not found!" );
    }

    return new UserPrincipal(user);
}
}
