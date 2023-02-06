package com.boardcamp.api.middleware;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ErrorHandler  extends Exception {
    private String code;
    public ErrorHandler(String code, String message){
        super(message);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
