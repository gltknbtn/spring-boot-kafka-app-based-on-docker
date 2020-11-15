FROM openjdk:11
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /appSpringbootKafka/appConsumer/lib
COPY ${DEPENDENCY}/BOOT-INF/classes /appSpringbootKafka/appConsumer
COPY ${DEPENDENCY}/META-INF /appSpringbootKafka/appConsumer/META-INF
EXPOSE 8081
ADD target/*.jar appConsumer.jar
ENTRYPOINT ["java", "-cp", "appSpringbootKafka/appConsumer:appSpringbootKafka/appConsumer/lib/*", "com.gltknbtn.SamplebootappKafkaConsumer"]