package org.weathersensor.SpringRESTClient.dto;

import lombok.Getter;

@Getter
public class JwtRequestDto {

    private String username;
    private String password;
}
