package com.drishticon.kencoreapp.model.response;

import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String jwtToken;
}
