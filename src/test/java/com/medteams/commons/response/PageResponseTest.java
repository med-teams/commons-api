package com.medteams.commons.response;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PageResponseTest {

    @Test
    void shouldComputeTotalPages() {
        assertEquals(3, PageResponse.of(List.of("a"), 2, 10, 21).totalPages());
        assertEquals(2, PageResponse.of(List.of("a"), 1, 10, 20).totalPages());
        assertEquals(0, PageResponse.of(List.of(), 0, 10, 0).totalPages());
    }

    @Test
    void shouldMapContentAndKeepPagination() {
        // Given
        PageResponse<Integer> page = PageResponse.of(List.of(1, 2), 1, 2, 5);

        // When
        PageResponse<String> mapped = page.map(value -> "#" + value);

        // Then
        assertEquals(List.of("#1", "#2"), mapped.content());
        assertEquals(1, mapped.page());
        assertEquals(2, mapped.size());
        assertEquals(5, mapped.totalElements());
        assertEquals(3, mapped.totalPages());
    }

    @Test
    void shouldRejectInvalidPagination() {
        assertThrows(IllegalArgumentException.class, () -> PageResponse.of(List.of(), -1, 10, 0));
        assertThrows(IllegalArgumentException.class, () -> PageResponse.of(List.of(), 0, 0, 0));
    }
}
