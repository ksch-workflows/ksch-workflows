# 3. Using Gradle instead of Maven

Date: 2018-07-07

## Status

Proposed

## Context

When you create a new Wicket project [like described in their documentation](https://wicket.apache.org/start/quickstart.html) it will use Maven as build tool.
The examples in the [wicket-spring-boot-examples](https://github.com/MarcGiffing/wicket-spring-boot-examples) are applying the [wicket-spring-boot-starter](https://github.com/MarcGiffing/wicket-spring-boot/tree/master/wicket-spring-boot-starter) as parent project, e.g. [spring-actuator/pom.xml#L6](https://github.com/MarcGiffing/wicket-spring-boot-examples/blob/master/spring-actuator/pom.xml#L6).
This has the implication that the version from the `wicket-spring-boot-starter` is also applied as version number for the actual project.
However, the project version number should be independent of this.
It seem like this is not possible (see [stackoverflow.com](https://stackoverflow.com/questions/10582054/maven-project-version-inheritance-do-i-have-to-specify-the-parent-version)).

## Decision

Maybe it is not necessary to apply the Spring Boot Wicket Starter project as parent project.
Maybe there is some workaround to for the issue with the version number.
However, Gradle might still be a good choice as it has a more concise syntax and offers nice features like a handy sub-module system.

Thus the build process of the project will be implemented with the help of [Gradle](https://gradle.org/) tasks.

## Consequences

* With Gradle it becomes difficult to use Spring together the Java Platform Module System (JPMS) (see [stackoverflow.com](https://stackoverflow.com/questions/50787235/combination-of-module-path-and-class-path-with-gradle)).
* Gradle has its own sub-module system. This might be good enough for the modularization of this project.
