package ksch.assertions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CssQuery {

    @NonNull
    private final String locator;

    public String toString() {
        return locator;
    }
}
