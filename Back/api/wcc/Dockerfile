FROM gradle:8.2.1-jdk11 as builder
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY ./workspace/wcc_api/Back/api/wcc/build.gradle ./workspace/wcc_api/Back/api/wcc/settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY ./workspace/wcc_api/Back/api/wcc/ /build
RUN gradle build -x test --parallel

# credential 복사
COPY ./util/wcc_api/env.properties /build/src/main/resources/

# APP
FROM azul/zulu-openjdk:11
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
COPY --from=builder /build/build/libs/wcc-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

# root 대신 nobody 권한으로 실행
USER nobody
ENTRYPOINT [                                                \
    "java",                                                 \
    "-jar",                                                 \
    "-Djava.security.egd=file:/dev/./urandom",              \
    "-Dsun.net.inetaddr.ttl=0",                             \
    "wcc-0.0.1-SNAPSHOT.jar"              \
]
