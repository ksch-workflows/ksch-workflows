
# Contribution Guidelines

Contributions to this project from novices, beginners, intermediary, and advanced developers are welcome.
However, we need to find a compromise between pragmatism and engineering rigor because mistakes might impact people's health.
Also [software entropy](https://pragprog.com/the-pragmatic-programmer/extracts/software-entropy) could bring the project to a halt.

So the guidelines below are supposed to help to keep the code in this project in a high-quality shape.
If you think that they should be improved in some way or the other,
please create a ticket with your suggestion [here](https://github.com/ksch-workflows/ksch-workflows/issues/new).

## Test coverage

Ideally all features and attributes of the system should be covered by automated tests.

See https://martinfowler.com/articles/practical-test-pyramid.html

## Code style

### Checkstyle

```
./gradlew checkstyleMain
```

### Coala

```
coala --bears=SpaceConsistencyBear --files=src/\*\* --apply-patches
coala --bears=CheckstyleBear --files=src/\*\* --apply-patches
```

## Housekeeping

You might see "Housekeeping" commits in the history of the project where things like inconsitent code style are cleaned up.

See http://olivergierke.de/2013/03/juergenized/
