FROM openjdk:11-jdk

RUN useradd -ms /bin/bash testuser
USER testuser

COPY user-interface/build/libs/ksch-workflows-0.1.0-SNAPSHOT.jar /opt/app/
WORKDIR /opt/app
EXPOSE 8080

CMD ["bash"]

ENTRYPOINT ["java","-jar","ksch-workflows-0.1.0-SNAPSHOT.jar"]
