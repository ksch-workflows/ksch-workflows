package ksch.assertions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WicketId {

    @NonNull
    private final String value;

    public String toString() {
        return value;
    }
}
