# K.S.C.H. Workflows [![Build Status](https://travis-ci.org/ksch-workflows/ksch-workflows.svg?branch=master)](https://travis-ci.org/ksch-workflows/ksch-workflows)

Implementation of the K.S.C.H. Workflows application with Apache Wicket

## Development

### Compiling the project

By running the following Gradle task the project can be packaged into a JAR file:

```
./gradlew bootJar
```

### Starting the application

After the compilation the KSCH Workflows application can be started like this:

```
java -jar build/libs/ksch-workflows-0.1.0-SNAPSHOT.jar
```

Then it can be accessed in the browser under the URL http://localhost:8080 .

Alternatively the application can also be started without explicit complication with the following Gradle task:

```
./gradlew bootRun
```

## Credits

- https://github.com/apache/wicket/
- https://github.com/MarcGiffing/wicket-spring-boot
- https://github.com/MarcGiffing/wicket-spring-boot-examples
