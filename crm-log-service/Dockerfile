FROM openjdk:11
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /appCrmLogService/crmLogService/lib
COPY ${DEPENDENCY}/BOOT-INF/classes /appCrmLogService/crmLogService
COPY ${DEPENDENCY}/META-INF /appCrmLogService/crmLogService/META-INF
EXPOSE 8081
ADD target/*.jar appConsumer.jar
ENTRYPOINT ["java", "-cp", "appCrmLogService/crmLogService:appCrmLogService/crmLogService/lib/*", "com.gltknbtn.CrmLogServiceApp"]