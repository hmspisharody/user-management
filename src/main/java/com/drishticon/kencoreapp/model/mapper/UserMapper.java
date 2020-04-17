package com.drishticon.kencoreapp.model.mapper;

import com.drishticon.kencoreapp.model.entity.User;
import com.drishticon.kencoreapp.model.resource.UserResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    public User mapFromResource(UserResource resource, User user) {

        if(Objects.nonNull(resource.getFirstName())) user.setFirstName(resource.getFirstName());
        if(Objects.nonNull(resource.getLastName())) user.setLastName(resource.getLastName());
        if(Objects.nonNull(resource.getEmail())) user.setEmail(resource.getEmail());
        if(Objects.nonNull(resource.getAddressLine())) user.setAddressLine(resource.getAddressLine());
        if(Objects.nonNull(resource.getCity())) user.setCity(resource.getCity());
        if(Objects.nonNull(resource.getCountry())) user.setCountry(resource.getCountry());
        if(Objects.nonNull(resource.getZipCode())) user.setZipCode(resource.getZipCode());
        if(Objects.nonNull(resource.getState())) user.setState(resource.getState());

        return user;
    }

    public UserResource mapToResource(User user) {
        return UserResource.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .addressLine(user.getAddressLine())
                .city(user.getCity())
                .state(user.getState())
                .country(user.getCountry())
                .zipCode(user.getZipCode()).build();
    }
}
