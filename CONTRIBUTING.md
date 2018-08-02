
# Contribution Guidelines

Contributions to this project from novices, beginners, intermediary, and advanced developers are welcome.
However, we need to find a compromise between pragmatism and engineering rigor because mistakes might impact people's health.
Also [software entropy](https://pragprog.com/the-pragmatic-programmer/extracts/software-entropy) could bring the project to a halt.

So the guidelines below are supposed to help to keep the code in this project in a high-quality shape.
If you think that they should be improved in some way or the other,
please create a ticket with your suggestion [as a GitHub issue](https://github.com/ksch-workflows/ksch-workflows/issues/new).

## Test coverage

All features of the system should be covered by automated tests.

See https://martinfowler.com/articles/practical-test-pyramid.html

## Code style

The code style for this project is specified in the [checkstyle.xml](https://github.com/ksch-workflows/ksch-workflows/blob/master/config/checkstyle/checkstyle.xml) file.

During the build process the following Gradle tasks verify whether the source code complies with those rules:

```
./gradlew checkstyleMain checkstyleTest
```
