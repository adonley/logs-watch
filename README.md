logs-watch
---
A spring boot Ethereum logs watcher / scanner / query. Puts the interesting logs into a database so they're quickly retrievable.

** It's a known issue that ethereumj won't sync fully, so this project is ded.

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
