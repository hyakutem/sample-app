## build docker image

```
git clone https://gitlab.raftel.in/hyakutake/sample-app.git
cd sample-app/
./gradlew bootBuildImage
```

↓

```
$ docker images
REPOSITORY                           TAG                 IMAGE ID            CREATED             SIZE
sample-app                           0.0.1-SNAPSHOT      ab435d570bab        11 minutes ago      228MB
:
```

## Run application container

```
docker run --rm -p 8080:8080 -e APP_TITLE=sample-app5 sample-app:0.0.1-SNAPSHOT
```

### Operation check

```
curl http://localhost:8080
curl http://localhost:8080/delay/1000
curl http://localhost:8080/actuator/health
```

```
curl http://localhost:8080/actuator/ |jq .
curl http://localhost:8080/actuator/env |jq .
```

## Shutdown container using actuator endpoint

```
curl http://localhost:8080/actuator/shutdown -i -X POST
```

## Others
何度もコンテナをビルドするとゴミが残るので、下記のコマンドで削除

```
docker image prune
```

