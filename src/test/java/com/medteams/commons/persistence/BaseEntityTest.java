package com.medteams.commons.persistence;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    static class SampleEntity extends BaseEntity {
    }

    static class OtherEntity extends BaseEntity {
    }

    @Test
    void shouldSetAuditDatesOnCreate() {
        // Given
        SampleEntity entity = new SampleEntity();

        // When
        entity.onCreate();

        // Then
        assertNotNull(entity.getCreatedAt());
        assertEquals(entity.getCreatedAt(), entity.getUpdatedAt());
        assertEquals(0, entity.getCreatedAt().getNano() % 1_000, "precision microseconde, comme TIMESTAMP(6)");
    }

    @Test
    void shouldOnlyMoveUpdatedAtOnUpdate() throws InterruptedException {
        // Given
        SampleEntity entity = new SampleEntity();
        entity.onCreate();
        Thread.sleep(5);

        // When
        entity.onUpdate();

        // Then
        assertTrue(entity.getUpdatedAt().isAfter(entity.getCreatedAt()));
    }

    @Test
    void shouldCompareEntitiesById() throws ReflectiveOperationException {
        assertEquals(withId(new SampleEntity(), 1L), withId(new SampleEntity(), 1L));
        assertNotEquals(withId(new SampleEntity(), 1L), withId(new SampleEntity(), 2L));
        assertNotEquals(withId(new SampleEntity(), 1L), withId(new OtherEntity(), 1L));
    }

    @Test
    void shouldNeverConsiderUnsavedEntitiesEqual() {
        SampleEntity entity = new SampleEntity();

        assertNotEquals(new SampleEntity(), entity);
        assertEquals(entity, entity);
        assertEquals(new SampleEntity().hashCode(), entity.hashCode());
    }

    private static BaseEntity withId(BaseEntity entity, Long id) throws ReflectiveOperationException {
        Field field = BaseEntity.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(entity, id);
        return entity;
    }
}
