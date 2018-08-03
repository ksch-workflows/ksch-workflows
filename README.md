# K.S.C.H. Workflows [![Build Status](https://travis-ci.com/ksch-workflows/ksch-workflows.svg?branch=master)](https://travis-ci.com/ksch-workflows/ksch-workflows)

![Project logo](https://cdn.pixabay.com/photo/2014/03/25/15/26/bike-296852_960_720.png)

This project is a prototype for a custom hospital information system for the [Kirpal Sagar Chariable Hospital](http://kirpal-sagar.org/en/medicale-care/gemeinn%C3%BCtziges-krankenhaus.html).

The motivation behind it is to support the growth vision for the hospital over the next decades.

## Development

### Starting the application

The application can be started for development tests with the following Gradle task:

```
./gradlew bootRun
```

Then it is accessible in the browser under the URL http://localhost:8080.

### Run mutation tests

With the help of mutation tests it is possible to improve the quality of the unit
tests. It works like this that the mutation testing tool injects defects into the
source code and then executes the test suite. Afterwards it creates a report about
the defects which where caught by the unit tests and which where not. Those
instances where the injected defects where not caught are hints about parts of the
source code for which better unit tests should be created.

In this project the tool [PIT Mutation Testing](http://pitest.org/) is being used
for this purpose. It can be executed by running this command on the terminal:

```
./gradlew clean pitest
```

Afterwards HTML reports are generated for each respective subproject and can be found in the directory `build/reports/pitest`:

```
find . -name "index.html" | grep "pitest"
```

## Credits

- https://github.com/apache/wicket/
- https://github.com/MarcGiffing/wicket-spring-boot
- https://github.com/MarcGiffing/wicket-spring-boot-examples
- https://github.com/uxsolutions/bootstrap-datepicker
