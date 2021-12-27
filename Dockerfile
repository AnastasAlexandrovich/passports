FROM openjdk:15
ADD target/passport-dockerfile.jar passport.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/passport.jar"]