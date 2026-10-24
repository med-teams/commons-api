package com.medteams.commons.exception;

import com.medteams.commons.error.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionTest {

    @Test
    void shouldCarryErrorCodeOfEachException() {
        assertEquals(ErrorCode.NOT_FOUND, new ResourceNotFoundException("x").getErrorCode());
        assertEquals(ErrorCode.CONFLICT, new ConflictException("x").getErrorCode());
        assertEquals(ErrorCode.FORBIDDEN, new ForbiddenException("x").getErrorCode());
        assertEquals(ErrorCode.BUSINESS_RULE_VIOLATION, new BusinessException("x").getErrorCode());
    }

    @Test
    void shouldBuildNotFoundMessageFromResourceAndId() {
        // When
        ResourceNotFoundException exception = ResourceNotFoundException.of("Produit", 42L);

        // Then
        assertEquals("Produit introuvable : 42", exception.getMessage());
    }
}
