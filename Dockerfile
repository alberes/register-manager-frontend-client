FROM maven:3.8.5-openjdk-17 as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17

WORKDIR /app

COPY --from=build ./build/target/*.jar ./register-manager-frontend-client.jar

expose 9090
expose 9091

ENV ADMIN_CLIENT_ID='admin-client-id'
ENV ADMIN_CLIENT_SECRET='admin-client-secret'
ENV AUTHORIZATION_CODE='authorization_code'
ENV SCOPE='openid, profile, email, address, phone'
ENV CLIENT_NAME='register-manager-frontend-client-oidc'
ENV ISSUER_URI='http://localhost:9090'
ENV LOG_NAME='register-manager-frontend-client.log'
ENV LOG_LEVEL='warn'
ENV REGISTER_MANAGER_RESOURCE_URL='http://localhost:9080'

ENTRYPOINT java -jar register-manager-frontend-client.jar