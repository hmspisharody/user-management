package com.drishticon.kencoreapp.controller;

import com.drishticon.kencoreapp.model.request.AuthenticationRequest;
import com.drishticon.kencoreapp.model.request.PasswordRequest;
import com.drishticon.kencoreapp.model.resource.UserResource;
import com.drishticon.kencoreapp.model.response.JwtAuthenticationResponse;
import com.drishticon.kencoreapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(new JwtAuthenticationResponse(userService.login(authenticationRequest)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserResource userResource) throws Exception {

        return ResponseEntity.ok(new JwtAuthenticationResponse(userService.create(userResource)));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserResource userResource) throws Exception {

        return ResponseEntity.ok(new JwtAuthenticationResponse(userService.update(userResource)));

    }

    @PostMapping("/change-password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordRequest passwordRequest) {
        return ResponseEntity.ok(userService.changePassword(passwordRequest));
    }


    //This endpoint is just for testing on a new machine
    @GetMapping("/hello")
    public String testHello() {
        return "Hello!";
    }

    @GetMapping("/user-details/{phoneNumber}")
    public ResponseEntity<?> getUserDetails(@RequestHeader String token, @PathVariable String phoneNumber) {
        return ResponseEntity.ok(userService.getDetails(phoneNumber, token));
    }
}
