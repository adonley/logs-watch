logs-watch
---
A spring boot Ethereum logs watcher / scanner / query. Puts the interesting logs into a database so they're quickly retrievable.

### Prereqs
Without docker:
* java
* gradle
* mysql

Otherwise:
* docker
* mysql

### Running
docker-compose:
`make`

Local env: `./gradlew bootRun`