# 2. Using Spring Boot

Date: 2018-07-07

## Status

Proposed

## Context

We need [dependency injection](https://stackoverflow.com/questions/130794/what-is-dependency-injection) in order to build decoupled software units.
Also we need access on a relational database.

## Decision

Spring offers a lot of convenience features for those purposes.
Apache Wicket already provides a Spring integration.
With the help of the [wicket-spring-boot](https://github.com/MarcGiffing/wicket-spring-boot) the convenience feature of Spring Boot can be applied on top of that.

This makes the development faster and easier than using the alternative of dependency injection with [Google Guice](http://software.danielwatrous.com/wicket-guice-including-unittests/).
Further usage of Spring enables potential useage of the various Spring sub-projects for advanced requirements like the integration of other software systems.

## Consequences

With Spring Boot there are happening a lot of things implicitly by things being on the classpath or some annotations being added.
While this leads to a very concise syntax it might make things hard to understand for people who are not familiar with the concepts of Spring.

However, with Spring Boot it is faily easy to implement things like database access.
Also it provides a powerful means for dependency injection which is very important for a good software structure.

In order to enable a broad range of contributions examples for day-to-day tasks and training for the introduction of new concept are required.
