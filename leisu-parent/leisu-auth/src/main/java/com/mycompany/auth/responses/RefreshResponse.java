package com.mycompany.auth.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshResponse extends UserLoginResponse {

    public RefreshResponse(String accessToken, String refreshToken) {
        super(accessToken, refreshToken);
    }
}
