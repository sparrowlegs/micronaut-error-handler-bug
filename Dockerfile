FROM alpine:3.19
RUN apk add --no-cache java-cacerts openjdk21-jdk

RUN mkdir /mvn_verify

WORKDIR /mvn_verify

COPY  . .

RUN ./mvnw -ntp -B clean verify
