package com.medteams.commons.exception;

import com.medteams.commons.error.ErrorCode;

/**
 * La ressource demandee n'existe pas (HTTP 404 cote appelant).
 */
public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message) {
        super(ErrorCode.NOT_FOUND, message);
    }

    /**
     * {@code ResourceNotFoundException.of("Produit", 42)} : "Produit introuvable : 42".
     */
    public static ResourceNotFoundException of(String resource, Object id) {
        return new ResourceNotFoundException(resource + " introuvable : " + id);
    }
}
