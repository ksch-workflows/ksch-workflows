# K.S.C.H. Workflows

Implementation of the K.S.C.H. Workflows application with Apache Wicket


## Code style

### Coala

```
coala --bears=SpaceConsistencyBear --files=src/\*\* --apply-patches
coala --bears=CheckstyleBear --files=src/\*\* --apply-patches
```

### Checkstyle

```
mvn checkstyle:check
```
