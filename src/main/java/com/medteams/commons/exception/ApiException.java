package com.medteams.commons.exception;

import com.medteams.commons.error.ErrorCode;

/**
 * Racine des exceptions partagees. Chaque sous-classe porte son {@link ErrorCode} : un microservice les
 * traduit toutes en reponse HTTP avec un seul handler, sans connaitre chaque type.
 */
public abstract class ApiException extends RuntimeException {

    private final ErrorCode errorCode;

    protected ApiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
