package com.medteams.commons.response;

/**
 * Enveloppe standard des reponses REST reussies. Les erreurs utilisent
 * {@link com.medteams.commons.error.ErrorResponse}.
 */
public record ApiResponse<T>(boolean success, T data, String message) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> ok(T data, String message) {
        return new ApiResponse<>(true, data, message);
    }
}
