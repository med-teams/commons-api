package com.medteams.commons.exception;

import com.medteams.commons.error.ErrorCode;

/**
 * L'appelant n'a pas le droit d'effectuer l'operation demandee (HTTP 403 cote appelant).
 */
public class ForbiddenException extends ApiException {

    public ForbiddenException(String message) {
        super(ErrorCode.FORBIDDEN, message);
    }
}
