package com.medteams.commons.exception;

import com.medteams.commons.error.ErrorCode;

/**
 * Regle metier non respectee : la requete est bien formee mais l'operation est interdite dans l'etat
 * actuel (HTTP 422 cote appelant).
 */
public class BusinessException extends ApiException {

    public BusinessException(String message) {
        super(ErrorCode.BUSINESS_RULE_VIOLATION, message);
    }
}
