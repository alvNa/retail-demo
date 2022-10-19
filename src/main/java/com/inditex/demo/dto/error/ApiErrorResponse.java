package com.inditex.demo.dto.error;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class ApiErrorResponse {
    private final LocalDateTime timestamp;
    private final List<ErrorDto> errors;

    public ApiErrorResponse(List<ErrorDto> errors) {
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

    public ApiErrorResponse(String message) {
        this.timestamp = LocalDateTime.now();
        this.errors = Collections.singletonList(ErrorDto.builder().description(message).build());
    }
}