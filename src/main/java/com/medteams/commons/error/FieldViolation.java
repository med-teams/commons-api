package com.medteams.commons.error;

/**
 * Erreur de validation portant sur un champ de la requete.
 *
 * @param field   nom du champ (ou du parametre) invalide
 * @param message raison du refus
 */
public record FieldViolation(String field, String message) {
}
