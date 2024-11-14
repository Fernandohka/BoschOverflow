package com.duo.duo.dto.UserDto;

import java.util.ArrayList;

public record ResponseLoginDto(
    String token,
    ArrayList<String> messages
) {}
