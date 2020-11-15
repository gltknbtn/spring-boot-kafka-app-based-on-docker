FROM openjdk:11
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /appSpringbootKafka/appProducer/lib
COPY ${DEPENDENCY}/META-INF /appSpringbootKafka/appProducer/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /appSpringbootKafka/appProducer
EXPOSE 8080
ADD target/*.jar appProducer.jar
ENTRYPOINT ["java", "-cp", "appSpringbootKafka/appProducer:appSpringbootKafka/appProducer/lib/*", "com.gltknbtn.SamplebootappKafkaProducer"]