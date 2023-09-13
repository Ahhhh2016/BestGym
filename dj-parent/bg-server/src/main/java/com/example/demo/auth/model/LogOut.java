package com.example.demo.auth.model;

import lombok.Data;

@Data
public class LogOut {

    private String accessToken;

    private String refreshToken;
}
