package com.drishticon.kencoreapp.service;

import com.drishticon.kencoreapp.exception.ApiAuthenticationException;
import com.drishticon.kencoreapp.model.entity.User;
import com.drishticon.kencoreapp.model.mapper.UserMapper;
import com.drishticon.kencoreapp.model.repository.UserRepository;
import com.drishticon.kencoreapp.model.request.AuthenticationRequest;
import com.drishticon.kencoreapp.model.request.PasswordRequest;
import com.drishticon.kencoreapp.model.resource.UserResource;
import com.drishticon.kencoreapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public String login(AuthenticationRequest authenticationRequest) throws Exception{
        User user;
        if(Objects.nonNull(userRepository.findByPhoneNumber(authenticationRequest.getUsername()))) {
            user = userRepository.findByPhoneNumber(authenticationRequest.getUsername());
            if(checkPassword(authenticationRequest.getPassword(), user.getPassword())) {
                return jwtUtil.generateToken(user);
            } else {
                throw new ApiAuthenticationException("wrong password! please try again.");
            }
        } else {
            throw new ApiAuthenticationException("wrong username and/or password! please try again.");
        }
    }

    public String create(UserResource userResource) {
        User user;
        if(userRepository.findByPhoneNumber(userResource.getPhoneNumber()) == null) {
            user = User.builder().phoneNumber(userResource.getPhoneNumber()).build();
        } else {
            throw new ApiAuthenticationException("User with the given phone number already exists");
        }

        userMapper.mapFromResource(userResource, user);
        user.setPassword(encodePassword(userResource.getPassword()));
        userRepository.save(user);

        return jwtUtil.generateToken(user);
    }

    public String update(UserResource userResource) {
        User user;
        if(userRepository.findByPhoneNumber(userResource.getPhoneNumber()) == null) {
            throw new ApiAuthenticationException("User with phone : [ " + userResource.getPhoneNumber()
                    + " ] does not exist. Please signup!");
        } else {
            user = userRepository.findByPhoneNumber(userResource.getPhoneNumber());
        }

        userMapper.mapFromResource(userResource, user);
        userRepository.save(user);

        return jwtUtil.generateToken(user);
    }

    public String changePassword(PasswordRequest passwordRequest) {
        if(userRepository.findByPhoneNumber(passwordRequest.getPhoneNumber()) == null) {
            throw new ApiAuthenticationException(String.format("User with phone [%s] does not exist!"
                    , passwordRequest.getPhoneNumber()));
        } else {
            if (checkPassword(passwordRequest.getOldPassword(),
                    userRepository.findByPhoneNumber(passwordRequest.getPhoneNumber()).getPassword())) {
                User user = userRepository.findByPhoneNumber(passwordRequest.getPhoneNumber());
                user.setPassword(encodePassword(passwordRequest.getNewPassword()));
                userRepository.save(user);
                return "Password changed successfully!";
            } else {
                throw new ApiAuthenticationException("Old Password entered is incorrect");
            }
        }
    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    private String encodePassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public UserResource getDetails(String phoneNumber, String jwt) {
        if(userRepository.findByPhoneNumber(phoneNumber) == null) {
            throw new ApiAuthenticationException(String.format("User with phone [%s] does not exist!"
                    , phoneNumber));
        } else {
            User user = userRepository.findByPhoneNumber(phoneNumber);
            if(jwtUtil.validateToken(jwt, user)){
                return userMapper.mapToResource(user);
            } else {
                throw new ApiAuthenticationException("Not Authenticated");
            }
        }
    }
}
