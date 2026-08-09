package com.example.commons.api.exception;

/**
 * Exception levee quand une regle metier n'est pas respectee (HTTP 400 cote appelant).
 */
public class BusinessException extends RuntimeException {

    private final String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
