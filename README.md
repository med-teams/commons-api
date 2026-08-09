# commons-api

Bibliotheque partagee entre les microservices : DTOs de reponse standard (`ApiResponse`, `ErrorResponse`)
et exceptions metier communes (`BusinessException`, `ResourceNotFoundException`).

## Build local

    mvn clean install

Cela publie le jar dans le `.m2` local de la machine qui build (voir le Jenkinsfile : les jobs
commons-api et msname-service tournent sur le meme agent pour partager ce cache).
