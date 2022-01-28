package com.coderhouse.exception;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApiRestException extends Exception {
    private String code;
    private String message;
}
