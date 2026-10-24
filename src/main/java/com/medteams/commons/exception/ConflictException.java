package com.medteams.commons.exception;

import com.medteams.commons.error.ErrorCode;

/**
 * La ressource existe deja ou est dans un etat incompatible (HTTP 409 cote appelant).
 */
public class ConflictException extends ApiException {

    public ConflictException(String message) {
        super(ErrorCode.CONFLICT, message);
    }
}
