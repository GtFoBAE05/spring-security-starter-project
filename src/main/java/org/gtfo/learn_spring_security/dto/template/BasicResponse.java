package org.gtfo.learn_spring_security.dto.template;

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
