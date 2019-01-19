# K.S.C.H. Workflows

[![Build Status](https://travis-ci.com/ksch-workflows/ksch-workflows.svg?branch=master)](https://travis-ci.com/ksch-workflows/ksch-workflows) [![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/2328/badge)](https://bestpractices.coreinfrastructure.org/projects/2328)

The [Kirpal Sagar Charitable Hospital](https://kirpal-sagar.org/en/kirpal-charitable-hospital-en/) is a small hospital in Punjab, India where all people receive treatment and then pay what they can afford. It is envisioned to grow up to 500 beds and become part of a Medical College. The purpose of this project is to support that vision.

## Software architecture

In order to be aplicable in a low-resource environment we try to keep the system as simple as possible. Another important architectural goal is evolvability in terms of the business requirements. Further we need high quality in terms of robustness, test coverage, and technical documentation.

### Technology stack

The project is built upon the following technologies:

- [Java](https://github.com/ksch-workflows/ksch-workflows/wiki/Java) - the primarily used programming language
- [Spring Boot](https://github.com/ksch-workflows/ksch-workflows/wiki/Spring-Boot) - a framework for the development of enterprise applications
- [Apache Wicket](https://github.com/ksch-workflows/ksch-workflows/wiki/Apache-Wicket) - connects the HTML web pages with the business logic expressed in Java
- [Bootstrap](https://github.com/ksch-workflows/ksch-workflows/wiki/Bootstrap) - provides a comprehensive set of patterns for the layout of the HTML pages
- [Gradle](https://github.com/ksch-workflows/ksch-workflows/wiki/Gradle) - used for the compilation of the source code into an executable application  

### Repository structure

The system can be classified as [modular monolith](https://vimeo.com/233980163).
In order to create a good hospital domain model and enable collaboration with contractors and occasional contributors the software is split into multiple sub-projects.

## Development

### Dependencies

The project uses the JDK 8. Here are some hints for the installation of the Java Development Kit:

- [Ubuntu](https://github.com/ksch-workflows/ksch-workflows/wiki/Installing-Java-on-Ubuntu)
- [Windows](https://github.com/ksch-workflows/ksch-workflows/wiki/Installing-Java-on-Windows)
- [MacOS](https://github.com/ksch-workflows/ksch-workflows/wiki/Installing-Java-on-MacOS)

For the development environment IntelliJ, Eclipse, and Netbeans are supported.
Please refer to the following wiki pages for explanations for configuring those IDEs for this project:

- [IntelliJ](https://github.com/ksch-workflows/ksch-workflows/wiki/Import-Project-with-IntelliJ)
- [Eclipse](https://github.com/ksch-workflows/ksch-workflows/wiki/Import-Project-with-Eclipse)
- [NetBeans](https://github.com/ksch-workflows/ksch-workflows/wiki/Import-Project-with-NetBeans)

### Starting the application

The application can be started for development tests with the following Gradle task:

```
./gradlew clean bootRun -Dspring.profiles.active=qa
```

Then it is accessible in the browser under the URL http://localhost:8080 (login: user / pwd).

The database can be inspected under this URL: http://localhost:8080/h2-console (login: sa / <empty password>).

### Run mutation tests

With the help of mutation tests it is possible to improve the quality of the unit
tests. It works like this that the mutation testing tool injects defects into the
source code and then executes the test suite. Afterwards it creates a report about
the defects which where caught by the unit tests and which where not. Those
instances where the injected defects where not caught are hints about parts of the
source code for which better unit tests should be created.

```
./gradlew clean pitest
```

Afterwards HTML reports are generated for each respective subproject and can be found in the directory `build/reports/pitest`:

```
find . -name "index.html" | grep "pitest"
```

## Deployment

The executable JAR file can be compiled with the following Gradle task:

```
./gradlew clean bootJar
```

Afterwards you can find the JAR file in the following directory:

```
$ ls user-interface/build/libs
ksch-workflows-0.1.0-SNAPSHOT.jar
```

The JAR file can then be copied onto the server and started like this:

```
java -jar ksch-workflows-0.1.0-SNAPSHOT.jar
```

## Contact information

If you have any question or idea, please join this chat room and post it there:

https://gitter.im/ksch-workflows/Lobby

## License

This software licensed under the [Apache License Version 2.0](https://github.com/ksch-workflows/ksch-workflows/blob/master/LICENSE).
