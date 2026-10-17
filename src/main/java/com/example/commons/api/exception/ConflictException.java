package com.example.commons.api.exception;

/**
 * Exception levee quand la ressource existe deja ou est dans un etat incompatible (HTTP 409 cote appelant).
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
