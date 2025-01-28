FROM openjdk:17
VOLUME /tmp
COPY build/libs/cloudlab-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-Xmx300m", "-Xms300m", "-XX:TieredStopAtLevel=1", "-noverify", "-jar", "app.jar"]
EXPOSE 8080