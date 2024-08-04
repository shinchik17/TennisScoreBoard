FROM maven:3.9.8-eclipse-temurin-17-alpine AS build
WORKDIR /
COPY pom.xml .
COPY src ./src
RUN --mount=type=cache,target=/root/.m2  mvn clean package dependency:copy-dependencies -DincludeScope=runtime

FROM tomcat:10.1-jdk17-temurin-jammy
RUN addgroup app-group  \
    && adduser --ingroup app-group app-user \
    && chown -R app-user:app-group /usr/local/tomcat
USER app-user:app-group
WORKDIR /usr/local/tomcat/webapps/
ARG WAR_FILE=tennis-scoreboard.war
COPY --from=build /target/${WAR_FILE} tennis-scoreboard.war
EXPOSE 8080
ENTRYPOINT ["catalina.sh"]
CMD ["run"]