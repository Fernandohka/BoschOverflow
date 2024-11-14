package com.duo.duo.dto.LoginDto;

import java.util.ArrayList;

public record ResponseLoginDto(
    String token,
    ArrayList<String> messages
) {}
