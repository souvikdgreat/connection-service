# Connection service

## Pre-requisite

1. Docker
2. Docker compose
3. Java 17

## Docker command

### 1. To only start neo4j db
- `docker-compose up -d neo4j`
- This will start neo4j on `7474` port. Head to any browser and open http://localhost:7474.
- Programmatically to connect to `7687` on `bolt://localhost:7687`

### 2. 