package com.example.commons.api.exception;

/**
 * Exception levee quand une ressource demandee n'existe pas (HTTP 404 cote appelant).
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
