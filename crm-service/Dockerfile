FROM openjdk:11
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /appCrmService/crmService/lib
COPY ${DEPENDENCY}/META-INF /appCrmService/crmService/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /appCrmService/crmService
EXPOSE 8080
ADD target/*.jar appProducer.jar
ENTRYPOINT ["java", "-cp", "appCrmService/crmService:appCrmService/crmService/lib/*", "com.gltknbtn.CrmServiceApp"]