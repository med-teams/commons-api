package com.medteams.commons.error;

import java.time.Instant;
import java.util.List;

/**
 * Format unique des erreurs renvoyees par les microservices.
 *
 * @param code       code stable ({@link ErrorCode}) sur lequel les clients s'appuient
 * @param message    message lisible ; ne doit jamais exposer de detail technique
 * @param status     statut HTTP
 * @param path       URI de la requete en erreur
 * @param timestamp  instant de l'erreur
 * @param violations erreurs par champ en cas de validation, liste vide sinon
 */
public record ErrorResponse(String code, String message, int status, String path, Instant timestamp,
                            List<FieldViolation> violations) {

    public ErrorResponse {
        violations = violations == null ? List.of() : List.copyOf(violations);
    }

    public static ErrorResponse of(ErrorCode errorCode, String message, String path) {
        return of(errorCode, message, path, List.of());
    }

    public static ErrorResponse of(ErrorCode errorCode, String message, String path, List<FieldViolation> violations) {
        return new ErrorResponse(errorCode.name(), message, errorCode.httpStatus(), path, Instant.now(), violations);
    }
}
