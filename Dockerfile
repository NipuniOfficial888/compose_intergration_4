FROM eclipse-temurin:17-jdk
RUN mkdir -p /usr/local/tomcat/newrelic
ADD ./newrelic/newrelic.jar /usr/local/tomcat/newrelic/newrelic.jar
ADD ./newrelic/newrelic.yml /usr/local/tomcat/newrelic/newrelic.yml
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} compose-integration-v0.0.1.jar
EXPOSE 8080/tcp
ENV APM_ENABLE=false
# ENTRYPOINT ["java","-jar","/compose-integration-v0.0.1.jar"]
ENTRYPOINT ["sh", "-c", "if [ \"$APM_ENABLE\" = 'true' ]; then java -javaagent:/usr/local/tomcat/newrelic/newrelic.jar -jar /compose-integration-v0.0.1.jar; else java -jar /compose-integration-v0.0.1.jar; fi"]