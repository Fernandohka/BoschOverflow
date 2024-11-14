package com.duo.duo.dto.UserDto;

import java.util.ArrayList;

import com.duo.duo.model.User;

public record ResponseNewUserDto(
    User user,
    ArrayList<String> messages
) {}
