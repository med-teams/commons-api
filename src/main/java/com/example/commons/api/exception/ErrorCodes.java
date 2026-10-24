package com.example.commons.api.exception;

/**
 * Codes d'erreur standard renvoyes dans {@link com.example.commons.api.dto.ErrorResponse} par tous les microservices.
 */
public final class ErrorCodes {

    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String CONFLICT = "CONFLICT";
    public static final String FORBIDDEN = "FORBIDDEN";

    private ErrorCodes() {
    }
}
