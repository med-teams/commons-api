# commons-api

Bibliotheque partagee par les microservices med-teams (`com.medteams:commons-api`). Elle ne depend
d'aucun framework : seule `jakarta.persistence-api` est requise, et fournie par le microservice.

| Package | Contenu |
|---|---|
| `com.medteams.commons.response` | `ApiResponse<T>` (enveloppe des reponses), `PageResponse<T>` (pagination) |
| `com.medteams.commons.error` | `ErrorCode` (code stable + statut HTTP), `ErrorResponse`, `FieldViolation` |
| `com.medteams.commons.exception` | `ApiException` et ses sous-classes : `ResourceNotFoundException` (404), `ConflictException` (409), `ForbiddenException` (403), `BusinessException` (422) |
| `com.medteams.commons.persistence` | `BaseEntity` : id, `createdAt` / `updatedAt`, `@Version` (verrou optimiste) |

## Principes

- **Une seule hierarchie d'exceptions** : chaque exception porte son `ErrorCode`, un microservice les
  traduit toutes en reponse HTTP avec un seul `@ExceptionHandler(ApiException.class)`.
- **Format d'erreur unique** pour tous les services :

      {"code": "NOT_FOUND", "message": "Produit introuvable : 42", "status": 404,
       "path": "/api/v1/products/42", "timestamp": "...", "violations": []}

- **Entites** : seule la structure technique (`BaseEntity`) est partagee. Chaque microservice reste
  proprietaire de ses entites metier et de sa base de donnees.
- `ErrorCode` est un contrat d'API : ne jamais renommer une constante existante, seulement en ajouter.

## Build

    mvn clean install

Version courante : `2.0.0-SNAPSHOT` (passage en 2.x : packages `com.medteams.*`, incompatible avec 1.x).
