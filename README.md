# Connection service

## Pre-requisite

1. Docker
2. Docker compose
3. Java 17

## Docker command

### 1. To only start neo4j db
- `docker-compose up -d neo4j`
- This will start neo4j on `7474` port. Head to any browser and open http://localhost:7474.  
  Username: `neo4j` Password: `password`
- Programmatically to connect to `7687` on `bolt://localhost:7687`

### 2. Start connection API service in docker
- `docker-compose up -d`
- This will start both app on `8080` and neo4j

## Seed script
```
CREATE (p:People {name: "User0"}) return p;
CREATE (p:People {name: "User1"}) return p;
CREATE (p:People {name: "User2"}) return p;
CREATE (p:People {name: "User3"}) return p;
CREATE (p:People {name: "User4"}) return p;
CREATE (p:People {name: "User5"}) return p;
CREATE (p:People {name: "User6"}) return p;
CREATE (p:People {name: "User7"}) return p;

MATCH (p1:People {name: "User1"}),(p2:People {name: "User3"})
CREATE (p1)-[c:CONNECTED_TO {status: "CONNECTED"}]->(p2);
MATCH (p1:People {name: "User7"}),(p2:People {name: "User0"})
CREATE (p1)-[c:CONNECTED_TO {status: "CONNECTED"}]->(p2);
MATCH (p1:People {name: "User3"}),(p2:People {name: "User5"})
CREATE (p1)-[c:CONNECTED_TO {status: "PENDING"}]->(p2);
MATCH (p1:People {name: "User3"}),(p2:People {name: "User4"})
CREATE (p1)-[c:CONNECTED_TO {status: "PENDING"}]->(p2);
MATCH (p1:People {name: "User3"}),(p2:People {name: "User4"})
CREATE (p1)-[f:FOLLOWING]->(p2);
MATCH (p1:People {name: "User4"}),(p2:People {name: "User2"})
CREATE (p1)-[f:FOLLOWING]->(p2);
MATCH (p1:People {name: "User2"}),(p2:People {name: "User3"})
CREATE (p1)-[f:FOLLOWING]->(p2);
MATCH (p1:People {name: "User0"}),(p2:People {name: "User5"})
CREATE (p1)-[f:FOLLOWING]->(p2);
MATCH (p1:People {name: "User5"}),(p2:People {name: "User0"})
CREATE (p1)-[f:FOLLOWING]->(p2);

```
