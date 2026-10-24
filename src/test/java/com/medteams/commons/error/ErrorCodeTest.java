package com.medteams.commons.error;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ErrorCodeTest {

    @ParameterizedTest
    @CsvSource({
            "400, MALFORMED_REQUEST",
            "401, MALFORMED_REQUEST",
            "403, FORBIDDEN",
            "404, NOT_FOUND",
            "405, METHOD_NOT_ALLOWED",
            "409, CONFLICT",
            "415, UNSUPPORTED_MEDIA_TYPE",
            "422, BUSINESS_RULE_VIOLATION",
            "500, INTERNAL_ERROR",
            "503, INTERNAL_ERROR"
    })
    void shouldMapFrameworkHttpStatusToErrorCode(int status, ErrorCode expected) {
        assertEquals(expected, ErrorCode.fromHttpStatus(status));
    }

    @Test
    void shouldExposeHttpStatusOfEachCode() {
        assertEquals(404, ErrorCode.NOT_FOUND.httpStatus());
        assertEquals(422, ErrorCode.BUSINESS_RULE_VIOLATION.httpStatus());
    }

    @Test
    void shouldBuildErrorResponseFromCode() {
        // When
        ErrorResponse response = ErrorResponse.of(ErrorCode.CONFLICT, "SKU deja utilise", "/api/v1/products");

        // Then
        assertEquals("CONFLICT", response.code());
        assertEquals(409, response.status());
        assertEquals("/api/v1/products", response.path());
        assertNotNull(response.timestamp());
        assertTrue(response.violations().isEmpty());
    }

    @Test
    void shouldKeepViolationsImmutable() {
        // Given
        List<FieldViolation> violations = new java.util.ArrayList<>(List.of(new FieldViolation("name", "obligatoire")));

        // When
        ErrorResponse response = ErrorResponse.of(ErrorCode.VALIDATION_ERROR, "Requete invalide", "/x", violations);
        violations.clear();

        // Then
        assertEquals(1, response.violations().size());
        assertThrows(UnsupportedOperationException.class, () -> response.violations().add(new FieldViolation("a", "b")));
    }
}
