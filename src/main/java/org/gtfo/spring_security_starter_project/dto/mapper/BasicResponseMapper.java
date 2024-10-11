package org.gtfo.spring_security_starter_project.dto.mapper;

import org.gtfo.spring_security_starter_project.dto.template.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasicResponseMapper {
    public static <T> ResponseEntity<?> toBasicResponse(HttpStatus httpStatus, String message, T data) {
        return ResponseEntity.status(httpStatus)
                .body(
                        BasicResponse.builder()
                                .httpStatus(httpStatus.value())
                                .message(message)
                                .data(data)
                                .build()
                );
    }
}
