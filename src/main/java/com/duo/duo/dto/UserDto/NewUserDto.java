package com.duo.duo.dto.UserDto;

public record NewUserDto(
    String name,
    String mail,
    String password,
    String edv
) {}
