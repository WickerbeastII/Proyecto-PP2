package org.ejemplo.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class LoginException extends Exception {
    private HttpStatus statusCode;
    private String causa;

    public LoginException(HttpStatus statusCode, String message, String causa) {
        super(message);
        this.statusCode = statusCode;
        this.causa = causa;
    }
}