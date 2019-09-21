# openehr-impl

An incomplete and un-precise implementation of the [openEHR specifications](https://specifications.openehr.org).

## Corners cut

Here is where the implementation currently deviated from the specifications:

- Where references to Archetypes are made, abstract classes are being used which are then implemented in the domain modules
- Enums and plain strings are being used instead basic data types
