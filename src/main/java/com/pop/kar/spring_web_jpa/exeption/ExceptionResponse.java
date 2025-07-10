package com.pop.kar.spring_web_jpa.exeption;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ExceptionResponse {

    private final Instant data;
    private final String message;
    private final String details;
}
