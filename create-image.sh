#!/usr/bin/env bash
set -e
DIR="$(dirname "$0")"
#APP_NAME="quarkus/royal-tanks-native"
#"${DIR}/mvnw" clean compile -DskipTests=true
#"${DIR}/mvnw" package -DskipTests=true -Pnative -Dquarkus.native.container-build=true
#docker build -f "${DIR}/src/main/docker/Dockerfile.native" -t "${APP_NAME}" .
#docker run -i --rm -p 8080:8080 ${APP_NAME}

#BUILD
"${DIR}/mvnw" package -Dmaven.test.skip=true -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true