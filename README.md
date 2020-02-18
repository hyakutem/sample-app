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

### push image to GitLab

```
docker login registry.recruit-tech.co.jp
```

```
docker tag sample-app:0.0.1-SNAPSHOT registry.recruit-tech.co.jp/hyakutake/sample-app:0.0.1-SNAPSHOT
docker push registry.recruit-tech.co.jp/hyakutake/sample-app:0.0.1-SNAPSHOT
```


## Run application container

```
docker run --rm -p 8080:8080 -e APP_TITLE=sample-app5 sample-app:0.0.1-SNAPSHOT
```

or (use pushed image to GitLab)

```
docker run --rm -p 8080:8080 -e APP_TITLE=sample-app5 registry.recruit-tech.co.jp/hyakutake/sample-app:0.0.1-SNAPSHOT
```


### Operation check

```
curl http://localhost:8080
curl http://localhost:8080/delay/1000
curl http://localhost:8080/randomDelay/3000
curl http://localhost:8080/actuator/health
```

※randomDelay/3000は、0[ms]~3000[ms]の間のランダムな[ms]経過後に"OK"を返す

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

