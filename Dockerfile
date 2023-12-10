FROM openjdk:17-alpine
USER guest
ADD boot/target/order-service.jar order-service.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "order-service.jar"]