package com.medteams.commons.response;

import java.util.List;
import java.util.function.Function;

/**
 * Page de resultats, independante de toute librairie de persistance.
 *
 * @param content       elements de la page
 * @param page          numero de page, a partir de 0
 * @param size          taille de page demandee
 * @param totalElements nombre total d'elements, toutes pages confondues
 * @param totalPages    nombre total de pages
 */
public record PageResponse<T>(List<T> content, int page, int size, long totalElements, int totalPages) {

    public PageResponse {
        if (page < 0) {
            throw new IllegalArgumentException("page doit etre >= 0 : " + page);
        }
        if (size < 1) {
            throw new IllegalArgumentException("size doit etre >= 1 : " + size);
        }
        content = List.copyOf(content);
    }

    public static <T> PageResponse<T> of(List<T> content, int page, int size, long totalElements) {
        // size invalide : on laisse le constructeur lever l'erreur explicite plutot qu'une division par zero
        int totalPages = size < 1 ? 0 : (int) ((totalElements + size - 1) / size);
        return new PageResponse<>(content, page, size, totalElements, totalPages);
    }

    /**
     * Convertit les elements (entite de domaine vers DTO par exemple) en conservant la pagination.
     */
    public <R> PageResponse<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mapped = content.stream().<R>map(mapper).toList();
        return new PageResponse<>(mapped, page, size, totalElements, totalPages);
    }
}
