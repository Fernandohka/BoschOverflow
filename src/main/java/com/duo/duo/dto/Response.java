package com.duo.duo.dto;

public record Response<T>(T object, String message) {
    
}
