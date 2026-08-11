FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/shopping-cart-*.jar app.jar

EXPOSE 9193

ENTRYPOINT ["java", "-jar", "app.jar"]