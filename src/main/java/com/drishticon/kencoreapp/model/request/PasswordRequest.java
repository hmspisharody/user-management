package com.drishticon.kencoreapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordRequest {
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;
}
