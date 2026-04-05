package com.stefano.order_management_api.model.dto;

import lombok.Data;


@Data
public class LoginResponse {
    private String accessToken;
    private String refreshToken;

    public LoginResponse(String accessToken,String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
