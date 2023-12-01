# İlk aşama: Maven bağımlılıklarını çöz ve projeyi derle
FROM openjdk:17-jdk-slim as build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

# İkinci aşama: Oluşan JAR dosyasını hafif bir OpenJDK imajına kopyala
FROM openjdk:17-jdk-slim
WORKDIR app
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
