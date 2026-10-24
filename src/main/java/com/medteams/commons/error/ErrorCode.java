package com.medteams.commons.error;

/**
 * Catalogue des codes d'erreur partages par tous les microservices, avec leur statut HTTP.
 * Le nom de chaque constante est expose tel quel dans {@link ErrorResponse#code()} : c'est un contrat
 * d'API sur lequel les clients s'appuient, il ne doit pas etre renomme.
 */
public enum ErrorCode {

    MALFORMED_REQUEST(400),
    VALIDATION_ERROR(400),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    CONFLICT(409),
    UNSUPPORTED_MEDIA_TYPE(415),
    BUSINESS_RULE_VIOLATION(422),
    INTERNAL_ERROR(500);

    private final int httpStatus;

    ErrorCode(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int httpStatus() {
        return httpStatus;
    }

    /**
     * Code correspondant a une erreur produite par le framework lui-meme (route inconnue, methode non supportee...).
     */
    public static ErrorCode fromHttpStatus(int status) {
        return switch (status) {
            case 403 -> FORBIDDEN;
            case 404 -> NOT_FOUND;
            case 405 -> METHOD_NOT_ALLOWED;
            case 409 -> CONFLICT;
            case 415 -> UNSUPPORTED_MEDIA_TYPE;
            case 422 -> BUSINESS_RULE_VIOLATION;
            default -> status >= 500 ? INTERNAL_ERROR : MALFORMED_REQUEST;
        };
    }
}
