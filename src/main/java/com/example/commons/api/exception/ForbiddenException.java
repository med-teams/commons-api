package com.example.commons.api.exception;

/**
 * Exception levee quand l'appelant n'a pas le droit d'effectuer l'operation demandee (HTTP 403 cote appelant).
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
