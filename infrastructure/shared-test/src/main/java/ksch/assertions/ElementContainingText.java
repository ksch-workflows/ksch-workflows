package ksch.assertions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ElementContainingText {

    @NonNull
    private final String value;

    public String toString() {
        return value;
    }
}
