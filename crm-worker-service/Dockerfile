FROM openjdk:11
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /appCrmWorkerService/crmWorkerService/lib
COPY ${DEPENDENCY}/BOOT-INF/classes /appCrmWorkerService/crmWorkerService
COPY ${DEPENDENCY}/META-INF /appCrmWorkerService/crmWorkerService/META-INF
EXPOSE 8081
ADD target/*.jar appConsumer.jar
ENTRYPOINT ["java", "-cp", "appCrmWorkerService/crmWorkerService:appCrmWorkerService/crmWorkerService/lib/*", "com.gltknbtn.CrmWorkerServiceApp"]