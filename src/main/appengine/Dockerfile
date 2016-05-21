FROM gcr.io/google_appengine/openjdk8
VOLUME /tmp
ADD st-idot-service-0.1.0.war app.war
ENTRYPOINT [ "java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]