package com.medteams.commons.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Socle technique commun des entites JPA : identifiant, dates d'audit et verrou optimiste.
 * Chaque microservice reste proprietaire de ses entites et de sa base ; seule cette structure est partagee.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Incremente a chaque mise a jour : deux modifications concurrentes ne peuvent pas s'ecraser.
    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    @PrePersist
    protected void onCreate() {
        Instant now = now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = now();
    }

    // Precision des colonnes TIMESTAMP(6) : la valeur en memoire est identique a celle relue en base.
    private static Instant now() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }

    public Long getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return id != null && id.equals(((BaseEntity) other).id);
    }

    @Override
    public int hashCode() {
        // Constant par classe : reste stable avant et apres l'attribution de l'id par la base.
        return getClass().hashCode();
    }
}
