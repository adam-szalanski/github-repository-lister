package com.example.github.repolister.githubcaller.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MessageResponse {
    private int status;
    private String message;

    public MessageResponse(HttpStatus httpStatus) {
        status = httpStatus.value();
        message = httpStatus.getReasonPhrase();
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"status\":" + status + '\n' +
                "\"Message\":'" + message + '\'' + '\n' +
                '}';
    }
}
