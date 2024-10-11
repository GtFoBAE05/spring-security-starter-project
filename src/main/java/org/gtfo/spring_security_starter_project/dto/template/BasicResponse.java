package org.gtfo.spring_security_starter_project.dto.template;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicResponse<T> {
    private Integer httpStatus;
    private String message;
    private T data;
}
